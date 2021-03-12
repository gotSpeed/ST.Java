package twentieth.plants.flowers;


import twentieth.plants.Plant;
import twentieth.plants.PlantCharacteristic;

import java.util.Map;



public class Flower extends Plant {

    protected String mBlossomColour;
    protected int    mStemLength;



    public Flower(Map<String, String> characteristics) {

        super(characteristics);
        mBlossomColour = characteristics.get(PlantCharacteristic.BLOSSOM_COLOUR.name());
        mStemLength    =
            Integer.parseInt(characteristics.get(PlantCharacteristic.STEM_LENGTH.name()));
    }

}
