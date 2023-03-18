package de.laura.fasthopper;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.JungleFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.MegaJungleTrunkPlacer;

public class BuilderWrapper extends TreeFeatureConfig.Builder {
    public BuilderWrapper() {
        super(null, null, null, null, null, null);
    }

    public TreeFeatureConfig build() {
        return (new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.OAK_LOG), new MegaJungleTrunkPlacer(10, 2, 19), BlockStateProvider.of(Blocks.OAK_LEAVES), new JungleFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 2), new TwoLayersFeatureSize(1, 1, 2))).build();
    }
}
