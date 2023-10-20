package fr.epu.vehicles;

import fr.epu.rentals.RentableItem;

public class ElectricCar extends ElectricVehicle implements RentableItem {
    private boolean coolingSystemActive = false;
    private String licensePlate;
    private String model;
    private final double COOLING_SYSTEM_FACTOR = 1.2;
    private boolean isAvailable;

    public ElectricCar(double batteryCapacity, String licensePlate) {
        super(batteryCapacity);
        this.licensePlate = licensePlate;
        this.model = "undefined";
        this.isAvailable = true;
    }

    public ElectricCar(double batteryCapacity, String licensePlate, String model) {
        super(batteryCapacity);
        this.licensePlate = licensePlate;
        this.model = model;
        this.isAvailable = true;
    }

    public void turnOnCoolingSystem() {
        coolingSystemActive = true;
    }

    public void turnOffCoolingSystem() {
        coolingSystemActive = false;
    }

    public boolean isOnCoolingSystem() {
        return coolingSystemActive;
    }

    @Override
    public double getEnergyConsumptionPerKilometer() {
        if (coolingSystemActive) return super.getEnergyConsumptionPerKilometer() * COOLING_SYSTEM_FACTOR;
        else return super.getEnergyConsumptionPerKilometer();
    }

    @Override
    public int calculateMaxRange() {
        return (int) (getBatteryCapacity() / getEnergyConsumptionPerKilometer());
    }

    public void setCoolingSystemActive(boolean coolingSystemActive) {
        this.coolingSystemActive = coolingSystemActive;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable() {
        this.isAvailable = true;
    }

    public void setUnavailable() {
        this.isAvailable = false;
    }

    @Override
    public String getName() {
        return licensePlate;
    }

    @Override
    public boolean match(String description) {
        return this.model.contains(description) || licensePlate.contains(description);
    }
}
