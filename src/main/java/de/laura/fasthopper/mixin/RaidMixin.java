package de.laura.fasthopper.mixin;

import net.minecraft.village.raid.Raid;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Raid.class)
public class RaidMixin {
    @Inject(method = "getMaxAcceptableBadOmenLevel", at = @At("HEAD"), cancellable = true)
    public void getMaxAcceptableBadOmenLevel(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(256);
    }

    @Inject(method = "getMaxWaves", at = @At("HEAD"), cancellable = true)
    public void getMaxWaves(Difficulty difficulty, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(256);
    }
}
