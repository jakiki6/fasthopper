package de.laura.fasthopper.mixin;

import net.minecraft.block.piston.PistonHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PistonHandler.class)
public class PistonHandlerMixin {
    @Mutable
    @Shadow @Final public static int MAX_MOVABLE_BLOCKS;

    static {
        MAX_MOVABLE_BLOCKS = Integer.MAX_VALUE;
    }

    @ModifyConstant(method = "tryMove", constant = @Constant(intValue = 12))
    public int tryMovePatch(int constant) {
        return MAX_MOVABLE_BLOCKS;
    }
}
