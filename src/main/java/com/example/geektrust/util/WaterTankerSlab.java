package com.example.geektrust.util;

public class WaterTankerSlab {
    private int minLitre;

    private int maxLitre;

    private int ratePerLitre;

    public WaterTankerSlab(int minLitre, int maxLitre, int ratePerLitre) {
        this.minLitre = minLitre;
        this.maxLitre = maxLitre;
        this.ratePerLitre = ratePerLitre;
    }

    public int getMinLitre() {
        return minLitre;
    }

    public int getMaxLitre() {
        return maxLitre;
    }

    public int getRatePerLitre() {
        return ratePerLitre;
    }

    public int getWaterRange() {
        return this.getMaxLitre() - this.getMinLitre() + 1;
    }
}
