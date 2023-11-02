package fr.epu.rentals;

import fr.epu.vehicles.ElectricBike;

import java.util.ArrayList;

public class BikeRentalSystem extends RentalSystem<ElectricBike> {
    public BikeRentalSystem(int maxItems) {
        super(maxItems);
    }

    public ArrayList<ElectricBike> findEligibleItems(int numberOfAvailableLevels) {
        ArrayList<ElectricBike> eligibleItems = new ArrayList<>();
        for (ElectricBike bike : getItems()) {
            if (bike.isAvailable() && bike.getNumberOfAvailableLevels() >= numberOfAvailableLevels) {
                eligibleItems.add(bike);
            }
        }
        return eligibleItems;
    }
}
