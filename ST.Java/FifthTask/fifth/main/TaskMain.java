package fifth.main;


import fifth.appliance.Appliance;
import fifth.appliance.cleaner.VacuumCleaner;
import fifth.appliance.fan.Fan;
import fifth.appliance.fridge.Fridge;
import fifth.appliance.fridge.FridgeWithFreezer;
import fifth.appliance.kettle.Kettle;
import fifth.appliance.stove.OvenMode;
import fifth.appliance.stove.Stove;
import fifth.appliance.stove.StoveWithOven;
import fifth.appliance.washer.Dishwasher;
import fifth.appliance.washer.WashingMachine;
import fifth.appliance.washer.WashingMachineMode;
import fifth.socket.Socket;



public class TaskMain {

    public static void main(String[] args) {

        // Hardcoded example.
        Socket strongSocket = new Socket("Makel", 6605.0f);
        Socket mediumSocket = new Socket("Legrand", Socket.MAX_WORKLOAD_DEFAULT);
        Socket weakSocket = new Socket("Schneider electric", 435.0f);


        Appliance vacuumCleaner = new VacuumCleaner("Bosch", "axs400", 120.0f);
        Appliance fan = new Fan("Lezard", "sd123", Fan.DEFAULT_POWER_USAGE, 70);
        Appliance kettle = new Kettle("Bosch", "qweRT", 140.0f);
        Appliance stove = new Stove("DKC", "oAooA", 685.75f, 4);
        Appliance dishwasher = new Dishwasher("Bosch", "yyyYy2", 515.95f, 350);

        Appliance fridge = new Fridge("Smart Systems",
                                      "wHo32",
                                      850.f,
                                      Fridge.DEFAULT_TEMPERATURE,
                                      500.0f);

        Appliance fridgeWithFreezer = new FridgeWithFreezer("Smart Systems",
                                                            "350AntS",
                                                            1150.f,
                                                            Fridge.DEFAULT_TEMPERATURE,
                                                            500.0f,
                                                            FridgeWithFreezer.DEFAULT_FREEZER_TEMPERATURE,
                                                            200.0f);

        Appliance stoveWithOven = new StoveWithOven("DKC",
                                                    "nOV9Ember",
                                                    1125.5f,
                                                    4,
                                                    180,
                                                    OvenMode.COOKING);

        Appliance washingMachine = new WashingMachine("Bosch",
                                                      "QXRa34A",
                                                      980.65f,
                                                      30,
                                                      100,
                                                      800,
                                                      WashingMachineMode.SOFT);


        vacuumCleaner.plugIn(weakSocket);       // OK.
        fan.plugIn(weakSocket);                 // OK.
        fridge.plugIn(weakSocket);              // Fail.
        kettle.plugIn(weakSocket);              // OK.

        System.out.println(weakSocket.toString());

        stove.plugIn(mediumSocket);             // OK.
        stoveWithOven.plugIn(mediumSocket);     // Fail.

        System.out.println(mediumSocket.toString());

        fridgeWithFreezer.plugIn(strongSocket); // OK.
        stoveWithOven.plugIn(strongSocket);     // OK.
        dishwasher.plugIn(strongSocket);        // OK.
        washingMachine.plugIn(strongSocket);    // OK.

        System.out.println(strongSocket.toString());

        ((Kettle) kettle).removeFromStand();

        System.out.println(weakSocket);
        System.out.println(kettle.toString());

        stove.unplug();

        System.out.println(mediumSocket);
    }

}
