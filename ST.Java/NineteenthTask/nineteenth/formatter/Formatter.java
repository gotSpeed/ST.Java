package nineteenth.formatter;


import eighteenth.parser.handler.PointsHandler;
import eighteenth.point.Point;

import nineteenth.formatter.handler.XslHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;



public class Formatter {

    private static final String OUT_FILE_PATH = "NineteenthTask\\out.html";



    private List<Point> mPoints;
    private String      mParsedString;



    public void formatXmlToHtml(String xslFilepath, String xmlFilepath) {

        mPoints       = parsePointsFromXml(xmlFilepath);
        mParsedString = parseXslt(xslFilepath);
        saveToFile(mParsedString);
    }



    private void saveToFile(String parsedString) {

        try {
            File file = new File(OUT_FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedWriter bfWriter = new BufferedWriter(new FileWriter(file))) {

                bfWriter.write(parsedString);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    private String parseXslt(String filepath) {

        try {
            SAXParser parser = SAXParserFactory.newInstance()
                                               .newSAXParser();

            XslHandler handler = new XslHandler(mPoints);

            parser.parse(filepath, handler);

            return handler.getSkeleton();
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }



    private List<Point> parsePointsFromXml(String filepath) {

        try {
            SAXParser parser = SAXParserFactory.newInstance()
                                               .newSAXParser();

            PointsHandler handler = new PointsHandler();

            parser.parse(filepath, handler);
            return handler.getPoints();
        }
        catch (SAXException | ParserConfigurationException | IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
