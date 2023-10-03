package fr.epu.charging;

/**
 * @author Loboss2206
 * This class represents a charging station.
 */
public class EnergyProvider {
    private String providerName;
    private String energySource; // Ex: Solar, Wind, Grid, etc.

    /**
     * This constructor initialises the charging station with the given name and the given number of charging points.
     * @param providerName the name of the charging station
     * @param energySource the number of charging points
     */
    public EnergyProvider(String providerName, String energySource) {
        this.providerName = providerName;
        this.energySource = energySource;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getEnergySource() {
        return energySource;
    }

}