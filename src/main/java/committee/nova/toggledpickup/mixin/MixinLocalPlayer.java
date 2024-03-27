package committee.nova.toggledpickup.mixin;

import committee.nova.toggledpickup.api.ExtendedGui;
import committee.nova.toggledpickup.client.KeyMappings;
import committee.nova.toggledpickup.network.handler.NetworkHandler;
import committee.nova.toggledpickup.network.msg.ManuallyMessage;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class MixinLocalPlayer {

    @Inject(method = "tick", at = @At("TAIL"))
    private void inject$tick(CallbackInfo ci) {
        final boolean manually = KeyMappings.manuallyPickup.isDown();
        NetworkHandler.MANUALLY.send(new ManuallyMessage(manually), PacketDistributor.SERVER.noArg());
        if (manually) ((ExtendedGui) Minecraft.getInstance().gui).toggledPickup$setOverlayMessage(
                Component.translatable("msg.toggledpickup.status.manually").withStyle(ChatFormatting.AQUA),
                20
        );
    }
}
