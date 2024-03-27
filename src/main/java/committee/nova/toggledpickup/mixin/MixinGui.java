package committee.nova.toggledpickup.mixin;

import committee.nova.toggledpickup.api.ExtendedGui;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;

@Mixin(IngameGui.class)
public abstract class MixinGui implements ExtendedGui {

    @Shadow
    protected int overlayMessageTime;

    @Shadow
    protected boolean animateOverlayMessageColor;

    @Shadow
    @Nullable
    protected ITextComponent overlayMessageString;

    @Override
    public void toggledPickup$setOverlayMessage(ITextComponent msg, int remainTime) {
        this.overlayMessageString = msg;
        this.overlayMessageTime = Math.max(20, remainTime);
        this.animateOverlayMessageColor = false;
    }
}
