package com.example.geektrust.service;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SettlementServiceTest {

    SettlementService service = new SettlementService();

    @Test
    public void testSettlement() {
        var roommate = service.addRoommate("Basav");

        assertEquals("Basav", roommate.getName());
        assertEquals(Map.of(), roommate.getExpenses());

        var roommate2 = service.addRoommate("Ammu");
        assertEquals("Ammu", roommate2.getName());
        assertEquals(0, roommate2.getExpenseFor(roommate));
        assertEquals(0, roommate.getExpenseFor(roommate2));

    }
}