package main;

import javax.swing.*;

public class Item {

    public final String name;
    public final ImageIcon image;
    public boolean picked;
    public int x,y,width,height;

    public Item(String name, String imageFile){
        this.name = name;
        this.image = new ImageIcon(imageFile);
        this.picked = false;
    }

    public Item(String name, String imageFile, int x, int y, int w, int h){
        this.name = name;
        this.image = new ImageIcon(imageFile);
        this.picked = false;
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;
    }

}
