package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Inventory {

    public static ArrayList<Item> list;
    public static JPanel inventoryPanel;
    public static JFrame window;

    public static void initInventory(JFrame w){
        list = new ArrayList<Item>();
        inventoryPanel = new JPanel();
        window = w;
        inventoryPanel.setBounds(1070,50,130,700);
        inventoryPanel.setBackground(Color.darkGray);
        inventoryPanel.setLayout(null);
        window.add(inventoryPanel);
    }

    public static void drawInventory(){
        window.add(inventoryPanel);
    }

    public static void addToInventory(Item item){
        if(window.getWidth()<=1100){
            window.setSize(1250,800);
        }
        list.add(item);
    }

}
