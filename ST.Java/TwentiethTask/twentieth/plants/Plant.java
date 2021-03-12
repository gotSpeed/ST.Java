package twentieth.plants;


import java.util.Map;



public class Plant {

    protected String mName;
    protected String mPlantClass;
    protected String mGenus;
    protected String mDescription;

    protected int mWidth;
    protected int mHeight;



    public Plant(Map<String, String> characteristics) {

        mName        = characteristics.get(PlantCharacteristic.NAME.name());
        mPlantClass  = characteristics.get(PlantCharacteristic.CLASS.name());
        mGenus       = characteristics.get(PlantCharacteristic.GENUS.name());
        mDescription = characteristics.get(PlantCharacteristic.DESCRIPTION.name());
        mWidth       =
            Integer.parseInt(characteristics.get(PlantCharacteristic.WIDTH.name()));
        mHeight      =
            Integer.parseInt(characteristics.get(PlantCharacteristic.HEIGHT.name()));
    }



    // region getters/setters
    public String getPlantClass() {

        return mPlantClass;
    }



    public String getName() {

        return mName;
    }



    public void setName(String name) {

        mName = name;
    }



    public void setPlantClass(String aClass) {

        mPlantClass = aClass;
    }



    public int getWidth() {

        return mWidth;
    }



    public void setWidth(int width) {

        mWidth = width;
    }



    public int getHeight() {

        return mHeight;
    }



    public void setHeight(int height) {

        mHeight = height;
    }
    // endregion

}
