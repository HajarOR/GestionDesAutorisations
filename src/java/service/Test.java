/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Lenovo
 */
import java.nio.charset.Charset;
import java.util.Map.Entry;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        String test = "\u060b";
        Scanner sc = new Scanner(System.in);
        System.out.println("Copier-coller cette phrase : " + test);
        System.out.println("Voici la phrase à chercher : " + sc.nextLine());
        System.out.println("\nAppuyer sur Entrée pour afficher la liste des possibilités");
        sc.nextLine();
        for (Entry<String, Charset> entry : Charset.availableCharsets().entrySet()) {
            System.out.println(new String(test.getBytes(), entry.getValue()) + "\t" + entry.getKey());
        }
    }
}
