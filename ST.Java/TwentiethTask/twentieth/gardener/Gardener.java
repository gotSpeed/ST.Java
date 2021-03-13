package twentieth.gardener;


import twentieth.park.Park;
import twentieth.parser.PlantsParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Gardener {

    private static final String PARK_TEMPLATE           =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<park-info>%s\n</park-info>";
    private static final String SUMMARY_HEIGHT_TEMPLATE =
        "\n\t<summary-height>%s</summary-height>";
    private static final String SPACE_OCCUPIED_TEMPLATE =
        "\n\t<space-occupied>%s</space-occupied>";
    private static final String TOTAL_AMOUNT_TEMPLATE   =
        "\n\t<total-amount>%s</total-amount>";

    private static final String OUTPUT_FILE_NAME = "park-info.xml";

    private static String mSourceDir;



    public static Park fillNewPark(String srcFilePath) {

        mSourceDir = getSourceDir(srcFilePath);

        Park newPark = new Park();
        PlantsParser parser = new PlantsParser(newPark);
        parser.readFile(srcFilePath);

        return newPark;
    }



    public static void saveParkInfo(Park park) {

        File outFile = Paths.get(mSourceDir, OUTPUT_FILE_NAME).toFile();
        String outString = buildTemplate(park);

        try (FileWriter fileWriter = new FileWriter(outFile)) {
            fileWriter.write(outString);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }



    private static String getSourceDir(String srcPath) {

        Path absPath = Paths.get(srcPath).toAbsolutePath();
        String absPathString = absPath.toString();
        int index = absPathString.lastIndexOf(
            absPath.getFileName().toString()
        );

        return absPathString.substring(0, index);
    }



    private static String buildTemplate(Park park) {

        return String.format(PARK_TEMPLATE,
                             String.format(SUMMARY_HEIGHT_TEMPLATE,
                                           park.getSummaryHeight()) +
                             String.format(TOTAL_AMOUNT_TEMPLATE,
                                           park.getPlants().size()) +
                             String.format(SPACE_OCCUPIED_TEMPLATE,
                                           park.getTotalSpaceOccupied())
        );
    }

}
