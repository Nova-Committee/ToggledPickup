package committee.nova.toggledpickup.api;

public interface ExtendedEntityPlayerMP {
    boolean toggledPickup$isAutoPickup();

    void toggledPickup$setAutoPickup(boolean autoPickup);

    boolean toggledpickup$isManuallyPickingUp();

    void toggledpickup$setManuallyPickingUp(boolean manually);
}
