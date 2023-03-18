package de.laura.fasthopper.mixin;


import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.client.item.TooltipData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.ToggleableFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(Item.class)
public abstract class ItemMixin implements ToggleableFeature, ItemConvertible, FabricItem {
    @Inject(method = "getMaxCount()I", at = @At("HEAD"), cancellable = true)
    private void getMaxCount(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(64);
    }
}