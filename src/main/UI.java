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
        gameField = new GameField(this.window);
        messageField = new MessageField(this.window);
        messageField.printMessage("HELLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO my name is Oleh",5000);
        messageField.printMessage("second message",6000);
        System.out.println("boom");

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

    public Thread getThreadByName(String threadName) {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(threadName)) return t;
        }
        return null;
    }

}
