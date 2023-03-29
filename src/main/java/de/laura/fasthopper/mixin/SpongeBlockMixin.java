package de.laura.fasthopper.mixin;

import net.minecraft.block.SpongeBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

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
}
