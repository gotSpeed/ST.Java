package twentieth.plants;


import java.util.Map;



public class Plant {

    protected String mName;
    protected String mPlantClass;
    protected String mGenus;
    protected String mDescription;

    protected double mWidth;
    protected double mHeight;



    public Plant(Map<String, String> characteristics) {

        mName        = characteristics.get(PlantCharacteristic.NAME.value());
        mPlantClass  = characteristics.get(PlantCharacteristic.CLASS.value());
        mGenus       = characteristics.get(PlantCharacteristic.GENUS.value());
        mDescription = characteristics.get(PlantCharacteristic.DESCRIPTION.value());
        mWidth       =
            Double.parseDouble(characteristics.get(PlantCharacteristic.WIDTH.value()));
        mHeight      =
            Double.parseDouble(characteristics.get(PlantCharacteristic.HEIGHT.value()));
    }



    public double getOccupiedSpace() {

        return mWidth;
    }



    public double getHeight() {

        return mHeight;
    }

}
