package third;


public class NameMark {

    private String mName;
    private int    mMark;



    public NameMark(String name, int mark) {

        mName = name;
        mMark = mark;
    }



    public int getMark() {

        return mMark;
    }



    public void setMark(int mark) {

        mMark = mark;
    }



    @Override
    public String toString() {

        return String.format("\n%s : %d\n", mName, mMark);
    }

}
