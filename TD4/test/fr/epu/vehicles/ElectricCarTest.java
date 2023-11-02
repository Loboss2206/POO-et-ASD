package fr.epu.vehicles;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class ElectricCarTest extends ElectricVehicleTest {
    ElectricCar car;
    final int batteryCapacity = 30;

    @BeforeEach
    void setUp() {
        car = new ElectricCar(batteryCapacity, "LG-654-AL", "Tesla Model S");
        ev = car;
    }

    @org.junit.jupiter.api.Test
    void testInitialise() {
        assertEquals(0, car.getCurrentCharge());
        assertEquals("LG-654-AL", car.getLicensePlate());
        assertEquals("Tesla Model S", car.getModel());
        assertEquals(0.2, car.getEnergyConsumptionPerKilometer());
        assertEquals(150, car.calculateMaxRange());
        car.turnOnCoolingSystem();
        assertEquals(0.2 * 1.2, car.getEnergyConsumptionPerKilometer());
        assertEquals(125, car.calculateMaxRange());
        car.turnOffCoolingSystem();
        assertEquals(0.2, car.getEnergyConsumptionPerKilometer());
        assertEquals(150, car.calculateMaxRange());
    }

    @org.junit.jupiter.api.Test
    void turnOnCoolingSystem() {
        assertFalse(car.isOnCoolingSystem());
        car.turnOnCoolingSystem();
        assertTrue(car.isOnCoolingSystem());
    }

    @org.junit.jupiter.api.Test
    public void turnOffCoolingSystem() {
        assertFalse(car.isOnCoolingSystem());
        car.turnOnCoolingSystem();
        assertTrue(car.isOnCoolingSystem());
        car.turnOffCoolingSystem();
        assertFalse(car.isOnCoolingSystem());
    }

    final String licensePlate = "AB-123-CD";
    final String brand = "Tesla";
    final String model = "Model S";

    @org.junit.jupiter.api.Test
    void testMatch() {
        car = new ElectricCar(batteryCapacity, licensePlate, brand + " " + model);
        assertTrue(car.match("AB-123-CD"));
        assertFalse(car.match("AB-123-CE"));
        assertTrue(car.match("123"));
        assertTrue(car.match("Tesla"));
        assertTrue(car.match("S"));
    }
}
