package fr.epu.vehicles;

import fr.epu.rentals.Rental;
import fr.epu.rentals.RentalSystem;
import fr.epu.rentals.costumes.CostumeSize;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VehiculeRentalSystemTest {

    RentalSystem vehiculeRentalSystem;

    ElectricCar ec = new ElectricCar(30, "LG-654-AL", "Tesla Model S");
    ElectricBike eb = new ElectricBike(30, new double[]{0.2, 0.5, 0.8});
    ElectricCar ec2 = new ElectricCar(30, "LG-654-AG", "Tesla Model SX");
    ElectricBike eb2 = new ElectricBike(30, new double[]{0.2, 0.5, 0.8});

    LocalDate today = LocalDate.now();
    LocalDate tomorrow = today.plusDays(1);
    LocalDate nextWeek = today.plusWeeks(1);

    @BeforeEach
    void setUp() {
        vehiculeRentalSystem = new RentalSystem(10);
        ec.setUnavailable();
    }

    void initRentalSystem() {
        vehiculeRentalSystem = new RentalSystem(10);
        vehiculeRentalSystem.addItem(eb2);
        vehiculeRentalSystem.addItem(eb);
        vehiculeRentalSystem.addItem(ec2);
        vehiculeRentalSystem.addItem(ec);
    }

    @org.junit.jupiter.api.Test
    void testAddItem() {
        assertEquals(0, vehiculeRentalSystem.getItems().size());
        vehiculeRentalSystem.addItem(eb2);
        assertEquals(1, vehiculeRentalSystem.getItems().size());
        vehiculeRentalSystem.addItem(eb);
        assertEquals(2, vehiculeRentalSystem.getItems().size());
        vehiculeRentalSystem.addItem(ec);
        assertEquals(3, vehiculeRentalSystem.getItems().size());
    }

    @org.junit.jupiter.api.Test
    void testAddItemWithException() {
        assertEquals(0, vehiculeRentalSystem.getItems().size());
        vehiculeRentalSystem.addItem(eb2);
        assertThrows(IllegalArgumentException.class, () -> vehiculeRentalSystem.addItem(eb2));
    }

    @org.junit.jupiter.api.Test
    void testRemoveItem() {
        initRentalSystem();
        assertEquals(4, vehiculeRentalSystem.getItems().size());
        vehiculeRentalSystem.removeItem(eb2);
        assertEquals(3, vehiculeRentalSystem.getItems().size());
        vehiculeRentalSystem.removeItem(ec);
        assertEquals(2, vehiculeRentalSystem.getItems().size());
        vehiculeRentalSystem.removeItem(eb);
        assertEquals(1, vehiculeRentalSystem.getItems().size());
        vehiculeRentalSystem.removeItem(ec2);
        assertEquals(0, vehiculeRentalSystem.getItems().size());
    }

    @org.junit.jupiter.api.Test
    void testSearchItems() {
        initRentalSystem();
        assertEquals(2, vehiculeRentalSystem.searchItems("Tesla").size());
        assertEquals(1, vehiculeRentalSystem.searchItems("SX").size());
        assertEquals(0, vehiculeRentalSystem.searchItems("Ferrari").size());
    }

    @org.junit.jupiter.api.Test
    void testIsRentable() {
        initRentalSystem();
        //All items are rentable today
        assertTrue(vehiculeRentalSystem.isRentable(eb2, today, tomorrow));
        assertTrue(vehiculeRentalSystem.isRentable(eb, today, nextWeek));

        //A costume that is not available cannot be rented
        assertFalse(vehiculeRentalSystem.isRentable(ec, today, tomorrow));
        //Renting an item makes it unavailable for the same period
        vehiculeRentalSystem.rentItem(eb2, today, tomorrow);
        assertFalse(vehiculeRentalSystem.isRentable(eb2, today, nextWeek));
        assertTrue(vehiculeRentalSystem.isRentable(eb, today, nextWeek));
        assertTrue(vehiculeRentalSystem.isRentable(eb2, tomorrow, nextWeek));

        //Renting an item makes it unavailable for the same period (1 jour la semaine prochaine)
        vehiculeRentalSystem.rentItem(eb2, nextWeek, nextWeek.plusDays(1));
        assertTrue(vehiculeRentalSystem.isRentable(eb2, tomorrow, nextWeek));
        assertFalse(vehiculeRentalSystem.isRentable(eb2, tomorrow, nextWeek.plusWeeks(1)));
        assertTrue(vehiculeRentalSystem.isRentable(eb, nextWeek.plusDays(2), nextWeek.plusWeeks(1)));
    }


    @org.junit.jupiter.api.Test
    void testRentItem() {
        initRentalSystem();
        vehiculeRentalSystem.rentItem(eb2, today, tomorrow);
        assertEquals(1, vehiculeRentalSystem.getRentals().size());
        vehiculeRentalSystem.rentItem(eb2, nextWeek, nextWeek.plusDays(1));
        assertEquals(2, vehiculeRentalSystem.getRentals().size());
        vehiculeRentalSystem.rentItem(eb, nextWeek.plusDays(2), nextWeek.plusWeeks(1));
        assertEquals(3, vehiculeRentalSystem.getRentals().size());
    }

    @org.junit.jupiter.api.Test
    void testARental() {
        initRentalSystem();
        vehiculeRentalSystem.rentItem(eb2, today, tomorrow.plusDays(1));
        Rental rental = vehiculeRentalSystem.getRentals().get(0);
        assertEquals(today, rental.getStartDate());
        assertEquals(tomorrow.plusDays(1), rental.getEndDate());
        assertEquals(eb2, rental.getItem());
        assertEquals(20, rental.getCost());
    }


    @org.junit.jupiter.api.Test
    void findAvailableMatches() {
        initRentalSystem();
        assertEquals(1, vehiculeRentalSystem.findAvailableMatches("Tesla", today, tomorrow).size());
        assertEquals(1, vehiculeRentalSystem.findAvailableMatches("SX", today, nextWeek).size());
        assertEquals(0, vehiculeRentalSystem.findAvailableMatches("Ferrari", today, nextWeek).size());
        assertEquals(0, vehiculeRentalSystem.findAvailableMatches("Ford", today, nextWeek).size());
        vehiculeRentalSystem.rentItem(eb2, today, tomorrow);
        assertEquals(1, vehiculeRentalSystem.findAvailableMatches("Tesla", today, tomorrow).size());
        vehiculeRentalSystem.rentItem(ec2, today, tomorrow);
        assertEquals(0, vehiculeRentalSystem.findAvailableMatches("Tesla", today, nextWeek).size());
    }

}