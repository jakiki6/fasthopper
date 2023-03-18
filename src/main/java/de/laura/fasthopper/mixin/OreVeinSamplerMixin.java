package de.laura.fasthopper.mixin;

import net.minecraft.world.gen.OreVeinSampler;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(OreVeinSampler.class)
public class OreVeinSamplerMixin {
    /*@ModifyConstant(method = "create", constant = @Constant(floatValue = 0.02F))
    private static float rand(float constant) {
        return 1.0F;
    }*/
}
