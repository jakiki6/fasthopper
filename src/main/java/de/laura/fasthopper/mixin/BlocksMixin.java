package de.laura.fasthopper.mixin;

import de.laura.fasthopper.LargeOakSaplingGenerator;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Blocks.class)
public class BlocksMixin {
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Blocks;register(Ljava/lang/String;Lnet/minecraft/block/Block;)Lnet/minecraft/block/Block;"))
    private static Block injected(String id, Block block) {
        switch (id) {
            case "obsidian":
                block = new Block(AbstractBlock.Settings.create().mapColor(MapColor.BLACK).instrument(Instrument.BASEDRUM).requiresTool().strength(1.5F, 1200.0F));
                break;
            case "blue_ice":
                block = new TransparentBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_PURPLE).strength(1.5F).slipperiness(1.0F).sounds(BlockSoundGroup.GLASS));
                break;
            case "deepslate":
                block = new PillarBlock(AbstractBlock.Settings.create().mapColor(MapColor.DEEPSLATE_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(1.5F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE));
                break;
            case "oak_sapling":
                block = new SaplingBlock(new LargeOakSaplingGenerator(), AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY));
                break;
        }

        return Registry.register(Registries.BLOCK, id, block);
    }
}
