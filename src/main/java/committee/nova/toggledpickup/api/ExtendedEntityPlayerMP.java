package committee.nova.toggledpickup.api;

public interface ExtendedEntityPlayerMP {
    boolean toggledPickup$isAutoPickup();

    void toggledPickup$setAutoPickup(boolean autoPickup, boolean notify);

    default void toggledPickup$setAutoPickup(boolean autoPickup) {
        toggledPickup$setAutoPickup(autoPickup, true);
    }

    boolean toggledpickup$isManuallyPickingUp();

    void toggledpickup$setManuallyPickingUp(boolean manually);
}
