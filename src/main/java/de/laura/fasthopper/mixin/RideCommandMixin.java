package de.laura.fasthopper.mixin;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.RideCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RideCommand.class)
public class RideCommandMixin {
    /**
     * @author e
     * @reason e
     */
    @Overwrite
    private static int executeMount(ServerCommandSource source, Entity rider, Entity vehicle) {
        rider.startRiding(vehicle, true);
        source.sendFeedback(() -> {
            return Text.translatable("commands.ride.mount.success", rider.getDisplayName(), vehicle.getDisplayName());
        }, true);
        return 1;
    }
}
