package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Function;

public class GameField extends JPanel {

    private JFrame window;
    private MessageField messageField;

    JLabel romaLabel;
    JLabel noteLabel;

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
        Item feather = new Item("Перо","res/feather35.png");
        JLabel featherLabel = new JLabel(feather.image);
        featherLabel.setBounds(190,400,35,31);
        add(featherLabel);
        romaLabel = new JLabel(new ImageIcon("res/roma.png"));
        romaLabel.setBounds(290,200,200,232);
        romaLabel.setVisible(false);
        noteLabel = new JLabel(new ImageIcon("res/note.png"));
        noteLabel.setBounds(310,300,70,93);
        noteLabel.setVisible(false);
        add(noteLabel);
        add(romaLabel);
        addMouseListener(featherLabel,()->Inventory.addToInventory(feather));
        featherLabel.setVisible(false);
        boolean[] portalsVisited = {false,false,false,false};
        addMouseListener(portals[0],()->{
            messageField.printMessage("*Ви заглянули у портал. Ви бачите гірський пейзаж. Досить дивний пейзаж - небо і хмари неонового зеленого кольору, а гори і земля яскраво фіолетових відтінків. Ростуть поодинокі дерева у формі спіралі, з темно червоним листям.",2000);
            messageField.printMessage("*Раптом, прямо перед вашим обличчям пролетів дивний птах, об щось стукнувшись, створивши хмару літаючого пір'я. Було відчуття, ніби він вдарився прямо об поверхню порталу.",2000);
            new Thread(()->{
                try {Thread.sleep(6000/Constants.speedMultiplayer);} catch (InterruptedException e) {e.printStackTrace();}
                featherLabel.setVisible(true);
                portalsVisited[0] = true;
                if(portalsVisited[0]==true && portalsVisited[1]==true && portalsVisited[2]==true && portalsVisited[3]==true){
                    spawnRoma();
                }
                addMouseListener(featherLabel,()->featherLabel.setVisible(false));}).start();
        });
        addMouseListener(portals[1],()->{
            messageField.printMessage("*Ви зазирнули у портал. Перед вами сюрреалістичний світ, якого ви ще не бачили, геометричні форми якого не підвладні вашим очам і вашому мозку. Ви мимовільно відвернули голову і зробили крок назад.",1000);
            portalsVisited[1] = true;
            if(portalsVisited[0]==true && portalsVisited[1]==true && portalsVisited[2]==true && portalsVisited[3]==true){
                spawnRoma();
            }
        });
        addMouseListener(portals[2],()->{
            messageField.printMessage("*Перед вами старий одинокий дім, біля якого знаходиться інший портал, дуже схожий на той у який ви дивитесь. Вдалені стоять і розмовляють три особи - чаклун з палицею, маленький товстий чоловік і жінка в окулярах.",2000);
            portalsVisited[2] = true;
            if(portalsVisited[0]==true && portalsVisited[1]==true && portalsVisited[2]==true && portalsVisited[3]==true){
                spawnRoma();
            }
        });
        addMouseListener(portals[3],()->{
            messageField.printMessage("Ви дивитесь у портал. Ви бачите підводний світ, тисяці різнокольорових коралів і риб. Вода надзвичайно прозора, і вдалені видно щось схоже на... будинки? Це затоплене місто чи хтось там живе?", 1000);
            portalsVisited[3] = true;
            if(portalsVisited[0]==true && portalsVisited[1]==true && portalsVisited[2]==true && portalsVisited[3]==true){
                spawnRoma();
            }
        });

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

    private void spawnRoma(){
        romaLabel.setVisible(true);
        addMouseListener(romaLabel,()->{
            messageField.printMessage("*Перед вами з'явилася загадкова постать з закритим лицем у плащі. Під плащем можна розгледіти емблему - \"Розумники\". Хто він і що йому від мене потрібно?",2000);
            messageField.printMessage("Не бійся мене, я тобі не ворог. Я твій друг із іншої реальності. Я прийшов щоб сказати тобі - у тебе є важлива місія, і я тут, щоб вказати тобі твій перший крок. У нас мало часу. Візьми цей код, він тобі знадоби-...",3000);
            messageField.printMessage("О ні! Записка перемістилась у іншу реальність. Вона повинна бути у іншого мене у іншій реальності. Але щоб знайти її знадобиться дуже багато часу.",4000);
            messageField.printMessage("Гаразд, не можна більше чекати. Може ти впораєшся без коду. А може ти знайдеш його у мене в іншій реальності. Ти ж обраний, тобі вдасться. Іди за мною",3000);
            if(romaLabel.getMouseListeners().length>=1){
                System.out.println("1");
                romaLabel.removeMouseListener(romaLabel.getMouseListeners()[0]);
            }
            addMouseListener(romaLabel,()->{
                drawPortalRoom();
            });
            new Thread(()->{
                try {
                    Thread.sleep(9000/Constants.speedMultiplayer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                noteLabel.setVisible(true);}).start();



        });

    }

    public void drawPortalRoom(){
        removeAll();
        revalidate();
        JLabel portalRoom = new JLabel(new ImageIcon("res/portalRoom.png"));
        JLabel portal = new JLabel();
        portal.setBounds(270, 100,450,400);
        portalRoom.setBounds(0,0,1000,550);
        romaLabel.setBounds(100,300,200,232);
        repaint();

        if(romaLabel.getMouseListeners().length>=1){
            System.out.println("1");
            romaLabel.removeMouseListener(romaLabel.getMouseListeners()[0]);
        }
        addMouseListener(romaLabel,()->messageField.printComment("Ми ще зустрінемось. Заходь, у нас мало часу.",2000));
        addMouseListener(portal,()->World.loadWorld());

        add(romaLabel);
        add(portal);
        add(portalRoom);
    }

}
