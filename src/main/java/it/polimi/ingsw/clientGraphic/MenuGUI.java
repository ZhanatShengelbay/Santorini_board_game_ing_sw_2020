package it.polimi.ingsw.clientGraphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuGUI extends JFrame implements ActionListener {
    JLabel bckg;
    JTextField nameForm;

    public MenuGUI(BackEndGui backEndGui){

        setSize(1280, 720);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon(getClass().getResource("/Santorini.jpeg"));
        bckg = new JLabel("", img, JLabel.CENTER);
        bckg.setBounds(0,0,1280, 720);
        add(bckg);


        nameForm=new JTextField("Insert your name");
        nameForm.setHorizontalAlignment(JTextField.CENTER);
        nameForm.setSize(150, 50);
        nameForm.setLocation(640-75,500);
        nameForm.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(10, 10, 9),2));
        nameForm.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nameForm.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                nameForm.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(10, 10, 9),5));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                nameForm.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(10, 10, 9),2));

            }
        });
        bckg.add(nameForm);
        //JPanel twoPlayerGame = new JPanel();

        JButton btn2player = new JButton("2-player game");
        btn2player.setSize(150, 50);
        btn2player.setLocation(300, 620);
        btn2player.setBackground(Color.getHSBColor(10, 10, 9));

        btn2player.addActionListener(e -> {
            if(nameForm.getText().equals("Insert your name"))
                JOptionPane.showMessageDialog(null, "Insert your name");
            else {
                JOptionPane.showMessageDialog(null, "Wait other player");
                backEndGui.sendMessage(nameForm.getText());
                backEndGui.sendMessage("2");

                new GodSetupUI(2, backEndGui);
                this.dispose();
            }

        });
        bckg.add(btn2player);

        JButton btn3player = new JButton("3-player game");
        btn3player.setSize(150, 50);
        btn3player.setLocation(550, 620);
        btn3player.setBackground(Color.getHSBColor(10, 10, 9));
        btn3player.addActionListener(e -> {
            if(nameForm.getText().equals("Insert your name"))
                JOptionPane.showMessageDialog(null, "Insert your name");
            else {
                JOptionPane.showMessageDialog(null, "Wait other players");
                backEndGui.sendMessage(nameForm.getText());
                backEndGui.sendMessage("3");
                new GodSetupUI(3, backEndGui);
                this.dispose();


            }

        });
        bckg.add(btn3player);

        JButton btnInstr = new JButton("how to play");
        btnInstr.setSize(150, 50);
        btnInstr.setLocation(800, 620);
        btnInstr.setBackground(Color.getHSBColor(10, 10, 9));
        btnInstr.addActionListener(new ActionListener() {
                                       @Override
                                       public void actionPerformed(ActionEvent e) {
                                           new Instruction();
                                       }
                                   });
        bckg.add(btnInstr);


        JButton btnExit = new JButton("Exit");
        btnExit.setSize(150, 50);
        btnExit.setLocation(1000, 620);
        btnExit.setBackground(Color.getHSBColor(10, 10, 9));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bckg.add(btnExit);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }


}
