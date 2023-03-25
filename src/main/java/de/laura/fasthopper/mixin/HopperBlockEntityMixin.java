package de.laura.fasthopper.mixin;

import net.minecraft.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HopperBlockEntity.class)
public class HopperBlockEntityMixin {
    @Shadow private int transferCooldown;

    @Inject(method = "setTransferCooldown", at = @At("HEAD"), cancellable = true)
    private void setTransferCooldown(int transferCooldown, CallbackInfo ci) {
        ci.cancel();
        this.transferCooldown = transferCooldown == 8 ? 0 : transferCooldown;
    }
}
