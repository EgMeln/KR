package com.bsu.by;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Race {
    ArrayList<User> usersInRace;
    Map<String, Horse> horsesInRace;
    boolean isItAdmin = false;

    Race(ArrayList<User> users, Map<String, Horse> horses) {
        this.usersInRace = users;
        this.horsesInRace = horses;
    }

    void showFirstMenu() {
        System.out.println("1. Registration");
        System.out.println("2. Login");
        System.out.println("9. EXIT");
    }

    void showSecondMenu() {
        System.out.println("1. View all entries");
        System.out.println("2. Search for a horse by number (for admin only)");
        System.out.println("3. Top N horses by number of wins");
        System.out.println("9. EXIT");
    }

    ArrayList<User> registration(String name, String login, String email, String password) {
        for (User people : usersInRace) {
            if (login.equals(people.getLogin()) || email.equals(people.getEmail())) {
                System.out.println("This user already exists");
                return usersInRace;
            }
        }
        usersInRace.add(new User(name, login, email, password, User.Role.USER));
        try (FileWriter fw = new FileWriter("users.txt", true)) {
            User i = usersInRace.get(usersInRace.size() - 1);
            fw.write(i.toString());
            fw.write("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersInRace;
    }

    boolean login(String login, String password) {
        for (User people : usersInRace) {
            if (login.equals(people.getLogin()) && password.equals(people.getPassword()) && people.getRole() == User.Role.ADMIN) {
                System.out.println("You are logged in");
                isItAdmin = true;
                return true;
            }
            if (login.equals(people.getLogin()) && password.equals(people.getPassword()) && people.getRole() == User.Role.USER) {
                System.out.println("You are logged in");
                isItAdmin = false;
                return true;
            }
        }
        System.out.println("There is no such user");
        return false;
    }

    void showHorses() {
        for (Horse horse : horsesInRace.values()) {
            System.out.println(horse);
        }
    }

    void searchHorsesByNumber(String id) {
        if (isItAdmin) {
            for (Horse horse : horsesInRace.values()) {
                if (horse.getId().equals(id)) {
                    System.out.println(horse);
                    break;
                }
            }
        } else {
            System.out.println("This operation is not available to you.");
        }
    }

    void topHorses(int N) {
        ArrayList<Horse> horses = new ArrayList<>(horsesInRace.values());
        Comparator speedComparator = new MaxVictories() {
        };
        Collections.sort(horses, speedComparator);
        for (Horse horse : horses) {
            if (N != 0) {
                System.out.println(horse);
                N--;
            }
        }
    }

    public class MaxVictories implements Comparator<Horse> {
        public int compare(Horse o1, Horse o2) {
            return o2.getVictories() - o1.getVictories();
        }
    }
}
