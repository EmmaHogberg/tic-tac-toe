package io.emma;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameApp extends JFrame implements ActionListener {

    private JPanel topPanel;
    private JPanel gamePanel;
    private JPanel bottomPanel;
    private static JLabel headerLabel;
    private JLabel gameNameLabel;
    private JButton replay;
    private JButton exit;
    private static TicTacToe[][] button = new TicTacToe[3][3];

    public GameApp() {
        this.setTitle("Tic Tac Toe");
        this.setSize(450, 630);
        this.setResizable(false);
        String colorForHeaderAndFooter = "#263238";

        // Top panel
        topPanel = new JPanel();
        topPanel.setBackground(Color.decode(colorForHeaderAndFooter));
        topPanel.setLayout(new GridLayout(2, 2));
        gameNameLabel = new JLabel();
        gameNameLabel.setText("Tic Tac Toe");
        gameNameLabel.setFont(new java.awt.Font("Courier", Font.BOLD, 50));
        gameNameLabel.setForeground(Color.decode("#e0e0e0"));
        headerLabel = new JLabel();
        headerLabel.setText("Start player is and winner is");
        headerLabel.setFont(new java.awt.Font("Courier", Font.BOLD, 30));
        headerLabel.setForeground(Color.decode("#e0e0e0"));
        topPanel.add(gameNameLabel, BorderLayout.CENTER);
        topPanel.add(headerLabel, BorderLayout.CENTER);


        // Center panel
        gamePanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gamePanel.add(button[i][j] = new TicTacToe(i, j));
            }
        }
        gamePanel.setBackground(Color.decode("#fff3e0"));

        // Bottom panel
        replay = new JButton("Play again");
        replay.addActionListener(this);
        replay.setFont(new java.awt.Font("Courier", Font.PLAIN, 30));
        replay.setForeground(Color.decode("#e0e0e0"));
        replay.setBackground(Color.decode(colorForHeaderAndFooter));
        exit = new JButton("Exit");
        exit.addActionListener(this);
        exit.setFont(new java.awt.Font("Courier", Font.PLAIN, 30));
        exit.setForeground(Color.decode("#e0e0e0"));
        exit.setBackground(Color.decode(colorForHeaderAndFooter));
        this.bottomPanel = new JPanel(new GridLayout(1, 2));
        this.bottomPanel.add(replay);
        this.bottomPanel.add(exit);

        // Add to frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(gamePanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }

    /**
     * Print message to player in header
     */
    public static void setHeaderMessage(HeaderMessages headerMessage) {
        switch (headerMessage) {

            case STAR_WINS -> {
                headerLabel.setText("Star wins!");
            }
            case EARTH_WINS -> {
                headerLabel.setText("Earth wins!");
            }
            case STAR_STARTS -> {
                headerLabel.setText("Star starts, good luck!");
            }
            case EARTH_STARTS -> {
                headerLabel.setText("Earth starts, good luck!");
            }
            case BOTH_WINS -> {
                headerLabel.setText("Both wins, it's a draw");
            }
        }
    }

    public static void setWinnerBoard(boolean isGameOver) {
        if (isGameOver) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    button[i][j].setWinnerIcon();
                }
            }
        }
    }


    /**
     * Listen for click on exit or reset buttons
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == replay) {
            TicTacToe.resetGame();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    button[i][j].reset();
                }
            }
        }
    }
}
