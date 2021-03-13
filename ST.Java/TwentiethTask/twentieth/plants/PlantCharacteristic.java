package twentieth.plants;


public enum PlantCharacteristic {

    NAME("name"),
    GENUS("genus"),
    CLASS("class"),
    DESCRIPTION("description"),
    PROPORTIONS("proportions"),
    WIDTH("width"),
    HEIGHT("height"),
    FRUIT("fruit"),
    TRUNK_THICKNESS("trunk-thickness"),
    IS_PAINTED("painted"),
    FRUIT_NAME("fruit-name"),
    FRUIT_COLOUR("fruit-colour"),
    BLOSSOM_COLOUR("blossom-colour"),
    STEM_LENGTH("stem-length"),
    ROW_LENGTH("row-length");



    PlantCharacteristic(String value) {

        mValue = value;
    }



    public String value() {

        return mValue;
    }



    private String mValue;

}
