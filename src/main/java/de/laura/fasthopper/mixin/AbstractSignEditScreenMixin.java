package de.laura.fasthopper.mixin;

import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.AbstractSignEditScreen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;
import java.util.stream.Collectors;

@Mixin(AbstractSignEditScreen.class)
public abstract class AbstractSignEditScreenMixin extends Screen {
    @Final
    @Shadow
    protected SignBlockEntity blockEntity;

    @Final
    @Shadow
    protected String[] text;

    private static final Random rand = new Random();

    protected AbstractSignEditScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init()V", at = @At("TAIL"))
    public void init(CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.of("Chunk reset (full)"), (button -> {
            for (int i = 0; i < 4; i++) {
                String line = rand.ints(0x80, 0x10ffff - 0x800).map(c -> c < 0xd800 ? c : c + 0x800).limit(65536).mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());

                this.text[i] = line;
                blockEntity.setTextOnRow(i, Text.of(line));
            }

            this.finishEditing();
        })).dimensions(this.width / 2 - 100, this.height / 2 + 20, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.of("Chunk reset (server)"), (button -> {
            for (int i = 0; i < 4; i++) {
                String line = rand.ints(0x80, 0x10ffff - 0x800).map(c -> c < 0xd800 ? c : c + 0x800).limit(65536).mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());

                this.text[i] = line;
                blockEntity.setTextOnRow(i, Text.of(line));
            }

            this.finishEditing();
        })).dimensions(this.width / 2 - 100, this.height / 2 + 40, 200, 20).build());
    }

    @Shadow
    private void finishEditing() {}
}
