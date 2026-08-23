package main;

import menu.MainMenu;

public class MusafirCafe {

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("              MUSAFIR CAFE");
        System.out.println("        Welcome to Musafir Cafe!");
        System.out.println("==============================================");

        MainMenu mainMenu = new MainMenu();

        mainMenu.start();

        System.out.println("\nThank you for visiting Musafir Cafe!");
    }
}