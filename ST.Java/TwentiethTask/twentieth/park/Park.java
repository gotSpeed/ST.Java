package twentieth.park;


import twentieth.parser.PlantsParser;
import twentieth.plants.Plant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;



public class Park {

    private static final String DEFAULT_OUT_FILE        = "out.xml";
    private static final String PARK_TEMPLATE           =
        "<?xml version=\"1.0\"?>\n<park>%s\n</park>";
    private static final String SUMMARY_HEIGHT_TEMPLATE =
        "\n\t<summary-height>%s</summary-height>";
    private static final String SPACE_OCCUPIED_TEMPLATE =
        "\n\t<space-occupied>%s</space-occupied>";
    private static final String TOTAL_AMOUNT_TEMPLATE   =
        "\n\t<total-amount>%s</total-amount>";

    private static final Park mPark;

    private List<Plant> mPlants;
    private int         mTotalSpaceOccupied;
    private int         mTotalPlantsAmount;
    private int         mSummaryHeight;



    static {

        mPark = new Park();

    }



    public static Park getInstance() {

        return mPark;
    }



    public void fillPark(String srcFilePath) {

        PlantsParser parser = new PlantsParser();
        parser.readFile(srcFilePath);
        mPlants = parser.getPlants();

        distributePlants();
    }



    public void saveParkInfo() {

        try {
            String out = String.format(PARK_TEMPLATE, buildTemplate());
            File file = new File(DEFAULT_OUT_FILE);
            file.createNewFile();

            try (FileWriter fWriter = new FileWriter(file)) {
                fWriter.write(out);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }



    private String buildTemplate() {

        StringBuilder builder = new StringBuilder();
        builder.append(
            String.format(SUMMARY_HEIGHT_TEMPLATE, mSummaryHeight)
        ).append(
            String.format(TOTAL_AMOUNT_TEMPLATE, mTotalPlantsAmount)
        ).append(
            String.format(SPACE_OCCUPIED_TEMPLATE, mTotalSpaceOccupied)
        );

        return builder.toString();
    }



    private void distributePlants() {

        mTotalSpaceOccupied = mPlants.stream()
                                     .reduce(
                                         0,
                                         (space, plant) -> space + plant.getWidth() *
                                                                   plant.getHeight(),
                                         Integer::sum
                                     );

        mTotalPlantsAmount = mPlants.size();
        mSummaryHeight     = mPlants.stream()
                                    .reduce(0,
                                            (height, plant) -> height + plant.getHeight(),
                                            Integer::sum);
    }

}
