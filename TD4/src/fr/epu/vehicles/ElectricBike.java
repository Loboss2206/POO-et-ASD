package fr.epu.vehicles;

import fr.epu.rentals.RentableItem;

public class ElectricBike extends ElectricVehicle implements RentableItem {
    private int pedalAssistLevel = 0;
    private int numberOfAvailableLevels;
    private double[] energyConsumptionLevels;
    private boolean isAvailable;
    private String id;
    private static int nextId = 1;

    public ElectricBike(double batteryCapacity, double[] energyConsumptionLevels) {
        super(batteryCapacity);
        this.energyConsumptionLevels = energyConsumptionLevels;
        this.numberOfAvailableLevels = energyConsumptionLevels.length;
        this.isAvailable = true;
        id = "EB-" + nextId;
        nextId++;
    }

    public int getPedalAssistLevel() {
        return pedalAssistLevel;
    }

    public void setPedalAssistLevel(int pedalAssistLevel) {
        if (pedalAssistLevel > numberOfAvailableLevels) this.pedalAssistLevel = numberOfAvailableLevels;
        else if (pedalAssistLevel >= 0) this.pedalAssistLevel = pedalAssistLevel;
    }

    public double getEnergyConsumptionForAssistLevel(int pedalAssistLevel) {
        return energyConsumptionLevels[pedalAssistLevel];
    }

    public int getNumberOfAvailableLevels() {
        return numberOfAvailableLevels;
    }

    public int calculateMaxRange() {
        return (int) (getBatteryCapacity() / getEnergyConsumptionForAssistLevel(getPedalAssistLevel()));
    }

    @Override
    public double getEnergyConsumptionPerKilometer() {
        return getEnergyConsumptionForAssistLevel(pedalAssistLevel);
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
        return id;
    }

    @Override
    public boolean match(String description) {
        return (description.contains(this.getName()) || description.contains(getNumberOfAvailableLevels() + " Level") || description.contains("*"));
    }

    protected static void resetIdentifier() {
        nextId = 1;
    }
}
