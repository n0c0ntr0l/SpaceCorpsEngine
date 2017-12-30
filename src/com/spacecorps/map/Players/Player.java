package com.spacecorps.map.Players;

import com.spacecorps.map.Ships.Ship;

import java.util.ArrayList;

public class Player {
    private String playerID;
    private String playerName;
    private ArrayList<Ship> listOfOwnedShips;
    private long amountOfMoney;
    private String nameOfCompany;
    private double percentageOfStockPublicallyAvailable;

    public Player(String playerID, String playerName) {
        this.playerID = playerID;
        this.playerName = playerName;
        listOfOwnedShips = new ArrayList<>();
    }

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public void addShip(Ship ship) {
        listOfOwnedShips.add(ship);
    }

    public void removeShip(Ship ship) {
        listOfOwnedShips.remove(ship);
    }




}
