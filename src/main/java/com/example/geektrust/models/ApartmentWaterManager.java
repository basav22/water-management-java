package com.example.geektrust.models;

import com.example.geektrust.constants.WaterRateCard;
import com.example.geektrust.util.WaterTankerCostCalulator;
import com.example.geektrust.util.WaterTankerSlab;

public class ApartmentWaterManager {
    private int numOfPeople;
    private int totalGuests;
    private float corporationWaterPercent;
    private int waterInLitresPerPerson;
    private WaterTankerCostCalulator waterTankerCostCalulator;

    public ApartmentWaterManager(int numOfBedrooms, float corporationWaterPercent, int waterInLitresPerPerson) {
        this.numOfPeople = numOfBedrooms == 2 ? 3 : 5;
        this.totalGuests = 0;
        this.corporationWaterPercent = corporationWaterPercent;
        this.waterInLitresPerPerson = waterInLitresPerPerson;
        this.waterTankerCostCalulator = new WaterTankerCostCalulator(
                new WaterTankerSlab(1, 500, 2),
                new WaterTankerSlab(501, 1500, 3),
                new WaterTankerSlab(1501, 3000, 5),
                new WaterTankerSlab(3001, Integer.MAX_VALUE, 8)
        );
    }

    public int addGuests(int newGuestCount) {
        totalGuests += newGuestCount;
        return totalGuests;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public int getTotalGuests() {
        return totalGuests;
    }

    public float getCorporationWaterPercent() {
        return corporationWaterPercent;
    }

    public int getTotalWaterConsumptionInMonth(int numDaysInMonth) {
        return (numOfPeople + totalGuests) * waterInLitresPerPerson * numDaysInMonth;
    }

    public float getTotalBillForDays(int numDaysInMonth) {
        var peopleWaterConsumption = numOfPeople * waterInLitresPerPerson * numDaysInMonth;
        var corporationWaterInLitre = peopleWaterConsumption * corporationWaterPercent / 100;
        var borewellWaterInLitre = peopleWaterConsumption - corporationWaterInLitre;

        var peopleWaterCost = (borewellWaterInLitre * WaterRateCard.BOREWELL_RATE_PER_LITER.getRatePerLitre()) +
                (corporationWaterInLitre * WaterRateCard.CORPORATION_RATE_PER_LITRE.getRatePerLitre());

        var guestWaterConsumption = totalGuests * waterInLitresPerPerson * numDaysInMonth;
        var guestWaterCost = waterTankerCostCalulator.calculateCost(guestWaterConsumption);

        return peopleWaterCost + guestWaterCost;
    }
}
