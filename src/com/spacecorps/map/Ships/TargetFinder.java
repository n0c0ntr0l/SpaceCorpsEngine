package com.spacecorps.map.Ships;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TargetFinder {

    private final Ship ship;
    private Ship currentShipTarget;
    private volatile HashMap<String, Ship> mapOfShipsInCurrentSector;
    private HashMap<String, Ship> mapOfNonPlayerShipsInSector;

    public TargetFinder(Ship ship, HashMap mapOfShipsInCurrentSector) {
        this.ship = ship;
        this.mapOfShipsInCurrentSector = mapOfShipsInCurrentSector;
    }

    //
    public boolean setTargetShipByShipID(String targetShipID) {
        currentShipTarget = mapOfShipsInCurrentSector.get(targetShipID);
        return currentShipTarget != null;
    }

    private void generateMapOfShipsExcludingPlayerOwnedShips() {
        mapOfNonPlayerShipsInSector = new HashMap();
        for (Map.Entry<String, Ship> entry : this.mapOfShipsInCurrentSector.entrySet()) {
            if (!(entry.getValue().getPlayer() == this.ship.getPlayer())) {
                this.mapOfNonPlayerShipsInSector.put(entry.getKey(), entry.getValue());
            }
        }
    }

    //currently picks a random target
    public boolean pickRandomTarget() {
        Random rng = new Random();
        boolean doesShipBelongToPlayer = false;
        ArrayList<Ship> listOfNonPlayerShips = generateArrayListOfNonPlayerShips();
        if (listOfNonPlayerShips.size() < 1) {
            return false;
        }
        int shipToPick = rng.nextInt(listOfNonPlayerShips.size());
        this.currentShipTarget = listOfNonPlayerShips.get(shipToPick);
        return true;
    }

    private ArrayList<Ship> generateArrayListOfNonPlayerShips() {
        ArrayList<Ship> listOfNonPlayerShips = new ArrayList();
        for (Map.Entry<String, Ship> entry : this.mapOfNonPlayerShipsInSector.entrySet()) {
            listOfNonPlayerShips.add(entry.getValue());
        }
        return listOfNonPlayerShips;
    }
}
