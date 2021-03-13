package twentieth.plants.bushes;


import twentieth.plants.FruitfulPlant;
import twentieth.plants.PlantCharacteristic;

import java.util.Map;



public class Bush extends FruitfulPlant {

    protected int mRowLength;



    public Bush(Map<String, String> characteristics) {

        super(characteristics);
        mRowLength =
            Integer.parseInt(characteristics.get(PlantCharacteristic.ROW_LENGTH.value()));
    }



    @Override
    public double getOccupiedSpace() {

        return mWidth * mRowLength;
    }

}
