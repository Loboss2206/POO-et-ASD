package fr.epu.fleets;

import fr.epu.tracking.Position;
import fr.epu.tracking.TrackingSystem;
import fr.epu.vehicles.Drone;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DroneFleetTest {

    @Test
    void addDrone() {
        DroneFleet droneFleet = new DroneFleet(new TrackingSystem());
        assertEquals(0, droneFleet.size());
        droneFleet.addDrone(new Drone(30, new Position(0, 0)));
        assertEquals(1, droneFleet.size());
        droneFleet.addDrone(new Drone(40, new Position(0, 0)));
        assertEquals(2, droneFleet.size());
    }

    @Test
    void getAllDronesPositions() {
        DroneFleet droneFleet = new DroneFleet(new TrackingSystem());
        assertEquals(new ArrayList<>(), droneFleet.getAllDronesPositions());
        droneFleet.addDrone(new Drone(30, new Position(2, 0)));
        assertEquals(droneFleet.getAllDronesPositions().get(0).getX(), new Position(2, 0).getX());
        assertEquals(droneFleet.getAllDronesPositions().get(0).getY(), new Position(2, 0).getY());
        droneFleet.addDrone(new Drone(40, new Position(0, 4)));
        assertEquals(droneFleet.getAllDronesPositions().get(1).getX(), new Position(0, 4).getX());
        assertEquals(droneFleet.getAllDronesPositions().get(1).getY(), new Position(0, 4).getY());
    }

    @Test
    void takeOffDronesWithRange() {
        DroneFleet droneFleet = new DroneFleet(new TrackingSystem());
        droneFleet.addDrone(new Drone(30, new Position(2, 0)));
        droneFleet.addDrone(new Drone(40, new Position(0, 4)));
        droneFleet.takeOffDronesWithRange(140);
        assertTrue(droneFleet.get(0).isFlying());
        assertTrue(droneFleet.get(1).isFlying());
        droneFleet.returnAllDrones();
        droneFleet.takeOffDronesWithRange(190);
        assertFalse(droneFleet.get(0).isFlying());
        assertTrue(droneFleet.get(1).isFlying());
    }

    @Test
    void returnDronesWithLowBattery() {
        DroneFleet droneFleet = new DroneFleet(new TrackingSystem());
        droneFleet.addDrone(new Drone(30, new Position(2, 0)));
        droneFleet.addDrone(new Drone(40, new Position(0, 4)));
        droneFleet.takeOffAllDrones();
        assertTrue(droneFleet.get(0).isFlying());
        assertTrue(droneFleet.get(1).isFlying());
        droneFleet.returnDronesWithLowBattery(140);
        assertTrue(droneFleet.get(0).isFlying());
        assertTrue(droneFleet.get(1).isFlying());
        droneFleet.returnDronesWithLowBattery(190);
        assertFalse(droneFleet.get(0).isFlying());
        assertTrue(droneFleet.get(1).isFlying());
    }
}