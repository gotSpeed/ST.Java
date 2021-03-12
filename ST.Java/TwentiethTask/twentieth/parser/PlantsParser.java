package twentieth.parser;


import org.xml.sax.SAXException;
import twentieth.parser.handler.PlantsParserHandler;
import twentieth.plants.Plant;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;



public class PlantsParser {

    private static final String FILE_ERROR_MESSAGE =
        "\nFile does not exist or wrong file location is provided\n";


    private List<Plant> mPlants;



    public List<Plant> getPlants() {

        return mPlants;
    }



    public void readFile(String filepath) {

        try {

            File file = new File(filepath);

            if (file.exists()) {
                SAXParser parser = SAXParserFactory.newInstance()
                                                   .newSAXParser();

                PlantsParserHandler handler = new PlantsParserHandler();

                parser.parse(filepath, handler);
                mPlants = handler.getPlants();
            }
            else {
                System.out.println(FILE_ERROR_MESSAGE);
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
