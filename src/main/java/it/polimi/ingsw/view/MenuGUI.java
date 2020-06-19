package it.polimi.ingsw.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI extends JFrame implements ActionListener {
    JLabel bckg;

    public MenuGUI(){

        setSize(1900, 800);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("src/Images/Santorini.jpeg");
        bckg = new JLabel("", img, JLabel.CENTER);
        bckg.setBounds(0,0,1800, 800);
        add(bckg);
        setVisible(true);


        JPanel twoPlayerGame = new JPanel();

        JButton btn2player = new JButton("2-player game");
        btn2player.setSize(150, 50);
        btn2player.setLocation(300, 620);
        btn2player.setBackground(Color.getHSBColor(10, 10, 9));

        btn2player.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Now You should choose the God before starting the game");
                new TwoPlayerGod();
            }
        });
        bckg.add(btn2player);

        JButton btn3player = new JButton("3-player game");
        btn3player.setSize(150, 50);
        btn3player.setLocation(500, 620);
        btn3player.setBackground(Color.getHSBColor(10, 10, 9));
        btn3player.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Now You should choose the God before starting the game");
                new ThreePlayerGod();
            }
        });
        bckg.add(btn3player);

        JButton btnInstr = new JButton("how to play");
        btnInstr.setSize(150, 50);
        btnInstr.setLocation(700, 620);
        btnInstr.setBackground(Color.getHSBColor(10, 10, 9));
        btnInstr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Instruction();
            }
        });
        bckg.add(btnInstr);

        JButton btnCrd = new JButton("Credits to: ");
        btnCrd.setSize(150, 50);
        btnCrd.setLocation(900, 620);
        btnCrd.setBackground(Color.getHSBColor(10, 10, 9));
        btnCrd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreditsTo();

            }
        });
        bckg.add(btnCrd);

        JButton btnExit = new JButton("Exit");
        btnExit.setSize(150, 50);
        btnExit.setLocation(1100, 620);
        btnExit.setBackground(Color.getHSBColor(10, 10, 9));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bckg.add(btnExit);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }


}
