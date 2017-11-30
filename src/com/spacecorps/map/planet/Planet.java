package com.spacecorps.map.planet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static com.spacecorps.map.planet.ElementalResources.*;
import static com.spacecorps.map.planet.PlanetType.ASTEROID;
import static com.spacecorps.map.planet.PlanetType.GAS;
import static com.spacecorps.map.planet.PlanetType.ROCK;

public class Planet {
    PlanetType planetType;
    private double planetPositionX;
    private double planetPositionY;
    private double planetOrbitalSpeed;

    public HashMap<ElementalResources, Integer> getResourceNumbers() {
        return resourceNumbers;
    }

    ArrayList<ElementalResources> listOfAvailableElements = new ArrayList<>();
    HashMap<ElementalResources,Integer> resourceNumbers = new HashMap<>();



    public void generatePlanet(PlanetType planetType) {
        this.planetType = planetType;
        generateResourcesPresentOnPlanet();

    }

    public void generateResourcesPresentOnPlanet() {
        Random rng = new Random();
        if (planetType == ROCK) {
            if (rng.nextDouble() < 0.5) {
                listOfAvailableElements.add(ElementalResources.HYDROGEN);
            }
            if (rng.nextDouble() < 0.3) {
                listOfAvailableElements.add(ElementalResources.HELIUM);
            }
            if (rng.nextDouble() < 0.3) {
                listOfAvailableElements.add(ElementalResources.CARBON);
            }
            if (rng.nextDouble() < 0.3) {
                listOfAvailableElements.add(ElementalResources.NITROGEN);
            }
            if (rng.nextDouble() < 0.1) {
                listOfAvailableElements.add(ALUMINIUM);
            }
            if (rng.nextDouble() < 0.2) {
                listOfAvailableElements.add(ElementalResources.OXYGEN);
            }
            if (rng.nextDouble() < 0.05) {
                listOfAvailableElements.add(ElementalResources.IRON);
            }
            if (rng.nextDouble() < 0.01) {
                listOfAvailableElements.add(ElementalResources.TITANIUM);
            }
            if (rng.nextDouble() < 0.1) {
                listOfAvailableElements.add(ElementalResources.COPPER);
            }
            if (rng.nextDouble() < 0.1) {
                listOfAvailableElements.add(ElementalResources.SILICON);
            }
            if (rng.nextDouble() < 0.01) {
                listOfAvailableElements.add(ElementalResources.URANIUM);
            }
            if (rng.nextDouble() < 0.002) {
                listOfAvailableElements.add(ElementalResources.POLONIUM);
            }
            if (rng.nextDouble() < 0.01) {
                listOfAvailableElements.add(ElementalResources.GOLD);
            }
        } else if (planetType == GAS) {
            if (rng.nextDouble() < 0.8) {
                listOfAvailableElements.add(ElementalResources.HYDROGEN);
            }
            if (rng.nextDouble() < 0.4) {
                listOfAvailableElements.add(ElementalResources.HELIUM);
            }
            if (rng.nextDouble() < 0.3) {
                listOfAvailableElements.add(ElementalResources.NITROGEN);
            }
            if (rng.nextDouble() < 0.2) {
                listOfAvailableElements.add(ElementalResources.OXYGEN);
            }
        } else if (planetType == ASTEROID){
            if(rng.nextDouble() < 0.03){
                listOfAvailableElements.add(ElementalResources.GOLD);
            }
            if(rng.nextDouble() < 0.005){
                listOfAvailableElements.add(ElementalResources.POLONIUM);
            }
            if(rng.nextDouble() < 0.3){
                listOfAvailableElements.add(ElementalResources.SILICON);
            }
            if(rng.nextDouble() < 0.2){
                listOfAvailableElements.add(ElementalResources.COPPER);
            }
            if(rng.nextDouble() < 0.2){
                listOfAvailableElements.add(ElementalResources.IRON);
            }
            if(rng.nextDouble() < 0.03){
                listOfAvailableElements.add(ElementalResources.URANIUM);
            }
            if(rng.nextDouble() < 0.2){
                listOfAvailableElements.add(ALUMINIUM);
            }
            if(rng.nextDouble() < 0.02){
                listOfAvailableElements.add(ElementalResources.TITANIUM);
            }
        }
        generateNumberOfResource(this.planetType);
    }

