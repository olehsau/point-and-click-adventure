package main;

import javax.swing.*;

public class Item {

    public final String name;
    public final ImageIcon image;
    public boolean picked;

    public Item(String name, String imageFile){
        this.name = name;
        this.image = new ImageIcon(imageFile);
        this.picked = false;
    }

}
