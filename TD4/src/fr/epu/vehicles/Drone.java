package fr.epu.vehicles;

import fr.epu.tracking.Position;
import fr.epu.tracking.Trackable;

public class Drone extends ElectricVehicle implements Trackable {
    private boolean isFlying = false;
    private Position position;

    public Drone(double batteryCapacity, double energyConsumptionPerKilometer, Position position) {
        super(batteryCapacity, energyConsumptionPerKilometer);
        this.position = position;
    }

    public Drone(double batteryCapacity, Position position) {
        super(batteryCapacity);
        this.position = position;
    }

    public void takeOff() {
        isFlying = true;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void returnToBase() {
        isFlying = false;
    }

    public boolean isFlying() {
        return isFlying;
    }
}
