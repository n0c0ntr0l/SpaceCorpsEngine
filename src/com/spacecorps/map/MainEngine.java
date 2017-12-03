package com.spacecorps.map;

import com.spacecorps.map.Players.Player;
import com.spacecorps.map.Sector.Sector;
import com.spacecorps.map.Ships.Ship;
import com.spacecorps.map.Ships.ShipFactory;
import com.spacecorps.map.Tickers.MoveShipsTimerTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

public class MainEngine {

    public static int LOGLEVEL = 1;
    public static int TICKRATEMILLISECONDS = 5;


    private ArrayList<Player> listOfPlayers;
    private GalaxyMapTop galaxyMapTop = new GalaxyMapTop();
    private final Sector[][][] sectorMatrix;
    private ArrayList<Player> listOfAllPlayers;
    private HashMap<String, Player> mapOfAllPlayers;
    private volatile ArrayList<Ship> listOfAllShips;
    private volatile ConcurrentHashMap<String, Ship> mapOfAllShips;
    protected volatile ArrayList<Ship> listOfAllMovingShips;

    private Timer mainTicker;
    private Timer calculateFights;
    private Timer moveShips;

    public MainEngine(){
        mapOfAllPlayers = new HashMap<String, Player>();
        listOfAllPlayers = new ArrayList<>();
        listOfAllShips = new ArrayList<>();
        mapOfAllShips = new ConcurrentHashMap<String, Ship>();
        listOfAllMovingShips = new ArrayList<>();
        galaxyMapTop.generateGalaxy();
        sectorMatrix = galaxyMapTop.getSectorMatrix();
        mainTicker = new Timer("MainTimer",false);
        moveShips = new Timer("MoveShips",false);
        calculateFights = new Timer("Fight Timer");
    }

    public void startGame() {
        MoveShipsTimerTask moveShipsTimerTask = new MoveShipsTimerTask(listOfAllMovingShips);
        moveShips.scheduleAtFixedRate(moveShipsTimerTask, 0, TICKRATEMILLISECONDS);
    }

    public void addPlayer(String name){
        Player player = new Player(name);
        listOfAllPlayers.add(player);
        mapOfAllPlayers.put(name, player);
    }

    public void addShip(String playerName) {
        Ship ship = ShipFactory.createFastShipInRandomSector(playerName, mapOfAllPlayers, this);
        listOfAllShips.add(ship);
        mapOfAllShips.put(ship.getShipID(), ship);
    }

    public void sendAllShipsToCenter() {
        for (Ship ship : listOfAllShips) {
            ship.setMovingTarget(new XYZcoord(GalaxyMapTop.HALFPOINT, GalaxyMapTop.HALFPOINT, GalaxyMapTop.HALFPOINT));
        }
    }

    public void addSectorToHotSectors(Sector sector) {
        galaxyMapTop.getListOfHotSectors().add(sector);
    }



    public Sector[][][] getSectorMatrix() {
        return sectorMatrix;
    }

    public Sector getSector(int x, int y, int z) {
        return sectorMatrix[x][y][z];
    }

    public void addShipToListOfMovingShips(Ship ship) {
        this.listOfAllMovingShips.add(ship);
    }

    public void removeShipFromListOfMovingShip(Ship ship) {
        this.listOfAllMovingShips.remove(ship);
    }

    public static void main(String[] args){
        MainEngine main = new MainEngine();
        main.addPlayer("Player1");
        main.addPlayer("Player2");
        main.addShip("Player1");
        main.addShip("Player2");
        main.startGame();
        main.sendAllShipsToCenter();
    }
}
