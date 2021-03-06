package fifth.appliance.stove;


public class StoveWithOven extends Stove {

    protected int      mSetTemperature;
    protected OvenMode mMode;



    public StoveWithOven(String manufacturer,
                         String model,
                         float powerUsage,
                         int amountOfHotplates,
                         int setTemperature,
                         OvenMode mode) {

        super(manufacturer, model, powerUsage, amountOfHotplates);
        mSetTemperature = setTemperature;
        mMode           = mode;
    }



    @Override
    public String toString() {

        return super.toString() +
               String.format("Set temperature (dgr C): %d\nMode: %s\n",
                             mSetTemperature,
                             mMode.name());
    }

}
