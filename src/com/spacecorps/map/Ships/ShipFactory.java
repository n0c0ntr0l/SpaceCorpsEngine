package com.spacecorps.map.Ships;

import com.spacecorps.map.GalaxyMapTop;
import com.spacecorps.map.MainEngine;
import com.spacecorps.map.Players.Player;

import java.util.HashMap;
import java.util.Random;

public class ShipFactory {


    public static Ship createFastShipAtCenter(String playerName, HashMap<String, Player> mapOfAllPlayers, MainEngine mainCallBack) {
        return new Ship(
                mapOfAllPlayers.size() + playerName,
                mapOfAllPlayers.get(playerName),
                0, 0, 0, 1000, 1000, 1000, 10,
                0.03d, mainCallBack);
    }

    public static Ship createFastShipInRandomSector(String playerName, HashMap<String, Player> mapOfAllPlayers, MainEngine mainCallBack) {
        Random rng = new Random();
        return new Ship(mapOfAllPlayers.size() + playerName,
                mapOfAllPlayers.get(playerName),
                rng.nextInt(GalaxyMapTop.GALAXYGRIDSIZE), rng.nextInt(GalaxyMapTop.GALAXYGRIDSIZE), rng.nextInt(GalaxyMapTop.GALAXYGRIDSIZE), 1000, 1000, 1000, 10,
                0.03d, mainCallBack);
    }
}
