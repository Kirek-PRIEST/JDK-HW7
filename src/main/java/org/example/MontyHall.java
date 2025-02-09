package org.example;

import java.util.*;

public class MontyHall {
    private final Map<Integer, Boolean> result = new HashMap<>();

    private int choice;
    private int removedDoor;
    private Map<Integer, Integer> doors;
    private int countTest = 1;


    public void printResult(double quantity) {
        getResult((int) quantity);
        double win = 0;
        double lose = 0;
        for (Map.Entry<Integer, Boolean> entry : result.entrySet()) {
            if (entry.getValue()) {
                win++;
            } else {
                lose++;
            }
            System.out.println(entry.getKey() + "\t - \t" + entry.getValue());
        }
        System.out.println("Выигрышей: " + (win/quantity*100) + "%\n"+
                "Проигрышей: " + (lose/quantity*100) + "%\n");

    }

    public Map<Integer, Boolean> getResult(int quantity) {
        for (int i = 0; i < quantity; i++) {
            saveResult();
        }
        return result;
    }
    private void saveResult() {
        if (game()) {
            result.put(countTest, true);
        } else {
            result.put(countTest, false);
        }
        countTest++;
    }
    private boolean game() {
        doors = new HashMap<>();
        fillDoors(doors);
        choice = rnd();
        removedDoor = removeDoor();
        choice = changeChoice();
        return doors.get(choice) == 1;
    }
    private int changeChoice() {
        int newChoice = rnd();
        if (newChoice != choice && newChoice != removedDoor) {
            return newChoice;
        } else {
            return changeChoice();
        }
    }
    private int removeDoor() {
        int toRemove = rnd();
        if (doors.get(toRemove) != 1 && toRemove != choice) {
            return toRemove;
        } else return removeDoor();
    }
    private Map<Integer, Integer> fillDoors(Map<Integer, Integer> doors) {
        doors.put(1, 0);
        doors.put(2, 0);
        doors.put(3, 0);
        doors.put(rnd(), 1);
        return doors;
    }

    private int rnd() {
        return (int) (Math.random() * 3) + 1;
    }
}
