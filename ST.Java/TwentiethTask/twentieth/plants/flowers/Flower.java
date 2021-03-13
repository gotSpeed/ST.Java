package twentieth.plants.flowers;


import twentieth.plants.Plant;
import twentieth.plants.PlantCharacteristic;

import java.util.Map;



public class Flower extends Plant {

    protected String mBlossomColour;
    protected double mStemLength;



    public Flower(Map<String, String> characteristics) {

        super(characteristics);
        mBlossomColour = characteristics.get(PlantCharacteristic.BLOSSOM_COLOUR.value());
        mStemLength    =
            Double.parseDouble(characteristics.get(PlantCharacteristic.STEM_LENGTH.value()));
    }



    @Override
    public double getOccupiedSpace() {

        return (int) (mWidth * 1.5);
    }

}
