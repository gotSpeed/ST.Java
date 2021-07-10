package com.aeroflot.webapp.models.transportrelated;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.aeroflot.webapp.misc.ModelsContext.DATE_PATTERN;



@Entity
public class Plane {

    @Id
    @GeneratedValue
    @Column(name = "id")
    protected long id;

    @Column(name = "title")
    protected String mTitle;

    @Column(name = "model")
    protected String mModel;

    @ManyToOne(
      targetEntity = Manufacturer.class
    )
    @JoinColumn(
      name = "manufacturer",
      referencedColumnName = "id"
    )
    protected Manufacturer mManufacturer;

    @Column(name = "manufacture_date")
    protected LocalDate mManufactureDate;

    @Column(name = "flights_amount")
    protected short mFlightsAmount;

    @Column(name = "seats_count")
    protected short mSeatsCount;

    @Column(name = "max_flight_distance")
    protected short mMaxFlightDistance;



    // region getters/setters
    public long getId() {

        return id;
    }



    public void setId(long id) {

        this.id = id < 1 ? -1 : id;
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



    public LocalDate getManufactureDate() {

        return mManufactureDate;
    }



    public void setManufactureDate(String manufactureDate) {

        mManufactureDate = LocalDate.parse(
          manufactureDate,
          DateTimeFormatter.ofPattern(DATE_PATTERN)
        );
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



    @Override
    public String toString() {

        return "(" + mTitle + ") " + mModel;
    }

}
