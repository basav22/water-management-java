package com.example.geektrust.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Roommate {
    private String name;
    private Map<Roommate, Integer> expenses;

    public Roommate(String name, List<Roommate> roommateList) {
        this.name = name;
        expenses = roommateList.stream().collect(Collectors.toMap(p->p , p->0));
    }

    public void createExpenseFor(Roommate roommate) {
        expenses.put(roommate, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roommate roommate = (Roommate) o;
        return name.equals(roommate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Roommate{" +
                "name='" + name + '\'' +
                '}';
    }

    public int getExpenseFor(Roommate roommate) {
        return expenses.get(roommate);
    }
}
