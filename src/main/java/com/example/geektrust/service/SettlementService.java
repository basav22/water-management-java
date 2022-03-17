package com.example.geektrust.service;

import java.util.ArrayList;
import java.util.List;

public class SettlementService {

    List<Roommate> roommateList = new ArrayList<Roommate>();

    public void settle(String settler1) {
    }

    public Roommate addRoommate(String name) {
        Roommate roommate = new Roommate(name, roommateList);

        roommateList.forEach(roommate1 -> roommate1.createExpenseFor(roommate));

        roommateList.add(roommate);
        return roommate;
    }
}
