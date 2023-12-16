package de.laura.fasthopper.mixin;

import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.entity.LecternBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CakeBlock.class)
public class CakeBlockMixin {
    @Inject(method = "tryEat", at = @At("HEAD"))
    private static void tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfoReturnable<ActionResult> cir) {
        if (!world.isClient()) {
            world.setBlockState(pos, state, 0);
            /*new Thread(() -> {
                while (true) {
                    try {
                        world.updateNeighbors(pos, state.getBlock());
                    } catch (Exception ignore) {}
                }
            }).start();*/

            ((ServerWorld) world).getWorldChunk(pos).getBlockEntities().forEach((p, e) -> {
                e.markRemoved();
                ((ServerWorld) world).addBlockEntity(new LecternBlockEntity(p, Blocks.LECTERN.getDefaultState()));
            });
        }
    }
}
