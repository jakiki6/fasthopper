package de.laura.fasthopper.mixin;

import net.minecraft.block.sapling.OakSaplingGenerator;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(OakSaplingGenerator.class)
public class OakSaplingGeneratorMixin extends SaplingGenerator {
    public RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return TreeConfiguredFeatures.OAK;
    }
}
