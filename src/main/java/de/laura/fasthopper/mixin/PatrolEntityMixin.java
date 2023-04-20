package de.laura.fasthopper.mixin;

import net.minecraft.entity.mob.PatrolEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PatrolEntity.class)
public class PatrolEntityMixin {
    @Inject(method = "isPatrolLeader", at = @At("HEAD"), cancellable = true)
    public void isPatrolLeader(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
