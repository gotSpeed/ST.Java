package twentieth.parser.handler;


import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import twentieth.park.Park;
import twentieth.plants.PlantCharacteristic;
import twentieth.plants.PlantType;
import twentieth.plants.bushes.Bush;
import twentieth.plants.flowers.Flower;
import twentieth.plants.trees.Tree;

import java.util.*;



public class PlantsParserHandler extends DefaultHandler {

    private Park                mPark;
    private Map<String, String> mEditablePlantCharacteristics;
    private String              mReadValue;     // Characters that has just been read.
    private boolean             mIsParsingFruit = false;



    public PlantsParserHandler(Park park) {

        mPark                         = park;
        mEditablePlantCharacteristics = new HashMap<>();
    }



    @Override
    public void characters(char[] ch, int start, int length) {

        mReadValue = String.valueOf(ch, start, length);
    }



    @Override
    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes) {

        if (qName.equals(PlantCharacteristic.FRUIT.value())) {
            mIsParsingFruit = true;
        }
    }



    @Override
    public void endElement(String uri, String localName, String qName) {

        try {
            PlantType plantType = PlantType.valueOf(qName.toUpperCase());
            switch (plantType) {
                case TREE:
                    mPark.plantNew(new Tree(mEditablePlantCharacteristics));
                    break;
                case BUSH:
                    mPark.plantNew(new Bush(mEditablePlantCharacteristics));
                    break;
                case FLOWER:
                    mPark.plantNew(new Flower(mEditablePlantCharacteristics));
                    break;
            }

            if (qName.equals(PlantCharacteristic.FRUIT.value())) {
                mIsParsingFruit = false;
            }
        }
        catch (IllegalArgumentException e) {
            resolveCharacteristic(qName);
        }
    }



    private void resolveCharacteristic(String qName) {

        if (mIsParsingFruit) {

            if (qName.equals(PlantCharacteristic.NAME.value())) {

                mEditablePlantCharacteristics.put(
                    PlantCharacteristic.FRUIT_NAME.value(),
                    mReadValue
                );
            }
            else {
                mEditablePlantCharacteristics.put(
                    PlantCharacteristic.FRUIT_COLOUR.value(),
                    mReadValue
                );
            }
        }
        else if (qName.equals(PlantCharacteristic.PROPORTIONS.value())) {
            return;
        }

        mEditablePlantCharacteristics.put(qName, mReadValue);
    }

}
