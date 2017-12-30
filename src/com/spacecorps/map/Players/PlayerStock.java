/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacecorps.map.Players;

/**
 *
 * @author Jason
 */
public class PlayerStock {
    
    private final Player player;
    private final String stockSymbol;
    private double stockPrice;
    private int totalNumberOfShares;
    
    
    public PlayerStock(String stockSymbol, Player player){
        this.player = player;
        this.stockSymbol = stockSymbol;
    }
    
}
