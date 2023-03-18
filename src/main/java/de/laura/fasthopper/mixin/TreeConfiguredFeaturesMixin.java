package de.laura.fasthopper.mixin;

import de.laura.fasthopper.BuilderWrapper;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.feature.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.world.gen.feature.TreeFeatureConfig.Builder;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TreeConfiguredFeatures.class)
public class TreeConfiguredFeaturesMixin {
    @Mutable
    @Final
    @Shadow
    public static RegistryKey<ConfiguredFeature<TreeFeatureConfig, ?>> OAK;

    @Inject(method = "oak", at = @At("HEAD"), cancellable = true)
    private static void injected(CallbackInfoReturnable<Builder> cir) {
        cir.setReturnValue(new BuilderWrapper());
    }
}