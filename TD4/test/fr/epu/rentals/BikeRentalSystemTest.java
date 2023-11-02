package fr.epu.rentals;

import fr.epu.vehicles.ElectricBike;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BikeRentalSystemTest {

    @Test
    void findEligibleItems() {
        BikeRentalSystem bikeRentalSystem = new BikeRentalSystem(10);
        assertEquals(0, bikeRentalSystem.getItems().size());
        ElectricBike eb = new ElectricBike(30, new double[]{0.2, 0.5, 0.8});
        bikeRentalSystem.addItem(eb);
        assertEquals(1, bikeRentalSystem.getItems().size());
        assertEquals(1, bikeRentalSystem.findEligibleItems(3).size());
        ElectricBike eb2 = new ElectricBike(30, new double[]{0.2, 0.5});
        bikeRentalSystem.addItem(eb2);
        assertEquals(2, bikeRentalSystem.getItems().size());
        assertEquals(1, bikeRentalSystem.findEligibleItems(3).size());
    }
}