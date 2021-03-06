package fifth.appliance.washer;


public class Dishwasher extends Washer {

    protected static final int DEFAULT_DISHWASHER_TIME = 30;



    public Dishwasher(String manufacturer,
                      String model,
                      float powerUsage,
                      float innerVolume) {

        super("Dishwasher",
              model,
              manufacturer,
              powerUsage,
              DEFAULT_DISHWASHER_TIME,
              innerVolume);

    }

}
