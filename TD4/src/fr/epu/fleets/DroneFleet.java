package fr.epu.fleets;

import fr.epu.tracking.Position;
import fr.epu.tracking.TrackingSystem;
import fr.epu.vehicles.Drone;

import java.util.ArrayList;
import java.util.Optional;

public class DroneFleet extends FleetOfVehicles<Drone> {
    TrackingSystem trackingSystem;

    public DroneFleet(TrackingSystem trackingSystem) {
        this.trackingSystem = trackingSystem;
    }

    public void addDrone(Drone drone) {
        add(drone);
        trackingSystem.addTrackableObject(drone);
    }

    public ArrayList<Position> getAllDronesPositions() {
        return trackingSystem.getAllTrackablePositions();
    }

    public Optional<Position> findPositionOfDroneWithLongestDistance() {
        Optional<Position> positionOfDroneWithLongestDistance = Optional.empty();
        double maxDistance = Double.MIN_VALUE;
        for (Drone drone : this) {
            if (drone.calculateMaxRange() > maxDistance) {
                maxDistance = drone.calculateMaxRange();
                positionOfDroneWithLongestDistance = Optional.of(trackingSystem.getTrackableObjectPosition(drone));
            }
        }
        return positionOfDroneWithLongestDistance;
    }

    public void takeOffDronesWithRange(double range) {
        for (Drone drone : this) {
            if (drone.calculateMaxRange() > range) {
                drone.takeOff();
            }
        }
    }

    public void returnDronesWithLowBattery(double returnDistance) {
        for (Drone drone : this) {
            if (drone.calculateMaxRange() < returnDistance) {
                drone.returnToBase();
            }
        }
    }

    public void returnAllDrones() {
        for (Drone drone : this) {
            drone.returnToBase();
        }
    }

    public void takeOffAllDrones() {
        for (Drone drone : this) {
            drone.takeOff();
        }
    }
}
