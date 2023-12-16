package de.laura.fasthopper.interf;

import net.minecraft.world.chunk.WorldChunk;

public interface IThreadedAnvilChunkStorage {
    public void fasthopper$forceUnload(WorldChunk chunk);
}
