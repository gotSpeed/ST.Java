package fifth.appliance.fan;


import fifth.appliance.Appliance;



public class Fan extends Appliance {

    public static final float DEFAULT_POWER_USAGE = 125.0f;

    protected int mVelocity;
    protected int mDiameter = 80;



    public Fan(String manufacturer, String model, float powerUsage, int diameter) {

        super("Fan", model, manufacturer, powerUsage);
        mDiameter = diameter;
        mVelocity = (int) powerUsage * 80;
    }



    @Override
    public String toString() {

        return super.toString() +
               String.format("Velocity (R/s): %d\nDiameter: %d\n", mVelocity, mDiameter);
    }

}
