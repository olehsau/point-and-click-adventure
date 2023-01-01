package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Inventory {

    public static ArrayList<Item> list;
    public static ArrayList<JLabel> labelsList;
    //public static int size;
    public static JPanel inventoryPanel;
    public static JFrame window;
    public static Item active;

    public static void initInventory(JFrame w){
        list = new ArrayList<Item>();
        inventoryPanel = new JPanel();
        active = null;
        //size=0;
        window = w;
        inventoryPanel.setBounds(1070,50,130,700);
        inventoryPanel.setBackground(Color.darkGray);
        inventoryPanel.setLayout(null);
        window.add(inventoryPanel);
    }

    public static void drawInventory(){
        int i=0;
        for(Item item: list){
            JLabel newLabel = new JLabel(item.image);
            newLabel.setBounds(15,i*(50+10),100,100);
            addMouseListener(newLabel,()->{active=item;
                System.out.println("Active="+active.name);});
            inventoryPanel.add(newLabel);
            i++;
        }
        inventoryPanel.updateUI();
    }

    public static void addToInventory(Item item){
        list.add(item);
        inventoryPanel.removeAll();
        inventoryPanel.revalidate();
        inventoryPanel.repaint();
        drawInventory();
    }

    public static void removeFromInventory(Item item){
        list.remove(item);
        inventoryPanel.removeAll();
        inventoryPanel.revalidate();
        inventoryPanel.repaint();
        drawInventory();
    }

    private static void addMouseListener(JLabel label, Runnable r){
        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                r.run();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

}
