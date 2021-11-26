package com.example.geektrust.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WaterTankerCostCalulator {
    private final List<WaterTankerSlab> waterTankerSlabs;

    public WaterTankerCostCalulator(WaterTankerSlab... waterTankerSlabs) {
        this.waterTankerSlabs = Arrays.asList(waterTankerSlabs);
    }

    public int calculateCost(int waterRequiredInLitre) {
        var applicableSlabs = waterTankerSlabs.stream()
                        .takeWhile(slab -> slab.getMaxLitre() < waterRequiredInLitre)
                        .collect(Collectors.toUnmodifiableList());

        var waterLeftInLitre = waterRequiredInLitre;
        var waterCost = 0;

        for(var slabIndex = 0;slabIndex <= applicableSlabs.size(); slabIndex++) {
            var waterTankerSlab = waterTankerSlabs.get(slabIndex);

            var waterSlabRange = waterTankerSlab.getWaterRange();
            var slabWaterConsumedInLitre = Math.min(waterSlabRange, waterLeftInLitre);

            var slabCost = waterTankerSlab.getRatePerLitre() * slabWaterConsumedInLitre;
            waterCost += slabCost;
            waterLeftInLitre -= slabWaterConsumedInLitre;
        }
        return waterCost;
    }
}
