package fifth.appliance.stove;


import fifth.appliance.Appliance;



public class Stove extends Appliance {

    protected int mAmountOfHotplates;



    public Stove(String manufacturer,
                 String model,
                 float powerUsage,
                 int amountOfHotplates) {

        super("Stove", model, manufacturer, powerUsage);
        mAmountOfHotplates = amountOfHotplates;
    }



    @Override
    public String toString() {

        return super.toString() +
               String.format("Hotplates amount: %d\n", mAmountOfHotplates);
    }

}
