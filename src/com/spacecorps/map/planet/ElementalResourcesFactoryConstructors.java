package com.spacecorps.map.planet;

import java.util.HashMap;

import static com.spacecorps.map.planet.ElementalResources.HYDROGEN;

public class ElementalResourcesFactoryConstructors {


    public static HashMap<ElementalResources,Integer> createElementalResourceAmountHashMap(){
        HashMap<ElementalResources,Integer> elementalResourcesIntegerHashMap = new HashMap<>();
        for(ElementalResources element : ElementalResources.values()){
            elementalResourcesIntegerHashMap.put(element,0);
        }
        return elementalResourcesIntegerHashMap;
    }
    public static HashMap<ElementalResources,Long> createElementalResourceLongHashMap(){
        HashMap<ElementalResources,Long> elementalResourcesIntegerHashMap = new HashMap<>();
        for(ElementalResources element : ElementalResources.values()){
            elementalResourcesIntegerHashMap.put(element,0l);
        }
        return elementalResourcesIntegerHashMap;
    }

}
