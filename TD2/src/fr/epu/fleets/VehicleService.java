package fr.epu.fleets;

import fr.epu.vehicles.ElectricVehicle;

public class VehicleService {
    private ElectricVehicle[] availableVehicles;
    private ElectricVehicle[] vehiclesInRepair;

    public VehicleService(int size) {
        availableVehicles = new ElectricVehicle[size];
        vehiclesInRepair = new ElectricVehicle[size];
    }

    public void addAvailableVehicle(ElectricVehicle vehicle) {
        for (int i = 0; i < availableVehicles.length; i++) {
            if (availableVehicles[i] == null) {
                availableVehicles[i] = vehicle;
                break;
            }
        }
    }

    public void addVehicleInRepair(ElectricVehicle vehicle) {
        for (int i = 0; i < vehiclesInRepair.length; i++) {
            if (vehiclesInRepair[i] == null) {
                vehiclesInRepair[i] = vehicle;
                break;
            }
        }
    }

    public void moveVehicleToRepair(ElectricVehicle vehicle) {
        for (int i = 0; i < availableVehicles.length; i++) {
            if (availableVehicles[i] == vehicle) {
                availableVehicles[i] = null;
                addVehicleInRepair(vehicle);
                break;
            }
        }
    }

    public void moveVehicleToAvailable(ElectricVehicle vehicle) {
        for (int i = 0; i < vehiclesInRepair.length; i++) {
            if (vehiclesInRepair[i] == vehicle) {
                vehiclesInRepair[i] = null;
                addAvailableVehicle(vehicle);
                break;
            }
        }
    }

    public ElectricVehicle findAvailableVehicleWithMaxDistance() {
        ElectricVehicle maxDistanceVehicle = null;
        for (int i = 0; i < availableVehicles.length; i++) {
            if (availableVehicles[i] != null) {
                if (maxDistanceVehicle == null) maxDistanceVehicle = availableVehicles[i];
                else if (availableVehicles[i].calculateMaxRange() > maxDistanceVehicle.calculateMaxRange()) {
                    maxDistanceVehicle = availableVehicles[i];
                }
            }
        }
        return maxDistanceVehicle;
    }

    public ElectricVehicle findVehicleWithMaxRangeInAvailableAndInRepair() {
        ElectricVehicle maxDistanceVehicle = null;
        for (int i = 0; i < availableVehicles.length; i++) {
            if (availableVehicles[i] != null) {
                if (maxDistanceVehicle == null) maxDistanceVehicle = availableVehicles[i];
                else if (availableVehicles[i].getBatteryCapacity() > maxDistanceVehicle.getBatteryCapacity()) {
                    maxDistanceVehicle = availableVehicles[i];
                }
            }
        }
        for (int i = 0; i < vehiclesInRepair.length; i++) {
            if (vehiclesInRepair[i] != null) {
                if (maxDistanceVehicle == null) maxDistanceVehicle = vehiclesInRepair[i];
                else if (vehiclesInRepair[i].getBatteryCapacity() > maxDistanceVehicle.getBatteryCapacity()) {
                    maxDistanceVehicle = vehiclesInRepair[i];
                }
            }
        }
        return maxDistanceVehicle;
    }

    public int getNbOfVehiclesInRepair() {
        int nbOfVehiclesInRepair = 0;
        for (int i = 0; i < vehiclesInRepair.length; i++) {
            if (vehiclesInRepair[i] != null) nbOfVehiclesInRepair++;
        }
        return nbOfVehiclesInRepair;
    }

    public int getNbOfAvailableVehiclesInCharge() {
        int nbOfAvailableVehiclesInCharge = 0;
        for (int i = 0; i < availableVehicles.length; i++) {
            if (availableVehicles[i] != null && availableVehicles[i].isConnected()) nbOfAvailableVehiclesInCharge++;
        }
        return nbOfAvailableVehiclesInCharge;
    }

    public int getNbOfAvailableVehicles() {
        int nbOfAvailableVehicles = 0;
        for (int i = 0; i < availableVehicles.length; i++) {
            if (availableVehicles[i] != null) nbOfAvailableVehicles++;
        }
        return nbOfAvailableVehicles;
    }

    public ElectricVehicle[] getAvailableVehicles() {
        return availableVehicles;
    }

    public ElectricVehicle[] getVehiclesInRepair() {
        return vehiclesInRepair;
    }
}
