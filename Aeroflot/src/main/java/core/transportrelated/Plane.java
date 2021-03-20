package core.transportrelated;


import java.time.LocalDateTime;



public class Plane {

    protected long          mId;
    protected String        mTitle;
    protected String        mModel;
    protected Manufacturer  mManufacturer;
    protected LocalDateTime mManufactureDate;
    protected short         mFlightsAmount;
    protected short         mSeatsCount;
    protected short         mMaxFlightDistance;



    // region getters/setters
    public long getId() {

        return mId;
    }



    public void setId(long id) {

        mId = id < 1 ? -1 : id;
    }



    public String getTitle() {

        return mTitle;
    }



    public void setTitle(String title) {

        mTitle = title == null ? "UNDEFINED" : title;
    }



    public String getModel() {

        return mModel;
    }



    public void setModel(String model) {

        mModel = model == null ? mTitle : model;
    }



    public Manufacturer getManufacturer() {

        return mManufacturer == null ? new Manufacturer() : mManufacturer;
    }



    public void setManufacturer(Manufacturer manufacturer) {

        mManufacturer = manufacturer;
    }



    public LocalDateTime getManufactureDate() {

        return mManufactureDate;
    }



    public void setManufactureDate(LocalDateTime manufactureDate) {

        mManufactureDate = manufactureDate;
    }



    public short getFlightsAmount() {

        return mFlightsAmount;
    }



    public void setFlightsAmount(short flightsAmount) {

        mFlightsAmount = flightsAmount;
    }



    public short getSeatsCount() {

        return mSeatsCount;
    }



    public void setSeatsCount(short seatsCount) {

        mSeatsCount = seatsCount < 1 ? -1 : seatsCount;
    }



    public short getMaxFlightDistance() {

        return mMaxFlightDistance;
    }



    public void setMaxFlightDistance(short maxFlightDistance) {

        mSeatsCount = maxFlightDistance < 1 ? -1 : maxFlightDistance;
    }
    // endregion

}
