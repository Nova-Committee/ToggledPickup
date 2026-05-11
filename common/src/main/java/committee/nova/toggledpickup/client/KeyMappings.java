package committee.nova.toggledpickup.client;

import com.mojang.blaze3d.platform.InputConstants;
import committee.nova.toggledpickup.Constants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.apache.logging.log4j.util.Lazy;

public class KeyMappings {

    public static final KeyMapping.Category TOGGLED_PICKUP_CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(Constants.MOD_ID, "main"));

    public static final Lazy<ExposedKeyMapping> toggleAutoPickup = Lazy.lazy(() -> new ExposedKeyMapping(
            "key.toggledpickup.toggle",
            InputConstants.Type.KEYSYM, InputConstants.KEY_GRAVE,
            TOGGLED_PICKUP_CATEGORY
    ));

    public static final Lazy<ExposedKeyMapping> manuallyPickup = Lazy.lazy(() -> new ExposedKeyMapping(
            "key.toggledpickup.manually",
            InputConstants.Type.KEYSYM, -1,
            TOGGLED_PICKUP_CATEGORY
    ));

    void test() {

    }
}
