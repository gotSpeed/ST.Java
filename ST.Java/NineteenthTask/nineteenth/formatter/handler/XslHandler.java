package nineteenth.formatter.handler;


import eighteenth.point.Point;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Arrays;
import java.util.List;



public class XslHandler extends DefaultHandler {

    private static final String TEMPLATE  = "xsl:template";
    private static final String LOOP      = "xsl:for-each";
    private static final String VALUE     = "xsl:value-of";
    private static final String X         = "x";
    private static final String Y         = "y";
    private static final String X_PATTERN = "%x%";
    private static final String Y_PATTERN = "%y%";

    private String        mTemplate;
    private String        mSkeleton;
    private StringBuilder mSkeletonBuilder;
    private StringBuilder mTemplateBuilder;
    private boolean       mIsMatchingArea = false;
    private boolean       mIsLoopArea     = false;

    private List<Point> mPoints;



    public XslHandler(List<Point> points) {

        mSkeletonBuilder = new StringBuilder();
        mTemplateBuilder = new StringBuilder();
        mPoints          = points;
    }



    public String getSkeleton() {

        return mSkeleton;
    }



    @Override
    public void characters(char[] ch, int start, int length) {

        if (mIsLoopArea) {
            mTemplateBuilder.append(ch, start, length);
        }
        else if (mIsMatchingArea) {
            mSkeletonBuilder.append(ch, start, length);
        }
    }



    @Override
    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes) {

        switch (qName) {
            case TEMPLATE:
                mIsMatchingArea = true;
                break;
            case LOOP:
                mIsLoopArea = true;
                break;
            case VALUE:
                mTemplateBuilder.append(resolvePointAttribute(attributes));
                break;
            default:
                if (mIsLoopArea) {
                    String attrs = resolveAttributes(attributes);
                    mTemplateBuilder.append(
                        String.format("\n<%s %s>", qName, attrs)
                    );
                }
                else if (mIsMatchingArea) {
                    String attrs = resolveAttributes(attributes);
                    mSkeletonBuilder.append(
                        String.format("\n<%s %s>", qName, attrs)
                    );
                }
                break;
        }
    }



    @Override
    public void endElement(String uri, String localName, String qName) {

        switch (qName) {
            case TEMPLATE:
                mIsMatchingArea = false;
                mSkeleton = mSkeletonBuilder.toString();
                break;
            case LOOP:
                mIsLoopArea = false;
                mTemplate = mTemplateBuilder.toString();
                mSkeletonBuilder.append(inflateTemplateWithPoints());
                break;
            case VALUE:
                break;
            default:
                if (mIsLoopArea) {
                    mTemplateBuilder.append(
                        String.format("\n</%s>", qName)
                    );
                }
                else if (mIsMatchingArea) {
                    mSkeletonBuilder.append(
                        String.format("\n</%s>", qName)
                    );
                }
                break;
        }
    }



    private String inflateTemplateWithPoints() {

        StringBuilder builder = new StringBuilder();
        String temp;
        for (var elem : mPoints) {
            temp = mTemplate.replace(
                X_PATTERN,
                String.valueOf(elem.getX()) + elem.getMeasureUnit()
            );
            builder.append(temp.replace(
                Y_PATTERN,
                String.valueOf(elem.getY()) + elem.getMeasureUnit()
            ));
        }

        return builder.toString();
    }



    private String resolvePointAttribute(Attributes attrs) {

        String attribute = attrs.getValue(0);
        if (attribute.equals(X)) {
            return String.format("\n<x>%s</x>", X_PATTERN);
        }
        else if (attribute.equals(Y)) {
            return String.format("\n<y>%s</y>", Y_PATTERN);
        }
        else {
            return "";
        }
    }



    private String resolveAttributes(Attributes attrs) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < attrs.getLength(); i++) {

            builder.append(
                String.format(" %s=\"%s\" ", attrs.getQName(i), attrs.getValue(i))
            );
        }

        return builder.toString();
    }

}
