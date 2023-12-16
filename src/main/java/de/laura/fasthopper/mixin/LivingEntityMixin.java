package de.laura.fasthopper.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {
    @Shadow public abstract boolean isFallFlying();

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyConstant(method = "travel", constant = @Constant(doubleValue = 0.9800000190734863))
    private static double terminalVelocityChange(double constant) {
        return 1.00;
    }

    @Inject(method = "hasNoDrag", at = @At("RETURN"), cancellable = true)
    public void hasNoDrag(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(cir.getReturnValue() || this.isFallFlying());
    }

    @Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getSlipperiness()F"))
    public float modifySlipperiness(Block instance) {
        return this.isFallFlying() ? 1.0f : instance.getSlipperiness();
    }
}
