package committee.nova.toggledpickup.mixin;

import committee.nova.toggledpickup.api.ExtendedGui;
import committee.nova.toggledpickup.client.KeyMappings;
import committee.nova.toggledpickup.network.handler.NetworkHandler;
import committee.nova.toggledpickup.network.msg.ManuallyMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class MixinLocalPlayer {

    @Inject(method = "tick", at = @At("TAIL"))
    private void inject$tick(CallbackInfo ci) {
        final boolean manually = KeyMappings.manuallyPickup.isDown();
        NetworkHandler.MANUALLY.send(PacketDistributor.SERVER.noArg(), new ManuallyMessage(manually));
        if (manually) ((ExtendedGui) Minecraft.getInstance().gui).toggledPickup$setOverlayMessage(
                new TranslationTextComponent("msg.toggledpickup.status.manually").withStyle(TextFormatting.AQUA),
                20
        );
    }
}