    private void generateNumberOfResource(PlanetType planetType){
        if(planetType == ROCK){
            for(ElementalResources element : this.listOfAvailableElements ){
                if(element == ALUMINIUM){
                    this.resourceNumbers.put(ALUMINIUM,generateResourceInt(5));
                }
                if(element == ElementalResources.HYDROGEN){
                    this.resourceNumbers.put(HYDROGEN,generateResourceInt(20));
                }
                if(element == ElementalResources.HELIUM){
                    this.resourceNumbers.put(HELIUM,generateResourceInt(15));
                }
                if(element == ElementalResources.NITROGEN){
                    this.resourceNumbers.put(NITROGEN,generateResourceInt(15));
                }
                if(element == ElementalResources.CARBON){
                    this.resourceNumbers.put(CARBON,generateResourceInt(20));
                }
                if(element == ElementalResources.TITANIUM){
                    this.resourceNumbers.put(TITANIUM,generateResourceInt(3));
                }
                if(element == ElementalResources.OXYGEN){
                    this.resourceNumbers.put(OXYGEN,generateResourceInt(20));
                }
                if(element == ElementalResources.IRON){
                    this.resourceNumbers.put(IRON,generateResourceInt(7));
                }
                if(element == ElementalResources.COPPER){
                    this.resourceNumbers.put(COPPER,generateResourceInt(10));
                }
                if(element == ElementalResources.SILICON){
                    this.resourceNumbers.put(SILICON,generateResourceInt(15));
                }
                if(element == ElementalResources.URANIUM){
                    this.resourceNumbers.put(URANIUM,generateResourceInt(2));
                }
                if(element == ElementalResources.POLONIUM){
                    this.resourceNumbers.put(POLONIUM,generateResourceInt(1));
                }
                if(element == GOLD){
                    this.resourceNumbers.put(GOLD,generateResourceInt(1));
                }
            }

        } else if (planetType == ASTEROID){
            for(ElementalResources element : this.listOfAvailableElements ){
                if(element == ALUMINIUM){
                    this.resourceNumbers.put(ALUMINIUM,generateResourceInt(7));
                }
                if(element == ElementalResources.TITANIUM) {
                    this.resourceNumbers.put(TITANIUM, generateResourceInt(3));
                }
                if(element == ElementalResources.IRON){
                    this.resourceNumbers.put(IRON,generateResourceInt(15));
                }
                if(element == ElementalResources.COPPER){
                    this.resourceNumbers.put(COPPER,generateResourceInt(13));
                }
                if(element == ElementalResources.SILICON){
                    this.resourceNumbers.put(SILICON,generateResourceInt(20));
                }
                if(element == ElementalResources.URANIUM){
                    this.resourceNumbers.put(URANIUM,generateResourceInt(2));
                }
                if(element == ElementalResources.POLONIUM){
                    this.resourceNumbers.put(POLONIUM,generateResourceInt(2));
                }
                if(element == GOLD){
                    this.resourceNumbers.put(GOLD,generateResourceInt(2));
                }
            }
        } else if (planetType == GAS){
            for(ElementalResources element : this.listOfAvailableElements ){
                if (element == HYDROGEN) {
                    this.resourceNumbers.put(HYDROGEN,generateResourceInt(70));
                }
                if (element == HELIUM) {
                    this.resourceNumbers.put(HELIUM,generateResourceInt(60));
                }
                if (element == OXYGEN) {
                    this.resourceNumbers.put(OXYGEN,generateResourceInt(50));
                }
                if (element == NITROGEN) {
                    this.resourceNumbers.put(NITROGEN,generateResourceInt(30));
                }
            }
        }
    }

    private int generateResourceInt(int maxResourceNumber){
        Random rng = new Random();
        Double result = rng.nextDouble() * maxResourceNumber * 1000000;
        return result.intValue();

    }


}
