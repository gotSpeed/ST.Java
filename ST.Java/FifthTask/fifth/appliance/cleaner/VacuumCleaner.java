package fifth.appliance.cleaner;


import fifth.appliance.Appliance;



public class VacuumCleaner extends Appliance {

    protected double mStrength;



    public VacuumCleaner(String manufacturer, String model, float powerUsage) {

        super("Vacuum cleaner", model, manufacturer, powerUsage);
        mStrength = 1.75 * powerUsage;
    }



    @Override
    public String toString() {

        return super.toString() + String.format("Strength: %f\n", mStrength);
    }

}
