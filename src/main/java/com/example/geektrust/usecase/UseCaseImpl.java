package com.example.geektrust.usecase;

import com.example.geektrust.models.ApartmentWaterManager;

import java.util.Arrays;
import java.util.Map;

public class UseCaseImpl implements UseCase{
    private final int NUM_DAYS_IN_MONTH = 30;
//    private Map<Integer, Integer> apartmentPersonCount;
    private int waterInLitresPerPerson;
    private ApartmentWaterManager apartmentWaterManager;

    public UseCaseImpl() {}

    public UseCaseImpl(int waterInLitrePerPerson) {
        this.waterInLitresPerPerson = waterInLitrePerPerson;
    }

    public ApartmentWaterManager getApartmentWaterManager() {
        return apartmentWaterManager;
    }

    @Override
    public void allocateWater(String command) {
        var args = getFunctionArguments(command);
        assert args.length == 2;

        var numOfBedrooms = args[0];

        var waterRatio = args[1];
        var corporateWaterShare= Integer.parseInt(waterRatio.split(":")[0]);
        var borewellWaterShare= Integer.parseInt(waterRatio.split(":")[1]);
        var corporationWaterPercent = (float)corporateWaterShare*100/(corporateWaterShare+borewellWaterShare);

        this.apartmentWaterManager = new ApartmentWaterManager(
                Integer.parseInt(numOfBedrooms),
                corporationWaterPercent,
                waterInLitresPerPerson
        );
    }

    @Override
    public void addGuests(String command) {
        var args = getFunctionArguments(command);
        assert args.length == 1;

        var newGuestCount = Integer.parseInt(args[0]);
        this.apartmentWaterManager.addGuests(newGuestCount);
    }

    @Override
    public void calculateBill(String command) {
        var args = getFunctionArguments(command);
        assert args.length == 0;

        var totalWaterConsumption = apartmentWaterManager.getTotalWaterConsumptionInMonth(NUM_DAYS_IN_MONTH);

        var totalCost = apartmentWaterManager.getTotalBillForDays(NUM_DAYS_IN_MONTH);
        System.out.println(totalWaterConsumption + " " + (int)Math.ceil(totalCost));
    }

    private String[] getFunctionArguments(String command) {
        var args = command.split(" ");
        return Arrays.copyOfRange(args, 1, args.length);
    }

}
