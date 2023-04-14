package de.laura.fasthopper.mixin;

import net.minecraft.block.SpongeBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SpongeBlock.class)
public class SpongeBlockMixin {
    @ModifyConstant(method = "absorbWater", constant = @Constant(intValue = 6))
    private static int modify6(int constant) {
        return 128;
    }

    @ModifyConstant(method = "absorbWater", constant = @Constant(intValue = 64))
    private static int modify64(int constant) {
        return Integer.MAX_VALUE;
    }

    @Redirect(method = "absorbWater", at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/FluidState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    public boolean absorbLava(FluidState instance, TagKey<Fluid> tag) {
        return true;
    }
}
