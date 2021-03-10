package eighteenth.parser.handler;


import eighteenth.point.Point;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;



public class PointsHandler extends DefaultHandler {

    private static final String MAP   = "map";
    private static final String POINT = "point";
    private static final String UNIT  = "unit";
    private static final String X     = "x";
    private static final String Y     = "y";

    private List<Point> mPoints;
    private String      mDataHolder;



    public List<Point> getPoints() {

        return mPoints;
    }



    @Override
    public void characters(char[] ch, int start, int length) {

        mDataHolder = new String(ch, start, length);
    }



    @Override
    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes) {

        switch (qName) {
            case MAP:
                mPoints = new ArrayList<>();
                break;
            case POINT:
                Point p = new Point();
                p.setMeasureUnit(attributes.getValue(UNIT));
                mPoints.add(p);
                break;
            default:
                break;
        }
    }



    @Override
    public void endElement(String uri, String localName, String qName) {

        switch (qName) {
            case X:
                mPoints.get(mPoints.size() - 1).setX(Integer.parseInt(mDataHolder));
                break;
            case Y:
                mPoints.get(mPoints.size() - 1).setY(Integer.parseInt(mDataHolder));
                break;
        }
    }

}
