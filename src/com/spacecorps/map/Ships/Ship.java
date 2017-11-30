package com.spacecorps.map.Ships;

import com.spacecorps.map.MainEngine;
import com.spacecorps.map.Players.Player;
import com.spacecorps.map.Sector.Sector;
import com.spacecorps.map.Util;
import com.spacecorps.map.XYZcoord;

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

    private final MainEngine mainCallBack;



    private TargetStatus targetStatus;

    public Ship(String shipID, Player shipOwner, int x, int y, int z, int shipHullTotal, int shipShieldTotal, int shipHullDamagePerSecond, int shipShieldDamagePerSecond, double shipMovementSpeed, MainEngine mainCallBack) {
        this.shipID = shipID;
        this.shipOwner = shipOwner;
        this.shipHullTotal = shipHullTotal;
        this.shipShieldTotal = shipShieldTotal;
        this.shipHullCurrent = shipHullTotal;
        this.shipShieldCurrent = shipShieldTotal;
        location = new XYZcoord(x,y,z);
        this.shipHullDamagePerSecond = shipHullDamagePerSecond;
        this.shipShieldDamagePerSecond = shipShieldDamagePerSecond;
        this.isShipDead = false;
        this.shipMovementSpeed = shipMovementSpeed;
        this.mainCallBack = mainCallBack;
        addShipToSector(location);
    }

    private void addShipToSector(XYZcoord coord) {
        Sector sectorFromLocation = mainCallBack.getSector(coord.getxAbsolute(), coord.getyAbsolute(), coord.getzAbsolute());
        sectorFromLocation.addShipToSector(this);
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
        	distanceToTargetTotal = Util.calculateDistanceBetweenTwoPoint(location, shipDestination);
        	this.distanceToTargetCurrent = distanceToTargetTotal;
        } else {
        	distanceToTargetCurrent = Util.calculateDistanceBetweenTwoPoint(location, shipDestination);
        }
        if(newPosition.equals(this.shipDestination)){
        	this.isShipTraveling = false;
        	this.shipOrigin = newPosition;
            mainCallBack.removeShipFromListOfMovingShip(this);
            if (MainEngine.LOGLEVEL > 0) {
                System.out.println(this.getShipID() + " has arrived at destination");
            }
        }
        if (!newPosition.equals(location)) {
            this.removeShipFromSector();
            this.addShipToSector(newPosition);
            if (MainEngine.LOGLEVEL > 0) {
                System.out.println(this.getShipID() + " has moved from sector " + location.getxAbsolute() + "," + location.getyAbsolute() + "," + location.getzAbsolute() +
                        " to " + newPosition.getxAbsolute() + "," + newPosition.getyAbsolute() + "," + newPosition.getzAbsolute());
            }
        }
        this.location = newPosition;
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
