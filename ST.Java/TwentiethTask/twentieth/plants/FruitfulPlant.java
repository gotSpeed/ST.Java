package twentieth.plants;


import java.util.Map;



public class FruitfulPlant extends Plant {

    protected String mFruitName;
    protected String mFruitColor;



    public FruitfulPlant(Map<String, String> characteristics) {

        super(characteristics);
        mFruitName  = characteristics.get(PlantCharacteristic.FRUIT_NAME.name());
        mFruitColor = characteristics.get(PlantCharacteristic.FRUIT_COLOUR.name());
    }

}
