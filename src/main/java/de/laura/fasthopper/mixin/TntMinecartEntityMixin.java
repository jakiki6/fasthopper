package de.laura.fasthopper.mixin;

import net.minecraft.entity.vehicle.TntMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(TntMinecartEntity.class)
public class TntMinecartEntityMixin {
    @ModifyVariable(method = "explode(Lnet/minecraft/entity/damage/DamageSource;D)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private double modifyPower(double x) {
        return 32;
    }

    @ModifyConstant(method = "explode(Lnet/minecraft/entity/damage/DamageSource;D)V", constant = @Constant(doubleValue = 5.0D))
    public double modifyLimit(double x) {
        return Double.MAX_VALUE;
    }

    @Redirect(method = "explode(Lnet/minecraft/entity/damage/DamageSource;D)V", at = @At(value = "INVOKE", target="Lnet/minecraft/entity/vehicle/TntMinecartEntity;getY()D"))
    public double getY(TntMinecartEntity instance) {
        return instance.getY() + 1.0;
    }
}
