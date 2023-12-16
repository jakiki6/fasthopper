package de.laura.fasthopper.mixin;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(CommandManager.class)
public class CommandManagerMixin {
    @Shadow @Final private CommandDispatcher<ServerCommandSource> dispatcher;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addDebuggingCommands(CommandManager.RegistrationEnvironment environment, CommandRegistryAccess commandRegistryAccess, CallbackInfo ci) {
//        ChaseCommand.register(this.dispatcher);
        DebugMobSpawningCommand.register(this.dispatcher);
//        DebugPathCommand.register(this.dispatcher);
//       JfrCommand.register(this.dispatcher);
//        PublishCommand.register(this.dispatcher);
        RaidCommand.register(this.dispatcher);
        ResetChunksCommand.register(this.dispatcher);
        SpawnArmorTrimsCommand.register(this.dispatcher);
//        TestCommand.register(this.dispatcher);
        WardenSpawnTrackerCommand.register(this.dispatcher);

        this.dispatcher.register(CommandManager.literal("s").executes(context -> {
            Objects.requireNonNull(context.getSource().getPlayer()).kill();
            return 1;
        }));
    }
}
