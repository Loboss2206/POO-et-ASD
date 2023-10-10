package fr.epu.vehicles;

public class ElectricBike extends ElectricVehicle {
    private int pedalAssistLevel = 0;
    private int numberOfAvailableLevels;
    private double[] energyConsumptionLevels;

    public ElectricBike(double batteryCapacity, double[] energyConsumptionLevels) {
        super(batteryCapacity);
        this.energyConsumptionLevels = energyConsumptionLevels;
        this.numberOfAvailableLevels = energyConsumptionLevels.length;
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
}
