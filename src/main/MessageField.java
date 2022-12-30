package main;

import javax.swing.*;
import java.awt.*;

public class MessageField extends JTextArea {

    private JFrame window;
    private Thread thread = new Thread();

    public MessageField(JFrame window){
        this.window = window;
        setBounds(80,620,940,170);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setEditable(false);
        setLineWrap(true);
        setWrapStyleWord(true);
        setFont(new Font("Book Antiqua",Font.PLAIN,28));
        setText("-");
        window.add(this);
    }

    public void printMessage(String message, int durationMs){

        thread = new Thread(()->{
            for(int i=1; i<=message.length(); i++){
                setText(message.substring(0,i));
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(durationMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setText("");
            thread.notifyAll();
        }, "messageThread");
        thread.start();
    }

}
