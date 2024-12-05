/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
import java.util.Scanner;
/**
 *
 * @author Salan
 */
public class Main {
    public static void main(String[]args) {
        WarGame war = new WarGame("War");
        war.reset();
        System.out.println("Starting a new game of: " + war.getName());
        war.play();
    }
}
