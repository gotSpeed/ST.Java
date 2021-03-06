package fifth.appliance.washer;


public class WashingMachine extends Washer {

    protected int                mFrequency;
    protected WashingMachineMode mMode;



    public WashingMachine(String manufacturer,
                          String model,
                          float powerUsage,
                          int timer,
                          float innerVolume,
                          int frequency,
                          WashingMachineMode mode) {

        super("Washing machine", model, manufacturer, powerUsage, timer, innerVolume);
        mFrequency = frequency;
        mMode      = mode;
    }



    @Override
    public String toString() {

        return super.toString() +
               String.format("Frequency: %d\nMode: %s\n",
                             mFrequency,
                             mMode.name());
    }

}
