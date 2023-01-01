package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UI {

    public GameManager gm;
    public JFrame window;
    public GameField gameField;
    public MessageField messageField;


    public UI(GameManager gm){
        this.gm = gm;
        initWindow();
        messageField = new MessageField(this.window);
        gameField = new GameField(this.window, this.messageField);
        World.initWorld(gameField,messageField);
        Inventory.initInventory(this.window);

        messageField.printMessage("...",2000);
        messageField.printMessage("Де я?",2000);
        messageField.printMessage("Я-я..., я не...",1000);
        messageField.printMessage("Що я тут роблю? Що це за місце?",2000);
        messageField.printMessage("...",1000);
        messageField.printMessage("Ці отвори у стінах схожі на якісь портали. Зі мною не станеться нічого поганого якщо я загляну у них?",3000);

        gameField.drawRoomWithPortals();

        window.setVisible(true);
    }



    public void initWindow(){
        window = new JFrame();
        window.setSize(1250,800);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);
        window.setResizable(false);
    }


    private void sleep(int milis){
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Thread getThreadByName(String threadName) {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(threadName)) return t;
        }
        return null;
    }

}
