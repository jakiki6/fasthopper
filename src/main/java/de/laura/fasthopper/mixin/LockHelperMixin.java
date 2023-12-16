package de.laura.fasthopper.mixin;

import net.minecraft.util.crash.CrashException;
import net.minecraft.util.thread.LockHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

@Mixin(LockHelper.class)
public class LockHelperMixin {
    @Shadow @Final private Lock lock;

    @Shadow @Final private Semaphore semaphore;

    @Inject(method = "unlock", at = @At("HEAD"), cancellable = true)
    public void unlock(CallbackInfo ci) {
        ci.cancel();

        try {
            this.lock.lock();
            this.semaphore.release();
        } finally {
            this.lock.unlock();
        }
    }
}
