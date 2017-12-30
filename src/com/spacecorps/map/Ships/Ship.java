package com.spacecorps.map.Ships;

import com.spacecorps.map.Sector.Sector;
import com.spacecorps.map.XYZcoord;

import java.util.Calendar;

public interface Ship {
    void addShipToSector(XYZcoord coord);

    void removeShipFromSector();

    TargetStatus getTargetStatus();

    void setTargetStatus(TargetStatus targetStatus);

    void takeDamage(int HullDamage, int ShieldDamage);

    void setMovingTarget(XYZcoord shipDestination);

    void moveShip();

    void setStartJourneyTime();

    void setEndOfJourneyTime();

    int numberOfSecondsTravelled();

    boolean isShipTravelling();

    String getShipID();

    XYZcoord getLocation();
}
