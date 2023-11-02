package fr.epu.vehicles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ElectricVehicleTest {
    ElectricVehicle ev;
    @BeforeEach
    void setUp() {
        ev = new ElectricVehicle(30);
    }

    @org.junit.jupiter.api.Test
    void testInitialiseVE() {
        assertEquals(30, ev.getBatteryCapacity());//verifie l’égalité
        assertEquals(0, ev.getCurrentCharge());
    }

    @org.junit.jupiter.api.Test
    void testChargeValid() {
        assertTrue((ev.charge(
                10)));
        assertEquals(10,ev.getCurrentCharge());
        assertTrue((ev.charge(
                20)));
        assertEquals(30,ev.getCurrentCharge());
    }

    @org.junit.jupiter.api.Test
    void testChargeNotValid() {
        assertFalse((ev.charge(
                100)));
        assertEquals(0,ev.getCurrentCharge());
        assertTrue((ev.charge(
                10)));
        assertEquals(10,ev.getCurrentCharge());
        assertFalse((ev.charge(
                21)));
        assertEquals(10,ev.getCurrentCharge());
    }

    @org.junit.jupiter.api.Test
    void testCheckChargeParameter() {
        assertThrows(IllegalArgumentException.class, () -> ev.charge(-10));
        assertEquals(0, ev.getCurrentCharge());
    }

    @org.junit.jupiter.api.Test
    void testChargeWith0() {
        assertThrows(IllegalArgumentException.class, () -> ev.charge(0));
        assertEquals(0, ev.getCurrentCharge());
    }

    @Test
    void testChargeToFull() {
        double charge = ev.chargeToFull();
        assertEquals(30, ev.getCurrentCharge());
        assertEquals(30, charge);
        ev = new ElectricVehicle(30);
        ev.charge(10);
        charge= ev.chargeToFull();
        assertEquals(30, ev.getCurrentCharge());
        assertEquals(20, charge);
    }

    @Test
    void connect() {
        assertFalse(ev.isConnected());
        ev.connect();
        assertTrue(ev.isConnected());
        ev.disconnect();
        assertFalse(ev.isConnected());
    }

    @Test
    void disconnect() {
        assertFalse(ev.isConnected());
        ev.connect();
        assertTrue(ev.isConnected());
    }

    @Test
    void percentageCharge() {
        assertEquals(ev.percentageCharge(), 0);
        assertTrue(ev.charge(10));
        assertEquals(ev.percentageCharge(), 33);
        assertTrue(ev.charge(10));
        assertEquals(ev.percentageCharge(), 66);
        assertTrue(ev.charge(10));
        assertEquals(ev.percentageCharge(), 100);
    }

    @Test
    void distanceToEmptyForCharge() {
        assertEquals(ev.distanceToEmptyForCharge(), 0);
        assertTrue(ev.charge(10));
        assertEquals(ev.distanceToEmptyForCharge(), 50);
        assertTrue(ev.charge(10));
        assertEquals(ev.distanceToEmptyForCharge(), 100);
    }

    @Test
    void distanceToEmptyForCapacity() {
        assertEquals(ev.distanceToEmptyForCapacity(), 150);
    }

    @Test
    void drive() {
        assertEquals(ev.getCurrentCharge(), 0);
        assertTrue(ev.charge(10));
        assertTrue(ev.drive(10));
        assertEquals(ev.getCurrentCharge(), 8.0); // 10 - 10 * 0.2 = 8

    }
}