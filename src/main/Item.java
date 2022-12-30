package main;

import javax.swing.*;

public class Item {

    public final String name;
    public final ImageIcon imageIcon;
    public boolean picked;

    public Item(String name, String imageFile){
        this.name = name;
        this.imageIcon = new ImageIcon(imageFile);
        this.picked = false;
    }

}
