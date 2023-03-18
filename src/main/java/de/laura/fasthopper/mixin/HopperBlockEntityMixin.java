package de.laura.fasthopper.mixin;

import net.minecraft.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HopperBlockEntity.class)
public class HopperBlockEntityMixin {
    @Inject(method = "needsCooldown()Z", at = @At("HEAD"), cancellable = true)
    private void needsCooldown(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
