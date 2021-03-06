package fifth.appliance.fridge;


import fifth.appliance.Appliance;



public class Fridge extends Appliance {

    public static final int    DEFAULT_TEMPERATURE  = 3;
    public static final String DEFAULT_MANUFACTURER = "Bosch";

    protected int   mTemperature;
    protected float mInnerVolume;



    public Fridge(String manufacturer,
                  String model,
                  float powerUsage,
                  int temperature,
                  float innerVolume) {

        super("Fridge", model, manufacturer, powerUsage);
        mTemperature = temperature;
        mInnerVolume = innerVolume;
    }



    @Override
    public String toString() {

        return super.toString() +
               String.format("Temperature (dgr C): %d\nInner volume: %f\n",
                             mTemperature,
                             mInnerVolume);
    }

}
