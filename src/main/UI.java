package main;

import javax.swing.*;
import java.awt.*;

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
        Inventory.initInventory(this.window);

        messageField.printMessage("...",4000);
        messageField.printMessage("Де я?",4000);
        messageField.printMessage("Я-я..., я не...",4000);
        messageField.printMessage("Що я тут роблю? Що це за місце?",5000);
        messageField.printMessage("...",3000);
        messageField.printMessage("Ці отвори у стінах схожі на якісь портали. Зі мною не станеться нічого поганого якщо я загляну у них?",5000);

        gameField.drawRoomWithPortals();

        window.setVisible(true);
    }



    public void initWindow(){
        window = new JFrame();
        window.setSize(1100,800);
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
