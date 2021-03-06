package eighteenth.point;



public class Point {

    private String mMeasureUnit;
    private int    mX;
    private int    mY;



    // region getters/setters
    public void setMeasureUnit(String measureUnit) {

        mMeasureUnit = measureUnit;
    }



    public void setX(int x) {

        mX = x;
    }



    public void setY(int y) {

        mY = y;
    }



    public String getMeasureUnit() {

        return mMeasureUnit;
    }



    public int getX() {

        return mX;
    }



    public int getY() {

        return mY;
    }
    // endregion



    @Override
    public String toString() {

        return String.format("%d%s, %d%s", mX, mMeasureUnit, mY, mMeasureUnit);
    }

}
