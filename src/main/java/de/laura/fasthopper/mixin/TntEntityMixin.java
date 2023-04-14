package de.laura.fasthopper.mixin;

import net.minecraft.entity.TntEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(TntEntity.class)
public class TntEntityMixin {
    @ModifyConstant(method = "explode", constant = @Constant(floatValue = 4.0F))
    public float explode(float old) {
        return 32.0f;
    }
}
