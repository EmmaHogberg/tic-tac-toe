package io.emma;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;


public class GameWindow extends JFrame {

    private JPanel panel;
    private ButtonXO[][]button = new ButtonXO[3][3];

    public GameWindow() {
        panel = new JPanel(new GridLayout(3,3));
        this.setTitle("Tic Tac Toe");
        this.setSize(450,450);
        this.setResizable(false);

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                panel.add(button[i][j] = new ButtonXO(i,j));
            }
        }
        panel.setBackground(Color.WHITE);
        this.add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
