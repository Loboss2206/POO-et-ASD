package fr.epu.vehicles;

/**
 * @author Loboss2206
 * This class represents an electric vehicle.
 */
public class ElectricVehicle {
    private double batteryCapacity;
    private double currentCharge;
    private double energyConsumptionPerKilometer;
    private boolean isConnected;

    /**
     * This constructor initialises the battery capacity and the current charge to 0.
     */
    public ElectricVehicle(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
        this.currentCharge = 0;
        this.energyConsumptionPerKilometer = 0.2;
    }

    /**
     * This constructor initialises the battery capacity and the current charge to 0.
     */
    public ElectricVehicle(double batteryCapacity, double energyConsumptionPerKilometer) {
        this.batteryCapacity = batteryCapacity;
        this.currentCharge = 0;
        this.energyConsumptionPerKilometer = energyConsumptionPerKilometer;
    }

    /**
     * This method charges the vehicle with the given amount of energy.
     * @param chargeAmount the amount of energy to charge the vehicle with
     * @return true if the charge is successful, false otherwise
     */
    public boolean charge(double chargeAmount) {
        if (chargeAmount + currentCharge > batteryCapacity) {
            return false;
        }
        else if (chargeAmount <= 0) {
            return false;
        } else {
            currentCharge += chargeAmount;
            return true;
        }
    }

    /**
     * This method charges the vehicle to full.
     * @return the amount of energy charged
     */
    public double chargeToFull() {
        double chargeAmount = batteryCapacity - currentCharge;
        currentCharge = batteryCapacity;
        return chargeAmount;
    }

    /**
     * This method connect the vehicle to a charging point or not depending on isConnected.
     */
    public boolean connect() {
        if (isConnected) return false;
        else {
            isConnected = true;
            return true;
        }
    }

    /**
     * This method disconnect the vehicle to a charging point or not depending on isConnected.
     */
    public boolean disconnect() {
        if (!isConnected) return false;
        else {
            isConnected = false;
            return true;
        }
    }

    /**
     * This method returns the percentage of charge of the vehicle.
     * @return the percentage of charge of the vehicle
     */
    public int percentageCharge() {
        return (int) (currentCharge / batteryCapacity * 100);
    }

    /**
     * This method returns the distance the vehicle can travel with the current charge.
     * @return the distance the vehicle can travel with the current charge
     */
    public double distanceToEmptyForCharge() {
        return currentCharge / energyConsumptionPerKilometer;
    }

    /**
     * This method returns the distance the vehicle can travel with a full battery.
     * @return the distance the vehicle can travel with a full battery
     */
    public double distanceToEmptyForCapacity() {
        return batteryCapacity / energyConsumptionPerKilometer;
    }

    public boolean drive(double distance) {
        double energyConsumption = distance * getEnergyConsumptionPerKilometer();
        if (energyConsumption <= currentCharge) {
            currentCharge -= energyConsumption;
            return true;
        } else {
            return false;
        }
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public double getCurrentCharge() {
        return currentCharge;
    }

    public double getEnergyConsumptionPerKilometer() {
        return energyConsumptionPerKilometer;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}
