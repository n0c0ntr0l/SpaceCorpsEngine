package com.spacecorps.map;

import com.spacecorps.map.Sector.Sector;
import com.spacecorps.map.planet.ElementalResources;
import com.spacecorps.map.planet.ElementalResourcesFactoryConstructors;

import java.util.*;

import static com.spacecorps.map.planet.ElementalResources.*;

public class GalaxyMapTop {

    private Sector[][][] sectorMatrix;
    private HashMap<ElementalResources, Long> totalResourcesInGalaxyMap;
    private ArrayList<Sector> listOfHotSectors; //this arraylist contains sectors that have multiple ships to allow for the fightTimerTask to see if any battles are to occur


    private static double CHANCEOFSTARPROBABILITY = 0.009;
    public static int GALAXYGRIDSIZE = 250;
    public static int HALFPOINT = (GALAXYGRIDSIZE / 2) - 1;
    private static double CENTREOFMAPSQRT;
    private static int GALACTICWEEKINTICKS = 6048000;


    public int getRockPlanetCount() {
        return rockPlanetCount;
    }

    public int getAsteroidCount() {
        return asteroidCount;
    }

    public int getGasCount() {
        return gasCount;
    }

    private int rockPlanetCount = 0;
    private int asteroidCount = 0;
    private int gasCount = 0;

    public int getNumberOfStars() {
        return numberOfStars;
    }

    private int numberOfStars = 0;

    public int getNumberOfPlanets() {
        return numberOfPlanets;
    }

    private int numberOfPlanets = 0;


    public void generateGalaxy() {
        sectorMatrix = new Sector[GALAXYGRIDSIZE][GALAXYGRIDSIZE][GALAXYGRIDSIZE];
        CENTREOFMAPSQRT = Math.sqrt(HALFPOINT ^ 2 + HALFPOINT ^ 2 + HALFPOINT ^ 2);
        totalResourcesInGalaxyMap = ElementalResourcesFactoryConstructors.createElementalResourceLongHashMap();

        for (int i = 0; i < GALAXYGRIDSIZE; i++) {
            for (int j = 0; j < GALAXYGRIDSIZE; j++) {
                for (int k = 0; k < GALAXYGRIDSIZE; k++) {

                    Random rng = new Random();
                    if (rng.nextDouble() < CHANCEOFSTARPROBABILITY) {
                        double distanceFromCenter = Util.calculateDistanceBetweenTwoPoint(i,j,k,HALFPOINT,HALFPOINT,HALFPOINT);
                        double distanceProbability = distanceFromCenter / CENTREOFMAPSQRT;
                        double piDistanceProbability = Math.PI * distanceProbability;
                        double sineProbabilitySeed = 0.5 * Math.sin(piDistanceProbability);
                        if (rng.nextDouble() < sineProbabilitySeed) {
                            sectorMatrix[i][j][k] = new Sector(i, j, k, false);
                            sectorMatrix[i][j][k].generateSector();
                            numberOfPlanets = numberOfPlanets + sectorMatrix[i][j][k].getNumOfPlanets();
                            rockPlanetCount = rockPlanetCount + sectorMatrix[i][j][k].getNumberOfRockPlanets();
                            asteroidCount = asteroidCount + sectorMatrix[i][j][k].getNumberOfAsteroidBelts();
                            gasCount = gasCount + sectorMatrix[i][j][k].getNumberOfGasPlanets();
                            numberOfStars++;
                        }
                    } //else {
                        //System.out.println("null sector at: " + i + "," + j + "," + k);
                        //sectorMatrix[i][j][k] = new Sector(i, j, k, true);
                  //  }
                }
            }
        }

        for (int i = 0; i < GALAXYGRIDSIZE; i++) {
            for (int j = 0; j < GALAXYGRIDSIZE; j++) {
                for (int k = 0; k < GALAXYGRIDSIZE; k++) {
                    if (sectorMatrix[i][j][k] == null) {
                        sectorMatrix[i][j][k] = new Sector(i, j, k, true);
                        //System.out.println("null sector at: " + i + "," + j + "," + k);
                    }
                }
            }
        }

        listOfHotSectors = new ArrayList<>();


    }

    public void getNumberOfResources() {
        for (int i = 0; i < GALAXYGRIDSIZE; i++) {
            for (int j = 0; j < GALAXYGRIDSIZE; j++) {
                for (int k = 0; k < GALAXYGRIDSIZE; k++) {
                    if (!sectorMatrix[i][j][k].isEmptySector()) {
                        HashMap<ElementalResources, Integer> map = sectorMatrix[i][j][k].getTotalResourcesInSector();
                        Iterator it = map.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry resourceAndValue = (Map.Entry) it.next();
                            ElementalResources element = (ElementalResources) resourceAndValue.getKey();
                            Integer tempElementAmount = (Integer) resourceAndValue.getValue();
                            Long longamount = tempElementAmount.longValue();
                            totalResourcesInGalaxyMap.put(element, totalResourcesInGalaxyMap.get(element) + longamount);
                        }
                    }
                }
            }
        }
        System.out.println("Total number of HYDROGEN in galaxy: " + totalResourcesInGalaxyMap.get(HYDROGEN));
        System.out.println("Total number of HELIUM in galaxy: " + totalResourcesInGalaxyMap.get(HELIUM));
        System.out.println("Total number of CARBON in galaxy: " + totalResourcesInGalaxyMap.get(CARBON));
        System.out.println("Total number of NITROGEN in galaxy: " + totalResourcesInGalaxyMap.get(NITROGEN));
        System.out.println("Total number of OXYGEN in galaxy: " + totalResourcesInGalaxyMap.get(OXYGEN));
        System.out.println("Total number of SILICON in galaxy: " + totalResourcesInGalaxyMap.get(SILICON));
        System.out.println("Total number of IRON in galaxy: " + totalResourcesInGalaxyMap.get(IRON));
        System.out.println("Total number of GOLD in galaxy: " + totalResourcesInGalaxyMap.get(GOLD));
        System.out.println("Total number of COPPER in galaxy: " + totalResourcesInGalaxyMap.get(COPPER));
        System.out.println("Total number of TITANIUM in galaxy: " + totalResourcesInGalaxyMap.get(TITANIUM));
        System.out.println("Total number of URANIUM in galaxy: " + totalResourcesInGalaxyMap.get(URANIUM));
        System.out.println("Total number of POLONIUM in galaxy: " + totalResourcesInGalaxyMap.get(POLONIUM));
    }

    public ArrayList<Sector> getListOfHotSectors() {
        return listOfHotSectors;
    }

    public Sector[][][] getSectorMatrix() {
        return sectorMatrix;
    }

    public static void main(String[] args) {
        GalaxyMapTop galaxyMapTop = new GalaxyMapTop();

        galaxyMapTop.generateGalaxy();


        System.out.println("number of stars: " + galaxyMapTop.getNumberOfStars());
        System.out.println("number of planets: " + galaxyMapTop.getNumberOfPlanets());
        System.out.println("Number of rock planets: " + galaxyMapTop.getRockPlanetCount());
        System.out.println("Number of gas planets: " + galaxyMapTop.getGasCount());
        System.out.println("Number of asteroid belts: " + galaxyMapTop.getAsteroidCount());
        galaxyMapTop.getNumberOfResources();

    }


}
