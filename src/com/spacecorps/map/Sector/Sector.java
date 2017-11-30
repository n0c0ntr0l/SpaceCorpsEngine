package com.spacecorps.map.Sector;

import com.spacecorps.map.Ships.Ship;
import com.spacecorps.map.XYZcoord;
import com.spacecorps.map.planet.ElementalResources;
import com.spacecorps.map.planet.ElementalResourcesFactoryConstructors;
import com.spacecorps.map.planet.Planet;
import com.spacecorps.map.planet.PlanetType;

import java.util.*;

import static com.spacecorps.map.planet.PlanetType.ASTEROID;
import static com.spacecorps.map.planet.PlanetType.GAS;
import static com.spacecorps.map.planet.PlanetType.ROCK;

public class Sector {
    private XYZcoord location;
    protected StarProperties starProperties;
    protected ArrayList<Planet> planetArrayList;
    private ArrayList<Ship> listOfShipsInSector;
    private HashMap<String, Ship> mapOfShipsInSector;
    private boolean emptySector;
    private int numberOfRockPlanets;
    private int numberOfAsteroidBelts;
    private int numberOfGasPlanets;
    private int numOfPlanets;



    public boolean isEmptySector() {
        return emptySector;
    }

    public HashMap<ElementalResources, Integer> getTotalResourcesInSector() {
        if (totalResourcesInSector == null) {
            totalResourcesInSector = ElementalResourcesFactoryConstructors.createElementalResourceAmountHashMap();
        }
        for (Planet planet : planetArrayList) {
            HashMap<ElementalResources, Integer> tempMap = planet.getResourceNumbers();
            Iterator it = tempMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry resourceAndValue = (Map.Entry) it.next();
                ElementalResources element = (ElementalResources) resourceAndValue.getKey();
                int tempElementAmount = (Integer) resourceAndValue.getValue();
                totalResourcesInSector.put(element, totalResourcesInSector.get(element) + tempElementAmount);
            }
        }
        return totalResourcesInSector;
    }

    private HashMap<ElementalResources, Integer> totalResourcesInSector;

    public int getNumberOfRockPlanets() {
        return numberOfRockPlanets;
    }

    public int getNumberOfAsteroidBelts() {
        return numberOfAsteroidBelts;
    }

    public int getNumberOfGasPlanets() {
        return numberOfGasPlanets;
    }

    public Sector(int x, int y, int z, boolean emptySector) {
        this.location = new XYZcoord(x, y, z);
        this.emptySector = emptySector;
        this.listOfShipsInSector = new ArrayList<>();
        if (!emptySector) {
            planetArrayList = new ArrayList<>();
        }
    }

    public void generateSector() {
        this.emptySector = emptySector;
        if (!emptySector) {
            numberOfAsteroidBelts = 0;
            numberOfGasPlanets = 0;
            numberOfRockPlanets = 0;
            generatePlanets();
        }
    }

    private void generatePlanets() {
        double randomSeed;
        Random randomNumberGenerator = new Random();
        randomSeed = randomNumberGenerator.nextDouble();
        this.numOfPlanets = numOfPlanets(randomSeed);
        for (int i = 0; i < this.numOfPlanets; i++) {
            planetArrayList.add(new Planet());
            PlanetType planetType = this.createPlanet(i);
            if (planetType == ROCK) {
                numberOfRockPlanets++;
            } else if (planetType == ASTEROID) {
                numberOfAsteroidBelts++;
            } else {
                numberOfGasPlanets++;
            }
            planetArrayList.get(i).generatePlanet(planetType);
        }
    }

    private PlanetType createPlanet(int planetIndex) {
        double chanceOfRock = 0, chanceOfGas = 0, chanceOfAsteroid = 0;
        if (planetIndex == 1) {
            chanceOfRock = 0.95;
            chanceOfAsteroid = 0.05;
            chanceOfGas = 0;
        } else if (planetIndex < 4) {
            chanceOfRock = 0.75;
            chanceOfAsteroid = 0.2;
            chanceOfGas = 0.05;
        } else if (planetIndex < 7) {
            chanceOfRock = 0.25;
            chanceOfAsteroid = 0.3;
            chanceOfGas = 0.45;
        } else if (planetIndex < 9) {
            chanceOfRock = 0.1;
            chanceOfAsteroid = 0.3;
            chanceOfGas = 0.6;
        } else if (planetIndex < 13) {
            chanceOfRock = 0.3;
            chanceOfAsteroid = 0.6;
            chanceOfGas = 0.1;
        }
        return generatePlanetType(chanceOfRock, chanceOfGas, chanceOfAsteroid);


    }

    private PlanetType generatePlanetType(double r, double g, double a) {
        Random rng = new Random();
        double numberSeed = rng.nextDouble();

        if (numberSeed < r && numberSeed > 0) {
            return ROCK;
        }
        if (numberSeed > r && numberSeed < (a + r)) {
            return ASTEROID;
        }
        if (numberSeed > (a + r)) {
            return GAS;
        }

        return ROCK;
    }

    public int getNumOfPlanets() {
        return planetArrayList.size();
    }

    private int numOfPlanets(double randomSeedResult) {
        if (randomSeedResult < 0.01) {
            return 12;
        } else if (randomSeedResult < 0.03) {
            return 11;
        } else if (randomSeedResult < 0.05) {
            return 10;
        } else if (randomSeedResult < 0.1) {
            return 9;
        } else if (randomSeedResult < 0.15) {
            return 8;
        } else if (randomSeedResult < 0.22) {
            return 7;
        } else if (randomSeedResult < 0.4) {
            return 6;
        } else if (randomSeedResult < 0.6) {
            return 5;
        } else if (randomSeedResult < 0.75) {
            return 4;
        } else if (randomSeedResult < 0.82) {
            return 3;
        } else if (randomSeedResult < 0.93) {
            return 2;
        }
        return 1;
    }

    public void addShipToSector(Ship ship) {
        listOfShipsInSector.add(ship);
    }

    public void removeShipFromSector(Ship ship) {
        listOfShipsInSector.remove(ship);
    }



}
