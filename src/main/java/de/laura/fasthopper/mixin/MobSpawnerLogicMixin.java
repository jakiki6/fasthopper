package de.laura.fasthopper.mixin;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobSpawnerLogic.class)
public class MobSpawnerLogicMixin {
    @Shadow private int spawnDelay = 20;
    @Shadow private int minSpawnDelay;
    @Shadow private int maxSpawnDelay;
    @Shadow private int maxNearbyEntities = 6;

    @Inject(method = "readNbt", at = @At("TAIL"))
    public void readNbt(World world, BlockPos pos, NbtCompound nbt, CallbackInfo ci) {
        this.spawnDelay = 1;
        this.minSpawnDelay = 1;
        this.maxSpawnDelay = 1;
        this.maxNearbyEntities = 12;
    }
}
