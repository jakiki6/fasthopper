package de.laura.fasthopper.mixin;

import net.minecraft.nbt.NbtString;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.DataOutput;
import java.io.IOException;

@Mixin(NbtString.class)
public class NbtStringMixin {
    @Final
    @Shadow
    private String value;

    @Inject(method = "write", at = @At("HEAD"), cancellable = true)
    public void write(DataOutput output, CallbackInfo ci) throws IOException {
        output.writeUTF(this.value);
        ci.cancel();
    }
}