package de.laura.fasthopper.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.block.NeighborUpdater;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(NeighborUpdater.class)
public interface NeighborUpdaterMixin {
    /*@Inject(method = "tryNeighborUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/crash/CrashReport;create(Ljava/lang/Throwable;Ljava/lang/String;)Lnet/minecraft/util/crash/CrashReport;"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void updateSuppressionCrashFix(World world, BlockState state, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify, CallbackInfo ci, Throwable throwable) throws Exception {
        if (throwable instanceof Exception e) throw e;
        if (throwable instanceof StackOverflowError e) {
            throw new Exception("Update suppression", e);
        }
    }*/
}