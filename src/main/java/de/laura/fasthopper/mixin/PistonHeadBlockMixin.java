package de.laura.fasthopper.mixin;

import net.minecraft.block.PistonHeadBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PistonHeadBlock.class)
public class PistonHeadBlockMixin {
    @Redirect(method = "onStateReplaced", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;breakBlock(Lnet/minecraft/util/math/BlockPos;Z)Z"))
    public boolean onStateReplaced(World instance, BlockPos blockPos, boolean b) {
        return false;
    }
}
