package de.laura.fasthopper.mixin;

import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DownloadingTerrainScreen.class)
public class DownloadingTerrainScreenMixin extends Screen {
    protected DownloadingTerrainScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "shouldCloseOnEsc", at = @At("HEAD"), cancellable = true)
    private void shouldCloseOnEsc(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
