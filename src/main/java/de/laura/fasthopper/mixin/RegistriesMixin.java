package de.laura.fasthopper.mixin;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

@Mixin(Registries.class)
public class RegistriesMixin {
    @Inject(method = "freezeRegistries", at = @At("HEAD"), cancellable = true)
    private static void freezeRegistries(CallbackInfo ci) {
        Registries.REGISTRIES.freeze();

        for (Registry<?> objects : Registries.REGISTRIES) {
            try {
                objects.freeze();
            } catch (IllegalStateException ignored) {}
        }

        ci.cancel();
    }
}
