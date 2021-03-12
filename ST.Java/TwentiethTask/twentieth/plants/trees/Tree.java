package twentieth.plants.trees;


import twentieth.plants.FruitfulPlant;
import twentieth.plants.PlantCharacteristic;

import java.util.Map;



public class Tree extends FruitfulPlant {

    protected int     mTrunkThickness;
    protected boolean mIsPainted;



    public Tree(Map<String, String> characteristics) {

        super(characteristics);
        mTrunkThickness =
            Integer.parseInt(characteristics.get(PlantCharacteristic.TRUNK_THICKNESS.name()));
        mIsPainted      =
            Boolean.parseBoolean(characteristics.get(PlantCharacteristic.IS_PAINTED.name()));
    }



    // region getters/setters
    public int getTrunkThickness() {

        return mTrunkThickness;
    }



    public void setTrunkThickness(int trunkThickness) {

        mTrunkThickness = trunkThickness;
    }



    public boolean isPainted() {

        return mIsPainted;
    }



    public void setPainted(boolean painted) {

        mIsPainted = painted;
    }
    // endregion

}
