package com.example.geektrust.usecase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UseCaseImplTest {

    private Map apartmentPersonCount = new HashMap<Integer, Integer>();
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpData() {
        this.apartmentPersonCount.put(2, 3);
        this.apartmentPersonCount.put(3, 5);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    UseCaseImpl useCase = new UseCaseImpl(10);

    @Test
    public void testALLOT_WATER() {
        String command = "ALLOT_WATER 2 3:7";
        useCase.allocateWater(command);
        var apartmentWaterManager = useCase.getApartmentWaterManager();
        assertEquals(3, apartmentWaterManager.getNumOfPeople());
        assertEquals(0, apartmentWaterManager.getTotalGuests());
        assertEquals(30, apartmentWaterManager.getCorporationWaterPercent());
    }

    @Test
    public void testADD_GUESTS() {
        String command = "ALLOT_WATER 2 3:7";
        useCase.allocateWater(command);

        String command2 = "ADD_GUESTS 2";
        useCase.addGuests(command2);
        var apartmentWaterManager = useCase.getApartmentWaterManager();
        assertEquals(2, apartmentWaterManager.getTotalGuests());

        useCase.addGuests("ADD_GUESTS 5");
        assertEquals(7, apartmentWaterManager.getTotalGuests());
    }

    @Test
    public void testBILL() {
        String command = "ALLOT_WATER 2 3:7";
        useCase.allocateWater(command);

        useCase.addGuests("ADD_GUESTS 2");
        useCase.addGuests("ADD_GUESTS 3");

        var apartmentWaterManager = useCase.getApartmentWaterManager();
        assertEquals(5, apartmentWaterManager.getTotalGuests());

        useCase.calculateBill("BILL");
        assertEquals("2400 5215\n", outputStreamCaptor.toString());
    }

    @Test
    public void testBILL_2() {
        String command = "ALLOT_WATER 3 2:1";
        useCase.allocateWater(command);

        useCase.addGuests("ADD_GUESTS 4");
        useCase.addGuests("ADD_GUESTS 1");

        var apartmentWaterManager = useCase.getApartmentWaterManager();
        assertEquals(5, apartmentWaterManager.getTotalGuests());

        useCase.calculateBill("BILL");
        assertEquals("3000 5750\n", outputStreamCaptor.toString());

    }

    @Test
    public void testBILL_3() {
        String command = "ALLOT_WATER 2 1:2";
        useCase.allocateWater(command);

        useCase.calculateBill("BILL");
        assertEquals("900 1200\n", outputStreamCaptor.toString());
    }

    @Test
    public void testBILL_4() {
        String command = "ALLOT_WATER 3 5:4";
        useCase.allocateWater(command);

        useCase.addGuests("ADD_GUESTS 3");
        useCase.addGuests("ADD_GUESTS 5");

        var apartmentWaterManager = useCase.getApartmentWaterManager();
        assertEquals(8, apartmentWaterManager.getTotalGuests());

        useCase.calculateBill("BILL");
        assertEquals("3900 10334\n", outputStreamCaptor.toString());

    }
}