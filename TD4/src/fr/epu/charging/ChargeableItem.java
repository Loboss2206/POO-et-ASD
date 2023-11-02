package fr.epu.charging;

import fr.epu.vehicles.ElectricVehicle;

public interface ChargeableItem {
    public double connectToChargingPoint(ChargingStation cs);
    public void disconnectFromChargingPoint(ChargingStation cs);
}