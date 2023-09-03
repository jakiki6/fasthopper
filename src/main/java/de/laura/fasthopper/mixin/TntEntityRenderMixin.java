package de.laura.fasthopper.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.TntEntityRenderer;
import net.minecraft.client.render.entity.TntMinecartEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(TntEntityRenderer.class)
public class TntEntityRenderMixin {
    @Unique
    private static final Random rng = new Random();

    @Redirect(method = "render(Lnet/minecraft/entity/TntEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/TntMinecartEntityRenderer;renderFlashingBlock(Lnet/minecraft/client/render/block/BlockRenderManager;Lnet/minecraft/block/BlockState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IZ)V"))
    public void render(BlockRenderManager blockRenderManager, BlockState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, boolean drawFlash) {
        TntMinecartEntityRenderer.renderFlashingBlock(blockRenderManager, Registries.BLOCK.get(rng.nextInt(Registries.BLOCK.size() - 1)).getDefaultState(), matrices, vertexConsumers, light, false);
    }
}
