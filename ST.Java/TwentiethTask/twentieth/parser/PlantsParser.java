package twentieth.parser;


import org.xml.sax.SAXException;
import twentieth.park.Park;
import twentieth.parser.handler.PlantsParserHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;



public class PlantsParser {

    private static final String FILE_ERROR_MSG =
        "\nFile does not exist or wrong file location is provided\n";

    private Park mPark;



    public PlantsParser(Park park) {

        mPark = park;
    }



    public void readFile(String filepath) {

        try {

            File file = new File(filepath);

            if (file.exists()) {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                factory.setValidating(true);
                SAXParser parser = factory.newSAXParser();

                PlantsParserHandler handler = new PlantsParserHandler(mPark);


                parser.parse(filepath, handler);
            }
            else {
                System.out.println(FILE_ERROR_MSG);
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
