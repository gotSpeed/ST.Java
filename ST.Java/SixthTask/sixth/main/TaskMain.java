package sixth.main;


import sixth.manager.FileManager;

import java.io.FileInputStream;



public class TaskMain {

    public static void main(String[] args) {

        if (args.length > 0) {
            FileManager.writeRandomly(args[0]);
            System.out.println(
                String.format("Avg: %f", FileManager.readFileAndFindAverage(args[0]))
            );
        }
        else {
            FileManager.writeRandomly();
            System.out.println(
                String.format("Avg: %f", FileManager.readFileAndFindAverage())
            );
        }

    }

}
