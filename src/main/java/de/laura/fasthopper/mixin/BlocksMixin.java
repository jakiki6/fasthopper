package de.laura.fasthopper.mixin;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Blocks.class)
public class BlocksMixin {
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Blocks;register(Ljava/lang/String;Lnet/minecraft/block/Block;)Lnet/minecraft/block/Block;"))
    private static Block injected(String id, Block block) {
        switch (id) {
            case "obsidian":
                block = new Block(AbstractBlock.Settings.of(Material.STONE, MapColor.BLACK).requiresTool().strength(1.5F, 1200.0F));
                break;
            case "blue_ice":
                block = new TransparentBlock(AbstractBlock.Settings.of(Material.DENSE_ICE).strength(2.8F).slipperiness(1.1F).sounds(BlockSoundGroup.GLASS));
                break;
            case "deepslate":
                block = new PillarBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.DEEPSLATE_GRAY).requiresTool().strength(1.5F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE));
                break;
            case "lava":
                block = new FluidBlock(Fluids.LAVA, AbstractBlock.Settings.of(Material.LAVA).noCollision().ticksRandomly().strength(0.01F).luminance((state) -> 15).dropsNothing());
                break;
        }

        return Registry.register(Registries.BLOCK, id, block);
    }
}
