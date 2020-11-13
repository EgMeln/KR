package com.bsu.by;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<User> users = new ArrayList<>();
        try (Scanner scannerUsers = new Scanner(new File("users.txt"));) {
            while (scannerUsers.hasNextLine()) {
                String line = scannerUsers.nextLine();
                String[] user = line.split(";");
                users.add(new User(user));
            }
        }
        HashMap<String, Horse> horses = new HashMap<>();
        try (Scanner scannerHorses = new Scanner(new File("horses.txt"))) {
            while (scannerHorses.hasNextLine()) {
                String line = scannerHorses.nextLine();
                String[] horse = line.split(",");
                horses.put(horse[0], new Horse(horse));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try (Scanner scanner = new Scanner(System.in)) {
            Race race = new Race(users, horses);
            int key;
            while (true) {
                race.showFirstMenu();
                key = scanner.nextInt();
                scanner.nextLine();
                switch (key) {
                    case 1:
                        System.out.println("Enter name ");
                        String name = scanner.next();
                        System.out.println("Enter login ");
                        String login = scanner.next();
                        System.out.println("Enter email ");
                        String email = scanner.next();
                        System.out.println("Enter password ");
                        String password = scanner.next();
                        race.registration(name, login, email, password);
                        break;
                    case 2:
                        System.out.println("Enter login ");
                        login = scanner.next();
                        System.out.println("Enter password ");
                        password = scanner.next();
                        if (race.login(login, password)) {
                            race.showSecondMenu();
                        } else {
                            break;
                        }
                        while (true) {
                            key = scanner.nextInt();
                            scanner.nextLine();
                            switch (key) {
                                case 1:
                                    race.showHorses();
                                    break;
                                case 2:
                                    System.out.println("Enter a unique horse number");
                                    String number = scanner.nextLine();
                                    race.searchHorsesByNumber(number);
                                    break;
                                case 3:
                                    System.out.println("Enter the number of horses");
                                    int num = scanner.nextInt();
                                    ;
                                    scanner.nextLine();
                                    race.topHorses(num);
                                    break;
                                case 9:
                                    System.out.println("EXIT");
                                    return;
                            }
                            race.showSecondMenu();
                        }
                    case 9:
                        System.out.println("EXIT");
                        return;
                    default:
                        continue;
                }
            }
        }
    }
}


