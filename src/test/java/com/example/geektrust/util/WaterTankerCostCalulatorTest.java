package com.example.geektrust.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class WaterTankerCostCalulatorTest {

    WaterTankerCostCalulator waterTankerCostCalulator = new WaterTankerCostCalulator(
            new WaterTankerSlab(1, 500, 2),
            new WaterTankerSlab(501, 1500, 3),
            new WaterTankerSlab(1501, 3000, 5),
            new WaterTankerSlab(3001, Integer.MAX_VALUE, 8)
    );

    @Test
    void calculateWaterCostInFirstSlab() {
        var waterRequiredInLitre = 302;

        int cost = waterTankerCostCalulator.calculateCost(waterRequiredInLitre);
        assertEquals(604, cost);
    }

    @Test
    void calculateWaterCostInSecondSlab() {
        var waterRequiredInLitre = 1000;

        int cost = waterTankerCostCalulator.calculateCost(waterRequiredInLitre);
        assertEquals(2500, cost);
    }

    @Test
    void calculateWaterCostInThirdSlab() {
        var waterRequiredInLitre = 2000;

        int cost = waterTankerCostCalulator.calculateCost(waterRequiredInLitre);
        assertEquals(6500, cost);
    }

    @Test
    void calculateWaterCostInForthSlab() {
        var waterRequiredInLitre = 4000;

        int cost = waterTankerCostCalulator.calculateCost(waterRequiredInLitre);
        assertEquals(19500, cost);
    }

}