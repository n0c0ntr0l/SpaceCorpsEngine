package com.spacecorps.map.Players;

import com.spacecorps.map.Ships.Ship;

import java.util.ArrayList;

public class Player {
    private String playerID;
    private String playerName;
    private ArrayList<Ship> listOfOwnedShips;

    public Player(String playerID){
        this.playerID = playerID;
        listOfOwnedShips = new ArrayList<>();
    }


}
