package de.laura.fasthopper.mixin;

import net.minecraft.entity.raid.RaiderEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(RaiderEntity.class)
public class RaiderEntityMixin {
    @ModifyConstant(method = "onDeath", constant = @Constant(intValue = 4))
    public int modify(int old) {
        return 255;
    }
}
