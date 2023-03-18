package de.laura.fasthopper.mixin;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {
    @Shadow
    int cookTime;
    @Shadow
    int cookTimeTotal;

    @Inject(method = "isBurning()Z", at = @At("HEAD"))
    private void isBurning(CallbackInfoReturnable<Boolean> cir) {
        this.cookTime = 0;
        this.cookTimeTotal = 1;
    }
}
