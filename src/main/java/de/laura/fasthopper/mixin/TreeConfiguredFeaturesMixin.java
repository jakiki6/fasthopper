package de.laura.fasthopper.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.MegaPineFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.trunk.GiantTrunkPlacer;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TreeConfiguredFeatures.class)
public class TreeConfiguredFeaturesMixin {
    /**
     * @author e
     * @reason e
     */
    @Overwrite
    private static TreeFeatureConfig.Builder fancyOak() {
        return new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.OAK_LOG),
                new GiantTrunkPlacer(13, 2, 14),
                BlockStateProvider.of(Blocks.OAK_LEAVES),
                new MegaPineFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), UniformIntProvider.create(13, 17)),
                new TwoLayersFeatureSize(1, 1, 2)).decorators(ImmutableList.of(new AlterGroundTreeDecorator(BlockStateProvider.of(Blocks.PODZOL))));
    }
}
