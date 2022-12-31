package main;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class MessageField extends JTextArea {

    private JFrame window;
    private Thread thread = new Thread();
    private LinkedList<String> messagesList = new LinkedList<String>();
    private LinkedList<Integer> durationsList = new LinkedList<Integer>();
    public boolean isDisplaying = false;

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

    public void printMessage(String msg, int durationMs){
        messagesList.add(msg);
        durationsList.add(durationMs/ 20);
        isDisplaying=true;
        if(messagesList.size()>1){
            return;
        }
        thread = new Thread(()->{
            while (!messagesList.isEmpty()) {
                String message = messagesList.pop();

                for (int i = 1; i <= message.length(); i++) {
                    setText(message.substring(0, i));
                    try {
                        Thread.sleep(20/ 10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(durationsList.pop());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setText("");
            }
            isDisplaying=false;
        }, "messageThread");
        thread.start();
    }

    // like message but interrupts and can be interrupted
    public void printComment(String comment, int durationMs){
        messagesList.clear();
        durationsList.clear();
        thread.stop();
        printMessage(comment,durationMs);
    }

    public Thread getThread(){
        return thread;
    }

}
