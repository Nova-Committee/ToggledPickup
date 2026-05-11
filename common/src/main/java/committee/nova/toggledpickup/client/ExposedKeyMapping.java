package committee.nova.toggledpickup.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;

public class ExposedKeyMapping extends KeyMapping {
    public ExposedKeyMapping(String name, InputConstants.Type type, int value, Category category, int order) {
        super(name, type, value, category, order);
    }

    public ExposedKeyMapping(String name, int keysym, KeyMapping.Category category) {
        this(name, InputConstants.Type.KEYSYM, keysym, category);
    }

    public ExposedKeyMapping(String name, InputConstants.Type type, int value, KeyMapping.Category category) {
        this(name, type, value, category, 0);
    }

    public InputConstants.Key getKey() {
        return this.key;
    }
}
