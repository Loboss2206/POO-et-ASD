package fr.epu.vehicles;

import fr.epu.tracking.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DroneTest {
    Drone d;
    @BeforeEach
    void setUp() {
        d = new Drone(30,new Position(2,3));
    }

    @org.junit.jupiter.api.Test
    void testInitialiseVE() {
        assertEquals(30, d.getBatteryCapacity());//verifie l’égalité
        assertEquals(0, d.getCurrentCharge());
    }

    @org.junit.jupiter.api.Test
    void testChargeValid() {
        assertTrue((d.charge(
                10)));
        assertEquals(10,d.getCurrentCharge());
        assertTrue((d.charge(
                20)));
        assertEquals(30,d.getCurrentCharge());
    }

    @org.junit.jupiter.api.Test
    void testChargeNotValid() {
        assertFalse((d.charge(
                100)));
        assertEquals(0,d.getCurrentCharge());
        assertTrue((d.charge(
                10)));
        assertEquals(10,d.getCurrentCharge());
        assertFalse((d.charge(
                21)));
        assertEquals(10,d.getCurrentCharge());
    }

    @org.junit.jupiter.api.Test
    void testCheckChargeParameter() {
        assertFalse((d.charge(-10)));
        assertEquals(0, d.getCurrentCharge());
    }

    @org.junit.jupiter.api.Test
    void testChargeWith0() {
        assertFalse((d.charge(0)));
        assertEquals(0, d.getCurrentCharge());
    }

    @Test
    void testChargeToFull() {
        double charge = d.chargeToFull();
        assertEquals(30, d.getCurrentCharge());
        assertEquals(30, charge);
        d = new Drone(30, new Position(6,4));
        d.charge(10);
        charge= d.chargeToFull();
        assertEquals(30, d.getCurrentCharge());
        assertEquals(20, charge);
    }

    @Test
    void connect() {
        assertFalse(d.isConnected());
        d.connect();
        assertTrue(d.isConnected());
        d.disconnect();
        assertFalse(d.isConnected());
    }

    @Test
    void disconnect() {
        assertFalse(d.isConnected());
        d.connect();
        assertTrue(d.isConnected());
    }

    @Test
    void percentageCharge() {
        assertEquals(d.percentageCharge(), 0);
        assertTrue(d.charge(10));
        assertEquals(d.percentageCharge(), 33);
        assertTrue(d.charge(10));
        assertEquals(d.percentageCharge(), 66);
        assertTrue(d.charge(10));
        assertEquals(d.percentageCharge(), 100);
    }

    @Test
    void distanceToEmptyForCharge() {
        assertEquals(d.distanceToEmptyForCharge(), 0);
        assertTrue(d.charge(10));
        assertEquals(d.distanceToEmptyForCharge(), 50);
        assertTrue(d.charge(10));
        assertEquals(d.distanceToEmptyForCharge(), 100);
    }

    @Test
    void distanceToEmptyForCapacity() {
        assertEquals(d.distanceToEmptyForCapacity(), 150);
    }

    @Test
    void drive() {
        assertEquals(d.getCurrentCharge(), 0);
        assertTrue(d.charge(10));
        assertTrue(d.drive(10));
        assertEquals(d.getCurrentCharge(), 8.0); // 10 - 10 * 0.2 = 8

    }

    @Test
    void testInitialiseDrone() {
        assertEquals(30, d.getBatteryCapacity());
        assertEquals(0, d.getCurrentCharge());
        assertEquals(0.2, d.getEnergyConsumptionPerKilometer());
    }
}