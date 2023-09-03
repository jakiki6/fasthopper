package de.laura.fasthopper.mixin;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TradeOffer.class)
public class TradeOfferMixin {
    @Mutable
    @Shadow @Final private int maxUses;

    @Inject(method = "<init>(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;IIIFI)V", at = @At("TAIL"))
    public void init(ItemStack firstBuyItem, ItemStack secondBuyItem, ItemStack sellItem, int _uses, int maxUses, int merchantExperience, float priceMultiplier, int demandBonus, CallbackInfo ci) {
        this.maxUses = Integer.MAX_VALUE;
    }

    @Inject(method = "updateDemandBonus", at = @At("HEAD"), cancellable = true)
    public void updateDemandBonus(CallbackInfo ci) {
        ci.cancel();
    }
}
