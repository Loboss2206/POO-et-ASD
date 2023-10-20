package fr.epu.fleets;

import fr.epu.vehicles.ElectricBike;
import fr.epu.vehicles.ElectricCar;
import fr.epu.vehicles.ElectricVehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleServiceTest {
    VehicleService vs;

    @BeforeEach
    void setUp() {
        vs = new VehicleService(10);
    }

    @Test
    void addAvailableVehicle() {
        ElectricCar car = new ElectricCar(30, "LG-654-AL", "Tesla Model S");
        vs.addAvailableVehicle(car);
        assertEquals(vs.getNbOfAvailableVehicles(), 1);
        assertEquals(vs.getAvailableVehicles()[0], car);
    }

    @Test
    void addVehicleInRepair() {
        ElectricCar car = new ElectricCar(30, "LG-654-AL", "Tesla Model S");
        vs.addVehicleInRepair(car);
        assertEquals(vs.getNbOfVehiclesInRepair(), 1);
        assertEquals(vs.getVehiclesInRepair()[0], car);
    }

    @Test
    void moveVehicleToRepair() {
        ElectricCar car = new ElectricCar(30, "LG-654-AL", "Tesla Model S");
        vs.addAvailableVehicle(car);
        assertEquals(vs.getNbOfAvailableVehicles(), 1);
        assertEquals(vs.getAvailableVehicles()[0], car);
        vs.moveVehicleToRepair(car);
        assertEquals(vs.getNbOfAvailableVehicles(), 0);
        assertEquals(vs.getNbOfVehiclesInRepair(), 1);
        assertEquals(vs.getVehiclesInRepair()[0], car);
    }

    @Test
    void moveVehicleToAvailable() {
        ElectricCar car = new ElectricCar(30, "LG-654-AL", "Tesla Model S");
        vs.addVehicleInRepair(car);
        assertEquals(1, vs.getNbOfVehiclesInRepair());
        assertEquals(vs.getVehiclesInRepair()[0], car);
        vs.moveVehicleToAvailable(car);
        assertEquals(0, vs.getNbOfVehiclesInRepair());
        assertEquals(1, vs.getNbOfAvailableVehicles());
        assertEquals(vs.getAvailableVehicles()[0], car);
    }

    @Test
    void findAvailableVehicleWithMaxDistance() {
        ElectricCar car = new ElectricCar(30, "LG-654-AL", "Tesla Model S");
        ElectricBike bike = new ElectricBike(30, new double[]{0.1, 0.2, 0.5});
        bike.setPedalAssistLevel(2);
        vs.addAvailableVehicle(car);
        vs.addAvailableVehicle(bike);
        assertEquals(2, vs.getNbOfAvailableVehicles());
        assertEquals(vs.findAvailableVehicleWithMaxDistance(), car);
    }

    @Test
    void findVehicleWithMaxRangeInAvailableAndInRepair() {
        ElectricCar car = new ElectricCar(30, "LG-654-AL", "Tesla Model S");
        ElectricBike bike = new ElectricBike(30, new double[]{0.1, 0.2, 0.5});
        bike.setPedalAssistLevel(2);
        vs.addVehicleInRepair(car);
        vs.addVehicleInRepair(bike);
        assertEquals(2, vs.getNbOfVehiclesInRepair());
        assertEquals(vs.findVehicleWithMaxRangeInAvailableAndInRepair(), car);
    }
}
