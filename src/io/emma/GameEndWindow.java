package io.emma;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class GameEndWindow extends JFrame implements ActionListener {

    JPanel contentPanel;
    JPanel bottomPanel;
    JPanel panel;
    JLabel imageLabel;
    JLabel headerLabel;
    JButton retry;
    JButton exit;


    public GameEndWindow(String messageToPlayer /*, GameLogic gameLogic*/) {
        try{
            imageLabel = new JLabel();
            headerLabel = new JLabel();

            retry = new JButton("<html><font size=+2 color=#00991C>Play again</font></html>");
            exit = new JButton("<html><font size=+2 color=#F50300>Exit</font></html>");

            panel = new JPanel();
            bottomPanel = new JPanel(new GridLayout(1, 2));
            bottomPanel.add(retry);
            bottomPanel.add(exit);
            this.setUndecorated(true);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.contentPanel = (JPanel) getContentPane();
            this.contentPanel.setLayout(new BorderLayout());
            this.setSize(500, 400);

            headerLabel.setFont(new java.awt.Font("Courier", Font.BOLD, 40));
            String attach = "<html><font color=#006699>"+ messageToPlayer +"</font></html>";
            headerLabel.setText(attach);
            panel.add(headerLabel);
            contentPanel.add(panel, BorderLayout.NORTH);
            ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("victory.jpg")));
            imageLabel.setIcon(imageIcon);

            this.contentPanel.add(imageLabel, BorderLayout.CENTER);
            this.contentPanel.add(bottomPanel, BorderLayout.SOUTH);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==exit) {
            System.exit(0);
        } else if (e.getSource()==retry) { //?????????????????????????????????? Varför är den tom?
            //gameLogic.reset();
        }


    }
}
