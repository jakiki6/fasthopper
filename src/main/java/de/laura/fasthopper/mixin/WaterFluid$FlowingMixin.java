package de.laura.fasthopper.mixin;

import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WaterFluid.Flowing.class)
public abstract class WaterFluid$FlowingMixin extends WaterFluid {
    //@Unique
    //World w;

    @Inject(method = "getLevel", at = @At("HEAD"), cancellable = true)
    public void getLevel(FluidState state, CallbackInfoReturnable<Integer> cir) {
        //if (w == null) return;

        //w.getPlayers().forEach(playerEntity -> {
            //if (playerEntity.isSneaking()) {
                //cir.setReturnValue(8);
                //cir.cancel();
            //}
        //});
    }
}
