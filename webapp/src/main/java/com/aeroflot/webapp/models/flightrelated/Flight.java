package com.aeroflot.webapp.models.flightrelated;


import com.aeroflot.webapp.models.countryrelated.Country;
import com.aeroflot.webapp.models.personrelated.User;
import com.aeroflot.webapp.models.transportrelated.Plane;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.aeroflot.webapp.misc.ModelsContext.TIMESTAMP_FORMATTER;



@Entity
public class Flight implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    protected long id;

    @ManyToOne(
      optional = false,
      targetEntity = User.class,
      fetch = FetchType.LAZY
    )
    @JoinColumn(
      name = "created_by",
      referencedColumnName = "id"
    )
    protected User mAdministrator;

    @Column(name = "status")
    protected String mStatus;

    @Column(name = "when_registered")
    protected LocalDateTime mWhenRegisteredDateTime;

    @Column(name = "departure_datetime")
    protected LocalDateTime mDepartureDateTime;

    @Column(name = "arrival_datetime")
    protected LocalDateTime mArrivalDateTime;

    @ManyToOne(
      optional = false,
      targetEntity = Country.class,
      fetch = FetchType.LAZY
    )
    @JoinColumn(
      name = "departure_point",
      referencedColumnName = "id"
    )
    protected Country mDeparturePoint;

    @ManyToOne(
      optional = false,
      targetEntity = Country.class,
      fetch = FetchType.LAZY
    )
    @JoinColumn(
      name = "arrival_point",
      referencedColumnName = "id"
    )
    protected Country mArrivalPoint;

    @ManyToOne(
      optional = false,
      targetEntity = Plane.class,
      fetch = FetchType.LAZY
    )
    @JoinColumn(
      name = "plane",
      referencedColumnName = "id"
    )
    protected Plane mPlane;



    // region getters/setters
    public long getId() {

        return id;
    }



    public void setId(long id) {

        this.id = id < 1 ? -1 : id;
    }



    public User getAdministrator() {

        return mAdministrator;
    }



    public void setAdministrator(User administrator) {

        mAdministrator = administrator;
    }



    public String getStatus() {

        return mStatus;
    }



    public void setStatus(String status) {

        mStatus = status;
    }



    public LocalDateTime getWhenRegisteredDateTime() {

        return mWhenRegisteredDateTime;
    }



    public void setWhenRegisteredDateTime(String whenRegisteredDateTime) {

        mWhenRegisteredDateTime = LocalDateTime.parse(
          whenRegisteredDateTime,
          TIMESTAMP_FORMATTER
        );
    }



    public LocalDateTime getDepartureDateTime() {

        return mDepartureDateTime;
    }



    public void setDepartureDateTime(String departureDateTime) {

        mDepartureDateTime = LocalDateTime.parse(
          departureDateTime,
          TIMESTAMP_FORMATTER
        );
    }



    public LocalDateTime getArrivalDateTime() {

        return mArrivalDateTime;
    }



    public void setArrivalDateTime(String arrivalDateTime) {

        mArrivalDateTime = LocalDateTime.parse(
          arrivalDateTime,
          TIMESTAMP_FORMATTER
        );
    }



    public Country getDeparturePoint() {

        return mDeparturePoint;
    }



    public void setDeparturePoint(Country departurePoint) {

        mDeparturePoint = departurePoint;
    }



    public Country getArrivalPoint() {

        return mArrivalPoint;
    }



    public void setArrivalPoint(Country arrivalPoint) {

        mArrivalPoint = arrivalPoint;
    }



    public Plane getPlane() {

        return mPlane;
    }



    public void setPlane(Plane plane) {

        mPlane = plane;
    }
    // endregion

}
