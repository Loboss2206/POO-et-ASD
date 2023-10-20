package fr.epu.tracking;

import fr.epu.vehicles.Drone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrackingSystemTest {
    TrackingSystem ts;

    @BeforeEach
    public void setUp() {
        ts = new TrackingSystem();
    }

    @Test
    public void testAddTrackableObject() {
        assertEquals(0, ts.getNumberOfTrackedObjects());
        Drone d = new Drone(30, new Position(2, 7));
        ts.addTrackableObject(d);
        assertEquals(1, ts.getNumberOfTrackedObjects());
    }

    @Test
    public void testGetTrackableObjectPosition() {
        Drone d = new Drone(30, new Position(2, 4));
        ts.addTrackableObject(d);
        assertEquals(d.getPosition(), ts.getTrackableObjectPosition(0));
    }

    @Test
    public void testGetAllTrackablePositions() {
        Drone d = new Drone(30, new Position(4, 4));
        Drone d2 = new Drone(30, new Position(2, 2));
        ts.addTrackableObject(d);
        ts.addTrackableObject(d2);
        assertEquals(2, ts.getAllTrackablePositions().size());
    }

    @Test
    public void testGetNumberOfTrackedObjects() {
        Drone d = new Drone(30, new Position(0, 4));
        Drone d2 = new Drone(30, new Position(2, 2));
        ts.addTrackableObject(d);
        ts.addTrackableObject(d2);
        assertEquals(2, ts.getNumberOfTrackedObjects());
    }
}
