package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class World {

    private static GameField gameField;
    private static MessageField messageField;

    private static Room room1;
    private static Room room2;
    private static Room room3;
    private static Room room4;
    private static int redFeathers;
    private static JLabel greenFeather;
    private static JLabel redFeather;
    private static JLabel yellowFeather;
    private static boolean greenReady=false;
    private static boolean yellowReady=false;
    private static boolean redReady=false;
    private static JLabel drawer;
    private static JLabel drawer2;
    private static JTextArea input;
    private static JButton enter;
    private static JLabel present;

    public static void initWorld(GameField gf, MessageField mf){
        gameField = gf;
        messageField = mf;
        room1 = new Room(1,"res/room1Background.jpg",new ArrayList<Item>());
        room2 = new Room(2,"res/room2Background.png",new ArrayList<Item>());
        room3 = new Room(3,"res/room3Background.png",new ArrayList<Item>());
        room4 = new Room(4,"res/room4Background.jpg",new ArrayList<Item>()); // doors and shoes
        room3.itemsInRoom.add(new Item("Green Feather","res/greenFeather.png",30,350,100,88));
        redFeathers=0;
    }

    public static void loadWorld(){

        loadRoom(room1);
        System.out.println("world loaded");
    }

    public static void loadRoom(Room roomToLoad) {
        gameField.removeAll();
        gameField.revalidate();
        gameField.repaint();
        Inventory.active = null;

        JLabel bg = new JLabel(roomToLoad.background);
        bg.setBounds(0, 0, 1000, 550);

        if (roomToLoad.number == 2) {
            JLabel parrot = new JLabel(new ImageIcon("res/parrot.png"));
            parrot.setBounds(350, 280, 35, 138);
            gameField.add(parrot);
            addMouseListener(parrot, () -> {
                if (redFeathers >= 2) {
                    //messageField.printComment("Ви розізлили папугу і він полетів.",2000);
                    //gameField.remove(parrot);
                } else {
                    Inventory.addToInventory(new Item("Red Feather", "res/redFeather.png"));
                }
                redFeathers++;
            });
        } else if (roomToLoad.number == 1) {
            present = new JLabel(new ImageIcon("res/present.png"));
            present.setBounds(350, 10, 372, 500);
            gameField.add(present);
            present.setVisible(false);
            input = new JTextArea("enter code");
            input.setVisible(false);
            input.setBounds(250,200,600,100);
            input.setBackground(Color.LIGHT_GRAY);
            input.setEditable(true);
            gameField.add(input);
            enter = new JButton("enter");
            enter.setBounds(450,300,200,50);
            enter.setBackground(Color.LIGHT_GRAY);
            enter.setVisible(false);
            gameField.add(enter);

            enter.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("action performed");
                    String code="384139";
                    String enteredCode = input.getText();
                    if(code.equals(enteredCode)) {
                        present.setVisible(true);
                        System.out.println("win");
                    }
                    else {
                        System.out.println("bad code");
                    }
                }
            });
            drawer2 = new JLabel(new ImageIcon("res/drawerOpened.png"));
            drawer2.setBounds(390,265,250,273);
            gameField.add(drawer2);
            drawer2.setVisible(false);
            if (redReady == false || greenReady == false || yellowReady == false) {
                drawer = new JLabel(new ImageIcon("res/drawer.png"));
                drawer.setBounds(390, 265, 250, 250);
                addMouseListener(drawer, World::drawFeathers);
                gameField.add(drawer);

                greenFeather = new JLabel(new ImageIcon("res/greenFeather.png"));
                greenFeather.setBounds(490, 230, 100, 88);
                gameField.add(greenFeather);
                greenFeather.setVisible(false);

                redFeather = new JLabel(new ImageIcon("res/redFeather.png"));
                redFeather.setBounds(400, 250, 100, 88);
                gameField.add(redFeather);
                redFeather.setVisible(false);

                yellowFeather = new JLabel(new ImageIcon("res/feather.png"));
                yellowFeather.setBounds(540, 250, 100, 88);
                gameField.add(yellowFeather);
                yellowFeather.setVisible(false);

                if (greenReady) {
                    greenFeather.setVisible(true);
                }
                if (redReady) {
                    redFeather.setVisible(true);
                }
                if (yellowReady) {
                    yellowFeather.setVisible(true);
                }

            }
            else{
                drawFeathers();
            }
    }

        JLabel leftArrow = new JLabel(new ImageIcon("res/left-arrow.png"));
        JLabel rightArrow = new JLabel(new ImageIcon("res/right-arrow.png"));
        leftArrow.setBounds(10,200,64,64);
        rightArrow.setBounds(910,200,64,64);

        gameField.add(leftArrow);
        gameField.add(rightArrow);

        addMouseListener(leftArrow,()->{
            switch (roomToLoad.number){
                case 1:loadRoom(room4);break;
                case 2:loadRoom(room1);break;
                case 3:loadRoom(room2);break;
                case 4:loadRoom(room3);break;
            }
        });

        addMouseListener(rightArrow,()->{
            switch (roomToLoad.number){
                case 1:loadRoom(room2);break;
                case 2:loadRoom(room3);break;
                case 3:loadRoom(room4);break;
                case 4:loadRoom(room1);break;
            }
        });

        for(Item item : roomToLoad.itemsInRoom){
            JLabel label = new JLabel(item.image);
            label.setBounds(item.x,item.y,item.width,item.height);
            gameField.add(label);
            addMouseListener(label,()->{Inventory.addToInventory(item);label.setVisible(false);roomToLoad.itemsInRoom.remove(item);});
        }

        gameField.add(bg);
    }

    private static void drawFeathers(){
        if(greenReady && redReady && yellowReady){
            gameField.remove(drawer);
            gameField.remove(redFeather);
            gameField.remove(greenFeather);
            gameField.remove(yellowFeather);
            gameField.revalidate();
            gameField.repaint();
            drawer2.setVisible(true);
            addMouseListener(drawer2, ()->{
                input.setVisible(true);
                enter.setVisible(true);
            });
            drawer2.setVisible(true);
        }
        else if(Inventory.active!=null && Inventory.active.name.equals("Green Feather")) {
            greenFeather.setVisible(true);
            Inventory.removeFromInventory(Inventory.active);
            greenReady=true;
        }
        else if(Inventory.active!=null && Inventory.active.name.equals("Red Feather")) {
            redFeather.setVisible(true);
            Inventory.removeFromInventory(Inventory.active);
            redReady=true;
        }
        else if(Inventory.active!=null && Inventory.active.name.equals("Перо")) {
            yellowFeather.setVisible(true);
            Inventory.removeFromInventory(Inventory.active);
            yellowReady=true;
        }

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
