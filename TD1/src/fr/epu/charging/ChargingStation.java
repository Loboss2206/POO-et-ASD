package fr.epu.charging;

import fr.epu.vehicles.ElectricVehicle;

/**
 * @author Loboss2206
 * This class represents a charging station.
 */
public class ChargingStation {
    private String stationName;
    private int availableChargingPoints;
    private EnergyProvider energyProvider;

    /**
     * This constructor initialises the charging station with the given name and the given number of charging points.
     * @param stationName the name of the charging station
     * @param availableChargingPoints the number of charging points
     * @param energyProvider the energy provider
     */
    public ChargingStation(String stationName, int availableChargingPoints, EnergyProvider energyProvider) {
        this.stationName = stationName;
        this.availableChargingPoints = availableChargingPoints;
        this.energyProvider = energyProvider;
    }

    /**
     * This constructor initialises the charging station with the given name and the given number of charging points.
     * @param stationName the name of the charging station
     * @param availableChargingPoints the number of charging points
     */
    public ChargingStation(String stationName, int availableChargingPoints) {
        this.stationName = stationName;
        this.availableChargingPoints = availableChargingPoints;
        this.energyProvider = new EnergyProvider("EDF","Solar");
    }

    /**
     * This method connects the given vehicle to a charging point.
     * @param ev the vehicle to connect
     * @return the amount of energy charged
     */
    public double connectToChargingPoint(ElectricVehicle ev) {
        double charge = 0;
        if (availableChargingPoints > 0 && ev.connect()) {
                availableChargingPoints--;
                charge = ev.chargeToFull();
        }
        return charge;
    }

    /**
     * This method disconnects the given vehicle from a charging point.
     * @param ev the vehicle to disconnect
     */
    public void disconnectFromChargingPoint(ElectricVehicle ev) {
        if (ev.isConnected()) {
            ev.setConnected(false);
            availableChargingPoints++;
        }
    }

    public String getStationName() {
        return stationName;
    }

    public int getAvailableChargingPoints() {
        return availableChargingPoints;
    }

    public EnergyProvider getEnergyProvider() {
        return energyProvider;
    }
}
