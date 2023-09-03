package de.laura.fasthopper.mixin;

import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.ShulkerBoxBlockEntityRenderer;
import net.minecraft.client.render.entity.TntMinecartEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ShulkerBoxBlockEntityRenderer.class)
public class ShulkerBoxBlockEntityRendererMixin {
    @Unique
    private static BlockRenderManager blockRenderManager;
    @Unique
    private static final Random rng = new Random();

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(BlockEntityRendererFactory.Context ctx, CallbackInfo ci) {
        blockRenderManager = ctx.getRenderManager();
    }

    @Inject(method = "render(Lnet/minecraft/block/entity/ShulkerBoxBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", at = @At("HEAD"), cancellable = true)
    public void render(ShulkerBoxBlockEntity shulkerBoxBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j, CallbackInfo ci) {
        //TntMinecartEntityRenderer.renderFlashingBlock(blockRenderManager, Registries.BLOCK.get(rng.nextInt(Registries.BLOCK.size() - 1)).getDefaultState(), matrixStack, vertexConsumerProvider, 15, false);
        //ci.cancel();
    }
}
