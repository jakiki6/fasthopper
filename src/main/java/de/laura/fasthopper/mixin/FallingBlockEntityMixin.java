package de.laura.fasthopper.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin extends Entity {
    @Shadow private BlockState block;

    public FallingBlockEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    /*@Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        this.block = Blocks.AIR.getDefaultState();

        while (this.block.getBlock().equals(Blocks.AIR)) {
            Block block = Registries.BLOCK.get(getEntityWorld().random.nextInt(Registries.BLOCK.size() - 1));
            this.block = block.getStateManager().getStates().get(getEntityWorld().random.nextInt(block.getStateManager().getStates().size()));
        }
    }*/
}
