package de.laura.fasthopper.mixin;

import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Material.class)
public class MaterialMixin {
    @Mutable
    @Shadow @Final public static Material PISTON;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void patchPiston(CallbackInfo ci) {
        PISTON = (new Material.Builder(MapColor.STONE_GRAY)).allowsMovement().build();
    }
}
