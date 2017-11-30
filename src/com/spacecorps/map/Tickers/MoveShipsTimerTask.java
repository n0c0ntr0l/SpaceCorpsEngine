package com.spacecorps.map.Tickers;

import com.spacecorps.map.MainEngine;
import com.spacecorps.map.Ships.Ship;

import java.util.ArrayList;
import java.util.TimerTask;

public class MoveShipsTimerTask extends TimerTask {

    private volatile ArrayList<Ship> listOfAllMovingShips;
    int processors;
    private boolean areThreadsRunning;

    public MoveShipsTimerTask(ArrayList<Ship> listOfAllMovingShips) {
        processors = Runtime.getRuntime().availableProcessors() - 1;
        this.listOfAllMovingShips = listOfAllMovingShips;
    }

    @Override
    public void run() {
        if (listOfAllMovingShips.size() > 0) {
            ArrayList<Ship>[] listOfShipsDividedIntoThreads = new ArrayList[processors];
            for (int i = 0; i < listOfShipsDividedIntoThreads.length; i++) {
                listOfShipsDividedIntoThreads[i] = new ArrayList<Ship>();
            }

            for (int i = 0; i < listOfAllMovingShips.size(); i++) {
                listOfShipsDividedIntoThreads[i % processors].add(listOfAllMovingShips.get(i));
            }
            MoveShipInnerThreadClass[] threads = new MoveShipInnerThreadClass[processors];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new MoveShipInnerThreadClass(listOfShipsDividedIntoThreads[i]);
            }
            for (int i = 0; i < threads.length; i++) {
                threads[i].run();
            }
            boolean threadsFinished = false;
            while (!threadsFinished) {
                int temp = 0;
                for (int i = 0; i < threads.length; i++) {
                    if (threads[i].getThreadFinished()) {
                        temp++;
                    }
                }
                if (temp >= threads.length - 1) {
                    threadsFinished = true;
                } else {
                    temp = 0;
                }
            }
        }
    }

    class MoveShipInnerThreadClass implements Runnable {
        private volatile ArrayList<Ship> listOfShips;
        private volatile boolean threadFinished = false;

        public MoveShipInnerThreadClass(ArrayList<Ship> listOfShips) {
            this.listOfShips = listOfShips;
        }

        @Override
        public void run() {
            for (Ship ship : listOfShips) {
                ship.moveShip();
                if (MainEngine.LOGLEVEL == 2) {
                    System.out.println(ship.getShipID() + ": is at " + ship.getLocation().toString());
                }
            }
            threadFinished = true;
        }

        public boolean getThreadFinished() {
            return threadFinished;
        }

    }
}
