package com.example.geektrust.constants;

public enum WaterRateCard {
    CORPORATION_RATE_PER_LITRE(1),
    BOREWELL_RATE_PER_LITER((float)1.5);

    private final float ratePerLitre;

    public float getRatePerLitre() {
        return ratePerLitre;
    }

    WaterRateCard(float rate) {
        this.ratePerLitre = rate;
    }
}
