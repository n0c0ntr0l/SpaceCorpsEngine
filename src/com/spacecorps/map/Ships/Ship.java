package com.spacecorps.map.Ships;

import com.spacecorps.map.Players.Player;
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
    private double distanceToTargetCurrent;



    private TargetStatus targetStatus;

    public Ship(String shipID, Player shipOwner, int x, int y, int z, int shipHullTotal, int shipShieldTotal, int shipHullDamagePerSecond, int shipShieldDamagePerSecond, int shipMovementSpeed){
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
    }

    public void moveShip(){
        XYZcoord newPosition = Util.getPositionGivenSpeedAndVelocityDirection(this.shipMovementSpeed,this.location,this.shipDestination);



    }





}
