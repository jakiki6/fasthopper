package de.laura.fasthopper.mixin;

import net.minecraft.entity.*;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.village.raid.Raid;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PillagerEntity.class)
public abstract class PillagerEntityMixin extends IllagerEntity implements CrossbowUser, InventoryOwner {
    protected PillagerEntityMixin(EntityType<? extends IllagerEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "isRaidCaptain", at = @At("HEAD"), cancellable = true)
    public void isRaidCaptain(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

    @Inject(method = "initialize", at = @At("RETURN"))
    public void initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir) {
        this.equipStack(EquipmentSlot.HEAD, Raid.getOminousBanner());
    }
}
