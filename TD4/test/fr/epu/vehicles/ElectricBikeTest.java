package fr.epu.vehicles;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElectricBikeTest extends ElectricVehicleTest {
    ElectricBike bike;
    final int batteryCapacity = 30;

    @BeforeEach
    void setUp() {
        bike = new ElectricBike(batteryCapacity, new double[]{0.2, 0.5, 0.8});
        ev = bike;
        ElectricBike.resetIdentifier();
    }

    @org.junit.jupiter.api.Test
    void testInitialise() {
        assertEquals(0, bike.getCurrentCharge());
        assertEquals(0, bike.getPedalAssistLevel());
    }

    @org.junit.jupiter.api.Test
    void testMaxRangeOfBike() {
        bike = new ElectricBike(batteryCapacity, new double[]{0.2, 0.5, 0.8});
        assertEquals(0, bike.getCurrentCharge());
        assertEquals(0, bike.getPedalAssistLevel());
        assertEquals(3, bike.getNumberOfAvailableLevels());
        assertEquals(0.2, bike.getEnergyConsumptionPerKilometer());
        assertEquals(0.5, bike.getEnergyConsumptionForAssistLevel(1));
        assertEquals(0.8, bike.getEnergyConsumptionForAssistLevel(2));
        assertEquals(150, bike.calculateMaxRange());
        bike.setPedalAssistLevel(1);
        assertEquals(1, bike.getPedalAssistLevel());
    }

    @org.junit.jupiter.api.Test
    void testGetEnergyCunsumptionPerKilometer() {
        assertEquals(0, bike.getPedalAssistLevel());
        assertEquals(0.2, bike.getEnergyConsumptionPerKilometer());
        assertEquals(150, bike.calculateMaxRange());
        bike.setPedalAssistLevel(1);
        assertEquals(1, bike.getPedalAssistLevel());
        assertEquals(0.5, bike.getEnergyConsumptionPerKilometer());
        assertEquals(batteryCapacity / 0.5, bike.calculateMaxRange());
    }

    final double[] energyConsumptionLevels = new double[]{0.2, 0.5, 0.8};
    @org.junit.jupiter.api.Test
    void testIdentifiers() {
        bike = new ElectricBike(batteryCapacity, energyConsumptionLevels);
        assertEquals("EB-1", bike.getName());
        ElectricBike bike2 = new ElectricBike(batteryCapacity, energyConsumptionLevels);
        assertEquals("EB-2", bike2.getName());
        ElectricBike bike3 = new ElectricBike(batteryCapacity, energyConsumptionLevels);
        assertEquals("EB-3", bike3.getName());
        assertEquals("EB-1", bike.getName());
    }
}
