package fr.epu.vehicles;

public class Drone extends ElectricVehicle {
    public Drone(double batteryCapacity, double energyConsumptionPerKilometer) {
        super(batteryCapacity, energyConsumptionPerKilometer);
    }

    public Drone(double batteryCapacity) {
        super(batteryCapacity);
    }
}
