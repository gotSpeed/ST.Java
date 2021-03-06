package fifth.appliance.kettle;


import fifth.appliance.Appliance;



public class Kettle extends Appliance {

    protected boolean mIsOnStand = true;



    public Kettle(String manufacturer, String model, float powerUsage) {

        super("Kettle", model, manufacturer, powerUsage);
    }



    @Override
    public boolean isActive() {

        return mIsOnStand && super.isActive();
    }



    public void removeFromStand() {

        mIsOnStand = false;
    }



    public void attachToStand() {

        mIsOnStand = true;
    }

}
