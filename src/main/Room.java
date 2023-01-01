package main;

import javax.swing.*;
import java.util.ArrayList;

public class Room {

    public int number;
    public ImageIcon background;
    public ArrayList<Item> itemsInRoom;

    public Room(int n, String bgFile, ArrayList<Item> items){
        this.number = n;
        ImageIcon bg = new ImageIcon(bgFile);
        this.background = bg;
        this.itemsInRoom = items;
    }

}
