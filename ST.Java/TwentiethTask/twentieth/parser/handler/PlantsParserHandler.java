package twentieth.parser.handler;


import org.xml.sax.helpers.DefaultHandler;
import twentieth.plants.Plant;
import twentieth.plants.PlantType;
import twentieth.plants.bushes.Bush;
import twentieth.plants.flowers.Flower;
import twentieth.plants.trees.Tree;

import java.util.*;



public class PlantsParserHandler extends DefaultHandler {

    private List<Plant> mPlants;


    private Map<String, String> mEditablePlantCharacteristics;
    private String              mReadValue;     // Value that has just been read.



    {
        mPlants                       = new ArrayList<>();
        mEditablePlantCharacteristics = new HashMap<>();
    }



    public List<Plant> getPlants() {

        return mPlants;
    }



    @Override
    public void characters(char[] ch, int start, int length) {

        mReadValue = Arrays.toString(ch).substring(start, start + length + 1);
    }



    @Override
    public void endElement(String uri, String localName, String qName) {

        try {
            PlantType plantType = PlantType.valueOf(qName.toUpperCase());
            switch (plantType) {
                case TREE:
                    mPlants.add(new Tree(mEditablePlantCharacteristics));
                    break;
                case BUSH:
                    mPlants.add(new Bush(mEditablePlantCharacteristics));
                    break;
                case FLOWER:
                    mPlants.add(new Flower(mEditablePlantCharacteristics));
                    break;
            }
        }
        catch (IllegalArgumentException e) {
            mEditablePlantCharacteristics.put(qName, mReadValue);
        }
    }

}
