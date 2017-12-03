package com.spacecorps.map.Ships;

import com.spacecorps.map.MainEngine;
import com.spacecorps.map.Players.Player;
import com.spacecorps.map.Sector.Sector;
import com.spacecorps.map.Util;
import com.spacecorps.map.XYZcoord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ship {
    private XYZcoord location;
    private final String shipID;
    private final Player shipOwner;
    private final int shipHullTotal;
    private final int shipShieldTotal;
    private int shipHullCurrent;
    private int shipShieldCurrent;
    private int shipHullDamagePerSecond;
    private int shipShieldDamagePerSecond;
    private double shipMovementSpeed;
    private boolean isShipTraveling;
    private XYZcoord shipDestination;
    private XYZcoord shipOrigin;
    private Ship targetShip;
    private boolean isShipDead;
    private double distanceToTargetTotal;
    private double distanceToTargetCurrent; //to be used to state how far from sector
    private Sector currentShipSector;
    private long endOfJourneyTime;
    private long startJourneyTime;

    private final MainEngine mainCallBack;



    private TargetStatus targetStatus;

    public Ship(String shipID, Player shipOwner, int x, int y, int z, int shipHullTotal, int shipShieldTotal, int shipHullDamagePerSecond, int shipShieldDamagePerSecond, double shipMovementSpeed, MainEngine mainCallBack) {
        this.shipID = shipID;
        this.shipOwner = shipOwner;
        this.shipHullTotal = shipHullTotal;
        this.shipShieldTotal = shipShieldTotal;
        this.shipHullCurrent = shipHullTotal;
        this.shipShieldCurrent = shipShieldTotal;
        this.location = new XYZcoord(x, y, z);
        this.shipHullDamagePerSecond = shipHullDamagePerSecond;
        this.shipShieldDamagePerSecond = shipShieldDamagePerSecond;
        this.isShipDead = false;
        this.shipMovementSpeed = shipMovementSpeed;
        this.mainCallBack = mainCallBack;
        addShipToSector(location);
    }

    private void addShipToSector(XYZcoord coord) {
        currentShipSector = mainCallBack.getSector(coord.getxAbsolute(), coord.getyAbsolute(), coord.getzAbsolute());
        currentShipSector.addShipToSector(this);
    }

    private void removeShipFromSector() {
        Sector sectorFromLocation = mainCallBack.getSector(location.getxAbsolute(), location.getyAbsolute(), location.getzAbsolute());
        sectorFromLocation.removeShipFromSector(this);
    }

    public TargetStatus getTargetStatus() {
        return targetStatus;
    }

    public void setTargetStatus(TargetStatus targetStatus) {
        this.targetStatus = targetStatus;
    }

    public void takeDamage(int HullDamage, int ShieldDamage){
        if(shipShieldCurrent == 0){
            shipHullCurrent =- HullDamage;
        }
        shipShieldCurrent =- ShieldDamage;
        if(shipHullCurrent <= 0){
            isShipDead = true;
        }
    }

    public void setMovingTarget(XYZcoord shipDestination){
        this.shipDestination = shipDestination;
        distanceToTargetTotal = Util.calculateDistanceBetweenTwoPoint(location,shipDestination);
        mainCallBack.addShipToListOfMovingShips(this);
    }

    public void moveShip(){
        XYZcoord newPosition = Util.getPositionGivenSpeedAndVelocityDirection(this.shipMovementSpeed,this.location,this.shipDestination);
        double distanceToTargetCurrent;
        if(!this.isShipTraveling){
        	this.isShipTraveling = true;
            setStartJourneyTime();
            distanceToTargetTotal = Util.calculateDistanceBetweenTwoPoint(location, shipDestination);
        	this.distanceToTargetCurrent = distanceToTargetTotal;
        } else {
        	distanceToTargetCurrent = Util.calculateDistanceBetweenTwoPoint(location, shipDestination);
        }
        if(newPosition.equals(this.shipDestination)){
        	this.isShipTraveling = false;
        	this.shipOrigin = newPosition;
            mainCallBack.removeShipFromListOfMovingShip(this);
            setEndOfJourneyTime();
            if (MainEngine.LOGLEVEL > 0) {
                System.out.println(this.getShipID() + " has arrived at destination" + newPosition.xAbsolute + "," + newPosition.getyAbsolute() + "," + newPosition.getzAbsolute());
                System.out.println(this.getShipID() + " took " + numberOfSecondsTravelled() + " seconds to reach the destination");
            }
        }
        if (!newPosition.equals(location)) {
            if (MainEngine.LOGLEVEL > 0) {
                //System.out.println(this.getShipID() + " has moved from sector " + location.getxAbsolute() + "," + location.getyAbsolute() + "," + location.getzAbsolute() +
                //        " to " + newPosition.getxAbsolute() + "," + newPosition.getyAbsolute() + "," + newPosition.getzAbsolute());
                //System.out.println(this.getShipID() + " has moved from sector " + location.toString() + " to " + newPosition.toString());
            }
            this.currentShipSector.removeShipFromSector(this);
            this.addShipToSector(newPosition);
        }
        if (this.currentShipSector.getNumOfShipsInSector() > 1) {
            this.mainCallBack.addSectorToHotSectors(this.currentShipSector);
        }
        this.location = newPosition;
    }

    private void setStartJourneyTime() {
        Calendar cal = Calendar.getInstance();
        this.startJourneyTime = cal.getTime().getTime();
    }

    private void setEndOfJourneyTime() {
        Calendar cal = Calendar.getInstance();
        this.endOfJourneyTime = cal.getTimeInMillis();
    }

    private int numberOfSecondsTravelled() {
        return (int) ((this.endOfJourneyTime - this.startJourneyTime) / 1000);
    }
    
    public boolean isShipTravelling(){
    	return isShipTraveling;
    }

    public String getShipID() {
        return this.shipID;
    }

    public XYZcoord getLocation() {
        return location;
    }
}
