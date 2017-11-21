package com.spacecorps.map;

import com.spacecorps.map.Players.Player;
import com.spacecorps.map.Ships.Ship;

import java.util.ArrayList;
import java.util.Timer;

public class MainEngine {

    private ArrayList<Player> listOfPlayers;
    private GalaxyMapTop galaxyMapTop = new GalaxyMapTop();
    private ArrayList<Player> listOfAllPlayers;
    private ArrayList<Ship> listOfAllShips;
    private ArrayList<Ship> listOfAllMovingShips;
    Timer mainTicker;
    Timer calculateFights;
    Timer moveShips;


    public MainEngine(){
        listOfAllPlayers = new ArrayList<>();
        listOfAllShips = new ArrayList<>();
        galaxyMapTop.generateGalaxy();

        mainTicker = new Timer("MainTimer",false);
        calculateFights = new Timer("Fight Timer");
    }

    public void addPlayer(String name){

    }

    public static void main(String[] args){

    }
}
