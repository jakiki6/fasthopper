package de.laura.fasthopper.mixin;

import net.minecraft.block.entity.SignText;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SignBlockEntityRenderer.class)
public class SignBlockEntityRendererMixin {
    @ModifyVariable(method = "renderText", at = @At("HEAD"), index = 2, argsOnly = true)
    SignText renderText(SignText value) {
        for (int i = 0; i < 4; i++) {
            value = value.withMessage(i, value.getMessage(i, false).getString().length() > 90 ? Text.of("§cCHUNK RESET CHUNK RESET CHUNK RESET") : value.getMessage(i, false));
        }

        return value;
    }
}
