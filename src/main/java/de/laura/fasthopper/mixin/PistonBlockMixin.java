package de.laura.fasthopper.mixin;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PistonBlock.class)
public class PistonBlockMixin extends FacingBlock {
    protected PistonBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "tryMove", at = @At("HEAD"))
    private void tryMove(World world, BlockPos pos, BlockState state, CallbackInfo ci) {
        Direction direction = state.get(FACING);
        BlockPos targetPos = pos.offset(direction);

        if (((PistonBlock) (Object) this).shouldExtend(world, pos, direction)) {
            BlockState targetState = world.getBlockState(targetPos);
            if (targetState.getBlock().equals(Blocks.PISTON_HEAD)) {
                if (targetState.get(FACING).compareTo(direction) != 0)
                world.getChunk(pos).setBlockState(targetPos, new BlockState(Blocks.AIR, null, null), false);
            }
        }
    }

    @Redirect(method = "isMovable", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private static boolean patchPiston(BlockState instance, Block block) {
        return false;
    }

}
