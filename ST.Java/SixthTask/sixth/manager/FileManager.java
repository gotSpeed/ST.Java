package sixth.manager;


import java.io.*;
import java.util.Random;



public class FileManager {

    public static final String DEFAULT_FILEPATH  = "SixthTask\\out.dat";
    public static final int    DEFAULT_AMOUNT    = 20;
    public static final int    DEFAULT_THRESHOLD = 10000;



    public static void writeRandomly(String filePath, int amount) {

        try {
            File outFile = new File(filePath);
            outFile.createNewFile();

            try (FileOutputStream oStream = new FileOutputStream(outFile, false);
                 DataOutputStream doStream = new DataOutputStream(oStream)) {

                Random rand = new Random();
                int count = 0;

                while (count++ < amount) {
                    doStream.writeInt(Math.abs(rand.nextInt() % DEFAULT_THRESHOLD));

                    // Debug
                    // int tmp = Math.abs(rand.nextInt() % DEFAULT_THRESHOLD);
                    // doStream.writeInt(tmp);
                    // System.out.println(tmp);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static void writeRandomly() {

        writeRandomly(DEFAULT_FILEPATH, DEFAULT_AMOUNT);
    }



    public static void writeRandomly(String filePath) {

        writeRandomly(filePath, DEFAULT_AMOUNT);
    }



    public static double readFileAndFindAverage(String filePath) {

        try (FileInputStream iStream = new FileInputStream(filePath);
             DataInputStream diStream = new DataInputStream(iStream)) {

            int count = diStream.available();
            int accumulator = 0;
            int amount = 0;

            if (count != 0) {
                int dword;
                do {
                    dword = diStream.readInt();
                    accumulator += dword;
                    amount++;
                    System.out.println(dword);
                }
                while ((count -= Integer.BYTES) > 0);
            }

            return accumulator * 1.0 / amount;
        }
        catch (IOException e) {
            e.printStackTrace();

            return -1.0;
        }
    }



    public static double readFileAndFindAverage() {

        return readFileAndFindAverage(DEFAULT_FILEPATH);
    }

}
