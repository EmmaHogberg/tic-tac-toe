package io.emma;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.Objects;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TicTacToe extends JButton implements ActionListener {

    private static int currentPlayer = 0;
    private static String winner = "";
    private boolean isTileUsed = false;
    private static boolean isGameOver = false;
    private int positionX = 0;
    private int positionY = 0;
    private static int turnCount = 0;
    private static int[][] gameBoard = new int[3][3];
    ImageIcon imageStar;
    ImageIcon imageEarth;
    ImageIcon imageDraw;


    /**
     * Game setup
     */
    public TicTacToe(int x, int y) {
        imageStar = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("star.png")));
        imageEarth = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("earth.png")));
        imageDraw = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("draw.png")));

        positionX = x;
        positionY = y;

        this.setOpaque(false);
        this.addActionListener(this);
        this.setVisible(true);
        this.setContentAreaFilled(false);

        resetGame();
    }


    /**
     * Listen for click and place players image marker on clicked button
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (!isTileUsed && !isGameOver) {
            turnCount++;

            switch (currentPlayer) {
                case 1 -> setIcon(imageEarth);
                case 2 -> setIcon(imageStar);
            }
            markChosenTile(currentPlayer);
            checkGameResult();

            if (currentPlayer == 1) {
                currentPlayer = 2;
            } else {
                currentPlayer = 1;
            }
        }
        isTileUsed = true;
    }

    /**
     * Mark chosen tile with current players number
     */
    private void markChosenTile(int player) {
        gameBoard[positionX][positionY] = player;
    }

    /**
     * Check who is the winner or if it is a draw
     */
    private void checkGameResult() {

        // Check horizontal and vertical
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] == 1 && gameBoard[i][1] == 1 && gameBoard[i][2] == 1 || gameBoard[0][i] == 1 && gameBoard[1][i] == 1 && gameBoard[2][i] == 1) {
                GameApp.setHeaderMessage(HeaderMessages.EARTH_WINS);
                winner = "Earth";
                isGameOver = true;
                break;
            } else if (gameBoard[i][0] == 2 && gameBoard[i][1] == 2 && gameBoard[i][2] == 2 || gameBoard[0][i] == 2 && gameBoard[1][i] == 2 && gameBoard[2][i] == 2) {
                GameApp.setHeaderMessage(HeaderMessages.STAR_WINS);
                winner = "Star";
                isGameOver = true;
                break;
            }
        }

        // Check diagonally
        if (gameBoard[0][0] == 2 && gameBoard[2][2] == 2 && gameBoard[1][1] == 2 || gameBoard[0][2] == 2 && gameBoard[1][1] == 2 && gameBoard[2][0] == 2) {
            GameApp.setHeaderMessage(HeaderMessages.STAR_WINS);
            winner = "Star";
            isGameOver = true;
        } else if (gameBoard[0][0] == 1 && gameBoard[2][2] == 1 && gameBoard[1][1] == 1 || gameBoard[0][2] == 1 && gameBoard[1][1] == 1 && gameBoard[2][0] == 1) {
            GameApp.setHeaderMessage(HeaderMessages.EARTH_WINS);
            winner = "Earth";
            isGameOver = true;
        }

        // Check for draw
        if (turnCount == 9 && !isGameOver) {
            GameApp.setHeaderMessage(HeaderMessages.BOTH_WINS);
            winner = "Draw";
            isGameOver = true;
        }

        if (isGameOver) {
            GameApp.setWinnerBoard(true);
        }
    }

    /**
     * Display winner board
     */
    public void setWinnerIcon() {
        if (winner.equals("Earth")) {
            setIcon(imageEarth);
        } else if (winner.equals("Star")) {
            setIcon(imageStar);
        } else if (winner.equals("Draw")) {
            setIcon(imageDraw);
        }
    }


    /**
     * Reset board to play a new game
     */
    public static void resetGame() {
        currentPlayer = (int) (Math.random() * 2 + 1);
        winner = "";
        isGameOver = false;
        turnCount = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = 0;
            }
        }

        if (currentPlayer == 1) {
            GameApp.setHeaderMessage(HeaderMessages.EARTH_STARTS);
        } else if (currentPlayer == 2) {
            GameApp.setHeaderMessage(HeaderMessages.STAR_STARTS);
        }
    }


    /**
     * Reset pressed buttons and clear icon
     */
    public void reset() {
        isTileUsed = false;
        setIcon(null);
    }
}
