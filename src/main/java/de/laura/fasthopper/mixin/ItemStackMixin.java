package de.laura.fasthopper.mixin;

import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.client.item.TooltipData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemStack.class)
public class ItemStackMixin implements FabricItemStack {
    int size = -1;

    @Inject(method = "getTooltip", at = @At("RETURN"), cancellable = true)
    public void getTooltip(CallbackInfoReturnable<List<Text>> cir) {
        ItemStack item = (ItemStack) (Object) this;

        if (item.getNbt() != null) {
            if (size == -1) {
                size = item.getNbt().getSizeInBytes();
            }
            ArrayList<Text> list = new ArrayList<>(cir.getReturnValue());
            list.add(Text.of("Size in bytes: " + size + " (" + (size / 2097000f * 100f) + "%)"));
            cir.setReturnValue(list);
        }
    }
}
