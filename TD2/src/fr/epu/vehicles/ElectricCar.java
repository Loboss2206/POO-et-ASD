package fr.epu.vehicles;

public class ElectricCar extends ElectricVehicle {
    private boolean coolingSystemActive = false;
    private String licensePlate;
    private String model;
    private final double COOLING_SYSTEM_FACTOR = 1.2;

    public ElectricCar(double batteryCapacity, String licensePlate) {
        super(batteryCapacity);
        this.licensePlate = licensePlate;
        this.model = "undefined";
    }

    public ElectricCar(double batteryCapacity, String licensePlate, String model) {
        super(batteryCapacity);
        this.licensePlate = licensePlate;
        this.model = model;
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
}
