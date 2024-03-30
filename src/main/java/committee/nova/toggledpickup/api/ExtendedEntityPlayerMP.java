package committee.nova.toggledpickup.api;

public interface ExtendedEntityPlayerMP {
    boolean toggledPickup$isAutoPickup();

    default void toggledPickup$setAutoPickup(boolean autoPickup) {
        toggledPickup$setAutoPickup(autoPickup, true);
    }

    void toggledPickup$setAutoPickup(boolean autoPickup, boolean notify);

    boolean toggledpickup$isManuallyPickingUp();

    void toggledpickup$setManuallyPickingUp(boolean manually);
}
