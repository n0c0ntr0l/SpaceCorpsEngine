package com.spacecorps.map;
import com.spacecorps.map.Ships.Ship;

import java.util.ArrayList;
import java.util.TimerTask;

public class MoveShipsTimerTask extends TimerTask {

    private ArrayList<Ship> listOfAllMovingShips;
    int processors;
    
    public MoveShipsTimerTask(ArrayList<Ship> listOfAllMovingShips){
    processors = Runtime.getRuntime().availableProcessors();

    	
    }

    @Override
    public void run() {
    	ArrayList<Ship>[] processList = new ArrayList[processors];
    		for(int i = 0; i < processList.length; i++){
    			processList[i] = new ArrayList<Ship>();
    	}

 	
    	
    }
    
    class MoveShipThread implements Runnable{
    	
		@Override
		public void run() {
			
			
		}
    	
    }
}
