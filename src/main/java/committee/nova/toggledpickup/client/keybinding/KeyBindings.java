package committee.nova.toggledpickup.client.keybinding;

import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyBindings {
    public static final KeyBinding toggleAutoPickup = new KeyBinding(
            "key.toggledpickup.toggle",
            Keyboard.KEY_GRAVE,
            "category.toggledpickup"
    );

    public static final KeyBinding manuallyPickup = new KeyBinding(
            "key.toggledpickup.manually",
            Keyboard.KEY_NONE,
            "category.toggledpickup"
    );
}
