package eighteenth.parser;


import eighteenth.parser.handler.PointsHandler;
import eighteenth.point.Point;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;



public class PointsParser {

    public List<Point> parsePointsFromFile(String filepath) {

        try {

            if (!filepath.matches(".:\\\\[\\w.\\\\]*")) {
                filepath = Paths.get(filepath).toAbsolutePath().toString();
            }

            SAXParser parser = SAXParserFactory.newInstance()
                                               .newSAXParser();

            PointsHandler handler = new PointsHandler();

            parser.parse(filepath, handler);

            return handler.getPoints();
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
