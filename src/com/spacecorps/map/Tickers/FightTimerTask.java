package com.spacecorps.map.Tickers;

import com.spacecorps.map.Sector.Sector;

import java.util.ArrayList;
import java.util.TimerTask;

public class FightTimerTask extends TimerTask{

    private volatile ArrayList<Sector> listOfHotSectors;
    private final int PROCESSORS;


    public FightTimerTask(ArrayList<Sector> listOfHotSectors) {
        this.listOfHotSectors = listOfHotSectors;
        PROCESSORS = Runtime.getRuntime().availableProcessors() - 1;

    }

    @Override
    public void run() {

    }

    class BattleThreadInnerClass implements Runnable {
        private volatile ArrayList<Sector> listOfSectors;

        @Override
        public void run() {

        }
    }
}
