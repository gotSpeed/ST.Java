package fifth.appliance.washer;


import fifth.appliance.Appliance;



public class Washer extends Appliance {

    protected int   mTimer;
    protected float mInnerVolume;



    public Washer(String title,
                  String manufacturer,
                  String model,
                  float powerUsage,
                  int timer,
                  float innerVolume) {

        super(title, model, manufacturer, powerUsage);
        mTimer       = timer;
        mInnerVolume = innerVolume;
    }



    @Override
    public String toString() {

        return super.toString() +
               String.format("Working time: %d\nInner volume: %f\n",
                             mTimer,
                             mInnerVolume);
    }

}
