package io.emma;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Graphics;
import java.util.Objects;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ButtonXO extends JButton implements ActionListener {

    // TODO ?????????????????????????????????????????????????????????????????????????????
    enum Player {
        X,
        O,
    }

    private static final int PLAYER_X = 1;
    private static final int PLAYER_O = 2;

    private static int currentPlayer = (int) (Math.random() * 2 + 1); // TODO reset metoden
    private boolean isGameWon = false;
    private static boolean isGameOver = false;

//    public int getPositionX() {
//        return positionX;
//    }
//
//    public int getPositionY() {
//        return positionY;
//    }

    private int positionX = 0;
    private int positionY = 0;
    private static byte turnCount = 0;
    ImageIcon imageX;
    ImageIcon imageO;
    private static int[][] gameBoard = new int[3][3];

    // Check whos turn?????
    public static byte getTurnCount() {
        return turnCount;
    }

    // Class method game setup
    public ButtonXO(int x, int y) {
        imageX = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("x.png")));
        imageO = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("o.png")));

        positionX = x;
        positionY = y;

        this.setOpaque(false);
        this.addActionListener(this);
        this.setVisible(true);

        switch (x) {
            case 0:
                switch (y) {
                    case 1:
                        this.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.BLACK));
                        break;
                    default:
                        this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
                }
                break;

            case 2:
                switch (y) {
                    case 1:
                        this.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, Color.BLACK));
                        break;
                    default:
                        this.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
                        break;
                }
                break;

            case 1:
                switch (y) {
                    case 1:
                        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
                        break;
                    default:
                        this.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
                        break;
                }
                break;
        }
        this.setContentAreaFilled(false);
        this.setOpaque(false);
    }


    // Method to make game move
    private void makeMove(int playerMove) {
        gameBoard[positionX][positionY] = playerMove;
    }

    // Method to check who is the winner or if it is a draw
    private void checkGameResult() {

        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] == 1 && gameBoard[i][1] == 1 && gameBoard[i][2] == 1 || gameBoard[0][i] == 1 && gameBoard[1][i] == 1 && gameBoard[2][i] == 1) {
                GameEndWindow winner = new GameEndWindow("Player-O won!");
                isGameOver = true;
                break;
            } else if (gameBoard[i][0] == 2 && gameBoard[i][1] == 2 && gameBoard[i][2] == 2 || gameBoard[0][i] == 2 && gameBoard[1][i] == 2 && gameBoard[2][i] == 2) {
                GameEndWindow winner = new GameEndWindow("Player-X won!");
                isGameOver = true;
                break;
            }
        }
        // Check diagonally
        if (gameBoard[0][0] == 2 && gameBoard[2][2] == 2 && gameBoard[1][1] == 2 || gameBoard[0][2] == 2 && gameBoard[1][1] == 2 && gameBoard[2][0] == 2) {
            GameEndWindow winner = new GameEndWindow("Player-X won!");
            isGameOver = true;
        } else if (gameBoard[0][0] == 1 && gameBoard[2][2] == 1 && gameBoard[1][1] == 1 || gameBoard[0][2] == 1 && gameBoard[1][1] == 1 && gameBoard[2][0] == 1) {
            GameEndWindow winner = new GameEndWindow("Player-O won!");
            isGameOver = true;
        }
        if (turnCount == 9 && !isGameOver) {
            GameEndWindow winner = new GameEndWindow("Match draw...");
            isGameOver = true;
        }
    }

    // Method to show players move
    // @Override
    public void actionPerformed(ActionEvent e) {

        //if (e.getSource() instanceof ButtonXO)
        //int x = ((ButtonXO) e.getSource()).getPositionX();
        if (!isGameWon && !isGameOver) {
            turnCount++;

            switch (currentPlayer) {
                case 1 -> setIcon(imageO);
                case 2 -> setIcon(imageX);
            }
            makeMove(currentPlayer);
            checkGameResult();

            if (currentPlayer == 1) {
                currentPlayer = 2;
            } else {
                currentPlayer = 1;
            }
        }
        isGameWon = true;
    }
}
