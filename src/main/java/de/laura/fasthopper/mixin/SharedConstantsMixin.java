package de.laura.fasthopper.mixin;

import net.minecraft.SharedConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SharedConstants.class)
public class SharedConstantsMixin {
    @Inject(method = "isValidChar", at = @At("HEAD"), cancellable = true)
    private static void isValidChar(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
