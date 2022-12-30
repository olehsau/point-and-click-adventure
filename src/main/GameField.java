package main;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {

    private JFrame window;

    public GameField(JFrame window){
        this.window = window;
        setBounds(50,50,1000,550);
        setBackground(Color.blue);
        setLayout(null);
        window.add(this);
    }

}
