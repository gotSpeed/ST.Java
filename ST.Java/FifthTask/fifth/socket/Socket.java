package fifth.socket;


import fifth.appliance.Appliance;

import java.util.HashSet;
import java.util.Set;



public class Socket {

    private static final int   AVERAGE_APPLIANCES_AMOUNT = 2;
    public static final  float MAX_WORKLOAD_DEFAULT      = 1250.0f;

    private String mManufacturer;

    private float mWorkload;
    private float mMaxWorkload;

    private Set<Appliance> mPluggedInAppliances;



    public Socket(String manufacturer, float maxWorkload) {

        mManufacturer = manufacturer;
        mMaxWorkload  = maxWorkload;
    }



    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder(
            String.format("\nSocket manufacturer: %s\nCurrent workload: %f\n",
                          mManufacturer,
                          mWorkload)
        );
        mPluggedInAppliances.forEach(app -> builder.append(app.toString()));
        return builder.toString();
    }



    public void plugIn(Appliance appliance) {

        if (mPluggedInAppliances == null) {
            mPluggedInAppliances = new HashSet<>(AVERAGE_APPLIANCES_AMOUNT);
        }

        if ((mWorkload + appliance.getPowerUsage()) > mMaxWorkload) {
            System.out.println("\nFailed to plug in: maximum has been reached.\n");
        }
        else {
            mWorkload += appliance.getPowerUsage();
            mPluggedInAppliances.add(appliance);
        }
    }



    public void unplug(Appliance appliance) {

        mWorkload -= appliance.getPowerUsage();
        mPluggedInAppliances.remove(appliance);
    }

}
