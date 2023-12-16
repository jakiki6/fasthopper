package de.laura.fasthopper.mixin;

import com.mojang.datafixers.DataFixer;
import de.laura.fasthopper.interf.IThreadedAnvilChunkStorage;
import it.unimi.dsi.fastutil.longs.Long2LongMap;
import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ChunkHolder;
import net.minecraft.server.world.ServerLightingProvider;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.storage.VersionedChunkStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.nio.file.Path;

@Mixin(net.minecraft.server.world.ThreadedAnvilChunkStorage.class)
public abstract class ThreadedAnvilChunkStorageMixin extends VersionedChunkStorage implements ChunkHolder.PlayersWatchingChunkProvider, IThreadedAnvilChunkStorage {
    public ThreadedAnvilChunkStorageMixin(Path directory, DataFixer dataFixer, boolean dsync) {
        super(directory, dataFixer, dsync);
    }

    @Shadow protected abstract boolean save(Chunk chunk);

    @Shadow @Final private LongSet loadedChunks;

    @Shadow @Final
    ServerWorld world;

    @Shadow @Final private ServerLightingProvider lightingProvider;

    @Shadow @Final private WorldGenerationProgressListener worldGenerationProgressListener;

    @Shadow @Final private Long2LongMap chunkToNextSaveTimeMs;

    @Unique
    public void fasthopper$forceUnload(WorldChunk chunk) {
        chunk.setLoadedToWorld(false);
        this.save(chunk);
        this.loadedChunks.remove(chunk.getPos().toLong());
        this.world.unloadEntities(chunk);
        this.lightingProvider.updateChunkStatus(chunk.getPos());
        this.lightingProvider.tick();
        this.worldGenerationProgressListener.setChunkStatus(chunk.getPos(), (ChunkStatus)null);
        this.chunkToNextSaveTimeMs.remove(chunk.getPos().toLong());
    }
}
