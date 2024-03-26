package committee.nova.toggledpickup.mixin;

import committee.nova.toggledpickup.api.ExtendedGui;
import committee.nova.toggledpickup.client.ToggledPickupClient;
import committee.nova.toggledpickup.common.ToggledPickup;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class MixinLocalPlayer {

    @Inject(method = "tick", at = @At("TAIL"))
    private void inject$tick(CallbackInfo ci) {
        final FriendlyByteBuf buf = PacketByteBufs.create();
        final boolean manually = ToggledPickupClient.manuallyPickup.isDown();
        buf.writeBoolean(manually);
        ClientPlayNetworking.send(ToggledPickup.MANUALLY, buf);
        if (manually) ((ExtendedGui) Minecraft.getInstance().gui).toggledPickup$setOverlayMessage(
                Component.translatable("msg.toggledpickup.status.manually").withStyle(ChatFormatting.AQUA),
                20
        );
    }
}
