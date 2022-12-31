package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Function;

public class GameField extends JPanel {

    private JFrame window;
    private MessageField messageField;

    public GameField(JFrame window, MessageField messageField){
        this.window = window;
        this.messageField = messageField;
        setBounds(50,50,1000,550);
        setBackground(Color.black);
        setLayout(null);
        window.add(this);
    }

    public void drawRoomWithPortals(){
        JLabel room = new JLabel(new ImageIcon("res/roomWithPortals.png"));
        room.setBounds(0,0,1000,550);

        JLabel[] portals = new JLabel[6];
        portals[0] = new JLabel(); portals[0].setBounds(130,150,100,265);
        portals[1] = new JLabel(); portals[1].setBounds(240,180,54,190);
        portals[2] = new JLabel(); portals[2].setBounds(740,180,84,210);
        portals[3] = new JLabel(); portals[3].setBounds(900,70,100,400);
        Item feather = new Item("Перо","res/feather.png");
        JLabel featherLabel = new JLabel(feather.image);
        featherLabel.setBounds(190,400,35,31);
        add(featherLabel);
        addMouseListener(featherLabel,()->Inventory.addToInventory(feather));
        featherLabel.setVisible(false);
        addMouseListener(portals[0],()->{
            messageField.printMessage("*Ви заглянули у портал. Ви бачите гірський пейзаж. Досить дивний пейзаж - небо і хмари неонового зеленого кольору, а гори і земля яскраво фіолетових відтінків. Ростуть поодинокі дерева у формі спіралі, з темно червоним листям.",5000);
            messageField.printMessage("*Раптом, прямо перед вашим обличчям пролетів дивний птах, об щось стукнувшись, створивши хмару літаючого пір'я. Було відчуття, ніби він вдарився прямо об поверхню порталу, але це повинно бути неможливо, з самого визначення порталу.",6000);
            new Thread(()->{
                try {Thread.sleep(6000);} catch (InterruptedException e) {e.printStackTrace();}
                featherLabel.setVisible(true);}).start();
        });

        addMouseListener(portals[1],()->messageField.printComment("000000000",4000));
        addMouseListener(portals[2],()->messageField.printComment("000000000",4000));
        addMouseListener(portals[3],()->messageField.printComment("000000000",4000));

        add(portals[0]);
        add(portals[1]);
        add(portals[2]);
        add(portals[3]);
        add(room);

    }

    private void addMouseListener(JLabel label, Runnable r){
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
