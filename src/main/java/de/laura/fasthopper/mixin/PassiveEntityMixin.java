package de.laura.fasthopper.mixin;

import net.minecraft.entity.passive.PassiveEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PassiveEntity.class)
public class PassiveEntityMixin {
    @ModifyVariable(method = "setBreedingAge", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int modify(int x) {
        return 0;
    }
}
