package fr.epu.fleets;

import fr.epu.vehicles.ElectricVehicle;

import java.util.ArrayList;

public class FleetOfVehicles<T extends ElectricVehicle> extends ArrayList<T> {
    public double calculateTotalMaxRange() {
        double totalMaxRange = 0;
        for (T vehicle : this) {
            totalMaxRange += vehicle.calculateMaxRange();
        }
        return totalMaxRange;
    }

    public double calculateAverageMaxRange () {
        if (size() == 0) return 0;
        return calculateTotalMaxRange() / size();
    }

    public void chargeFullAllVehicles() {
        for (T vehicle : this) {
            vehicle.chargeToFull();
        }
    }

    public double calculateMinDistance() {
        double minDistance = Double.MAX_VALUE;
        for (T vehicle : this) {
            if (vehicle.calculateMaxRange() < minDistance) {
                minDistance = vehicle.calculateMaxRange();
            }
        }
        return minDistance;
    }

    public double calculateMaxDistance() {
        double maxDistance = Double.MIN_VALUE;
        for (T vehicle : this) {
            if (vehicle.calculateMaxRange() > maxDistance) {
                maxDistance = vehicle.calculateMaxRange();
            }
        }
        return maxDistance;
    }

    public void chargeVehiclesBelowPercentage(double percentage) {
        for (T vehicle : this) {
            if (vehicle.getCurrentCharge() / vehicle.getBatteryCapacity() < percentage) {
                vehicle.chargeToFull();
            }
        }
    }

    public double driveVehicles(double distance) {
        double totalDistance = 0;
        for (T vehicle : this) {
            if (vehicle.calculateMaxRange() > distance) {
                vehicle.drive(distance);
                totalDistance += distance;
            }
        }
        return totalDistance;
    }

    public ElectricVehicle findVehicleWithMaxRange() {
        ElectricVehicle maxRangeVehicle = null;
        for (T vehicle : this) {
            if (maxRangeVehicle == null) maxRangeVehicle = vehicle;
            else if (vehicle.calculateMaxRange() > maxRangeVehicle.calculateMaxRange()) {
                maxRangeVehicle = vehicle;
            }
        }
        return maxRangeVehicle;
    }
}
