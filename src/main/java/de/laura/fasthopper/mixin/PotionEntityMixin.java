package de.laura.fasthopper.mixin;

import net.minecraft.block.AbstractCandleBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PotionEntity.class)
public abstract class PotionEntityMixin extends ThrownItemEntity implements FlyingItemEntity  {
    @Unique
    private static final int RANGE = 8;

    public PotionEntityMixin(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "onBlockHit", at = @At("TAIL"))
    public void onBlockHit(BlockHitResult blockHitResult, CallbackInfo ci) {
        ItemStack itemStack = ((PotionEntity) (Object) this).getStack();
        Potion potion = PotionUtil.getPotion(itemStack);
        List<StatusEffectInstance> list = PotionUtil.getPotionEffects(itemStack);
        boolean bl = potion == Potions.WATER && list.isEmpty();
        BlockPos blockPos = blockHitResult.getBlockPos();

        if (bl) {
            for (int x = -RANGE; x <= RANGE; x++) {
                for (int y = -RANGE; y <= RANGE; y++) {
                    for (int z = -RANGE; z <= RANGE; z++) {
                        extinguishFire(blockPos.add(x, y, z));
                    }
                }
            }
        }
    }

    @Unique
    private void extinguishFire(BlockPos pos) {
        BlockState blockState = this.getWorld().getBlockState(pos);
        if (blockState.isIn(BlockTags.FIRE)) {
            this.getWorld().removeBlock(pos, false);
        } else if (AbstractCandleBlock.isLitCandle(blockState)) {
            AbstractCandleBlock.extinguish((PlayerEntity)null, blockState, this.getWorld(), pos);
        } else if (CampfireBlock.isLitCampfire(blockState)) {
            this.getWorld().syncWorldEvent((PlayerEntity)null, 1009, pos, 0);
            CampfireBlock.extinguish(this.getOwner(), this.getWorld(), pos, blockState);
            this.getWorld().setBlockState(pos, (BlockState)blockState.with(CampfireBlock.LIT, false));
        }
    }
}
