package committee.nova.toggledpickup.mixin;

import committee.nova.toggledpickup.api.ExtendedGui;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Gui.class)
public abstract class MixinGui implements ExtendedGui {

    @Shadow
    public abstract void setChatDisabledByPlayerShown(boolean bl);

    @Shadow
    private @Nullable Component overlayMessageString;

    @Shadow
    private int overlayMessageTime;

    @Shadow
    private boolean animateOverlayMessageColor;

    @Override
    public void toggledPickup$setOverlayMessage(Component msg, int remainTime) {
        this.setChatDisabledByPlayerShown(false);
        this.overlayMessageString = msg;
        this.overlayMessageTime = Math.max(20, remainTime);
        this.animateOverlayMessageColor = false;
    }
}
