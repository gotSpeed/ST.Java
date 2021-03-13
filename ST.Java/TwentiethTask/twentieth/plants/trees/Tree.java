package twentieth.plants.trees;


import twentieth.plants.FruitfulPlant;
import twentieth.plants.PlantCharacteristic;

import java.util.Map;



public class Tree extends FruitfulPlant {

    protected double  mTrunkThickness;
    protected boolean mIsPainted;



    public Tree(Map<String, String> characteristics) {

        super(characteristics);
        mTrunkThickness =
            Double.parseDouble(characteristics.get(PlantCharacteristic.TRUNK_THICKNESS.value()));
        mIsPainted      =
            Boolean.parseBoolean(characteristics.get(PlantCharacteristic.IS_PAINTED.value()));
    }

}
