package fifth.appliance.fridge;


public class FridgeWithFreezer extends Fridge {

    public static final int DEFAULT_FREEZER_TEMPERATURE = -18;

    protected int   mFreezerTemperature;
    protected float mFreezerInnerVolume;



    public FridgeWithFreezer(String manufacturer,
                             String model,
                             float powerUsage,
                             int temperature,
                             float innerVolume,
                             int freezerTemperature,
                             float freezerInnerVolume) {

        super(manufacturer, model, powerUsage, temperature, innerVolume);
        mFreezerInnerVolume = innerVolume;
        mFreezerTemperature = temperature;
    }



    @Override
    public String toString() {

        return super.toString() +
               String.format("Freezer temperature (dgr C): %d\nInner volume: %f\n",
                             mFreezerTemperature,
                             mFreezerInnerVolume);
    }

}
