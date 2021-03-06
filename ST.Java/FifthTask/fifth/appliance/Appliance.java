package fifth.appliance;


import fifth.socket.Socket;



public class Appliance {

    // Assuming we got an ID generator.
    private static int ID = 1;

    protected int mId;

    protected String mTitle;
    protected String mManufacturer;
    protected String mModel = "unknown";

    protected float mPowerUsage;

    protected boolean mIsActive = false;
    protected Socket  mSocket;



    public Appliance(String title, String model, String manufacturer, float powerUsage) {

        super();
        mId           = ID++;
        mTitle        = title;
        mManufacturer = manufacturer;
        mPowerUsage   = powerUsage;

        if (model != null) {
            mModel = model;
        }
    }



    public boolean isActive() {

        return mIsActive;
    }



    @Override
    public int hashCode() {

        return mId + mTitle.hashCode() + mModel.hashCode();
    }



    @Override
    public boolean equals(Object obj) {

        return Appliance.class == obj.getClass() && hashCode() == obj.hashCode();
    }



    public float getPowerUsage() {

        return mPowerUsage;
    }



    @Override
    public String toString() {

        return String.format(
            "\nID: %d\nTitle: %s\nModel: %s\nPower usage: %f\nIs plugged: %b\n",
            mId, mTitle, mModel, mPowerUsage, isActive()
        );
    }



    public void plugIn(Socket socket) {

        mIsActive = true;
        mSocket   = socket;
        socket.plugIn(this);
    }



    public void unplug() {

        mIsActive = false;
        mSocket.unplug(this);
        mSocket = null;
    }

}
