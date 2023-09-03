package de.laura.fasthopper.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.util.collection.Weight;
import net.minecraft.util.collection.Weighted;
import net.minecraft.world.biome.SpawnSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(SpawnSettings.SpawnEntry.class)
public class SpawnSettings$SpawnEntryMixin extends Weighted.Absent {
    @Mutable
    @Shadow @Final public int minGroupSize;

    @Mutable
    @Shadow @Final public int maxGroupSize;

    public SpawnSettings$SpawnEntryMixin(Weight weight) {
        super(weight);
    }

    @Inject(method = "<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/util/collection/Weight;II)V", at = @At("TAIL"))
    private void init(EntityType<?> type, Weight weight, int minGroupSize, int maxGroupSize, CallbackInfo ci) {
        if (type.equals(EntityType.WITHER_SKELETON)) {
            this.minGroupSize = 10;
            this.maxGroupSize = 10;

            try {
                Field field = this.getClass().getField("weight");
                field.setAccessible(true);
                field.set(this, Weight.of(30));
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
    }
}
