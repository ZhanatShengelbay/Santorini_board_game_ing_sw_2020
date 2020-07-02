package it.polimi.ingsw.clientGraphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Instruction extends JFrame implements ActionListener {
    JFrame frame = new JFrame("how to play: ");

    private JPanel panelUp;
    private JPanel panelButtom;

    private JLabel lblGod;
    private JLabel lblOne;
    private JLabel lblTwo;
    private JLabel lblFloor;
    private JLabel lblThree;
    private JLabel lblDome;
    private JLabel lblSend;
    private JLabel lblPower;
    private JTextPane stdRules;
    private JTextPane turnActions;


    public Instruction(){



        panelUp = new JPanel();
        panelUp.setLayout(new GridBagLayout());

        GridBagConstraints c1 = new GridBagConstraints();
        c1.weightx = 0;
        c1.weighty = 0;
        c1.gridx = 0;
        c1.gridy = 0;
        c1.gridwidth = 1;
        c1.gridheight = 1;
        ImageIcon img = new ImageIcon(getClass().getResource("/godcI.png"));
        lblGod = new JLabel("God Cards", img, JLabel.CENTER);
        lblGod.setPreferredSize(new Dimension(235, 235));
        panelUp.add(lblGod, c1);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 1;
        c2.gridy = 0;
        c2.weightx = 0;
        c2.weighty = 0;
        c2.gridwidth = 1;
        c2.gridheight = 1;

        ImageIcon fl = new ImageIcon(getClass().getResource("/floorI.png"));
        lblFloor = new JLabel("floor", fl, JLabel.CENTER);
        lblFloor.setPreferredSize(new Dimension(235, 235));
        panelUp.add(lblFloor, c2);

        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 2;
        c3.gridy = 0;
        c3.weightx = 0;
        c3.weighty = 0;
        c3.gridwidth = 1;
        c3.gridheight = 1;
        ImageIcon one = new ImageIcon(getClass().getResource("/firstI.png"));
        lblOne = new JLabel("first level", one, JLabel.CENTER);
        lblOne.setPreferredSize(new Dimension(235, 235));
        panelUp.add(lblOne, c3);

        GridBagConstraints c4 = new GridBagConstraints();
        c4.weightx = 0;
        c4.weighty = 0;
        c4.gridx = 3;
        c4.gridy = 0;
        c4.gridwidth = 1;
        c4.gridheight = 1;
        ImageIcon two = new ImageIcon(getClass().getResource("/secondI.png"));
        lblTwo = new JLabel("second level", two, JLabel.CENTER);
        lblTwo.setPreferredSize(new Dimension(250, 250));
        panelUp.add(lblTwo, c4);

        GridBagConstraints c5 = new GridBagConstraints();
        c5.weightx = 0;
        c5.weighty = 0;
        c5.gridx = 0;
        c5.gridy = 1;
        c5.gridwidth = 1;
        c5.gridheight = 1;
        ImageIcon three = new ImageIcon(getClass().getResource("/thirdI.png"));
        lblThree = new JLabel("third level", three, JLabel.CENTER);
        lblThree.setPreferredSize(new Dimension(235, 235));
        panelUp.add(lblThree, c5);

        GridBagConstraints c6 = new GridBagConstraints();
        c6.weightx = 0;
        c6.weighty = 0;
        c6.gridx = 1;
        c6.gridy = 1;
        c6.gridwidth = 1;
        c6.gridheight = 1;
        ImageIcon dome = new ImageIcon(getClass().getResource("/domeI.png"));
        lblDome = new JLabel("dome", dome, JLabel.CENTER);
        lblDome.setName("Dome");
        lblDome.setPreferredSize(new Dimension(235, 235));
        panelUp.add(lblDome, c6);

        GridBagConstraints c7 = new GridBagConstraints();
        c7.weightx = 0;
        c7.weighty = 0;
        c7.gridx = 2;
        c7.gridy = 1;
        c7.gridwidth = 1;
        c7.gridheight = 1;
        ImageIcon pwr = new ImageIcon(getClass().getResource("/powerI.png"));
        lblPower = new JLabel("power", pwr, JLabel.CENTER);
        lblPower.setPreferredSize(new Dimension(235, 235));
        panelUp.add(lblPower, c7);

        GridBagConstraints c8 = new GridBagConstraints();
        c8.weightx = 0;
        c8.weighty = 0;
        c8.gridx = 3;
        c8.gridy = 1;
        c8.gridwidth = 1;
        c8.gridheight = 1;
        ImageIcon send = new ImageIcon(getClass().getResource("/sendI.png"));
        lblSend = new JLabel("send", send, JLabel.CENTER);
        lblSend.setPreferredSize(new Dimension(235, 235));
        panelUp.add(lblSend, c8);
        frame.add(panelUp, BorderLayout.CENTER);

        panelButtom = new JPanel();
        panelButtom.setLocation(0, 450);

        stdRules = new JTextPane();
        stdRules.setBackground(Color.lightGray);
        stdRules.setPreferredSize(new Dimension(600, 195));
        //stdRules.setLocation(50, 450);
        stdRules.setText("STANDARD RULES: \n" +
                "1. After each action, SEND button should be pressed;\n" +
                "2. To activate god's power press the POWER button;\n" +
                "3. God and Player have the same color (gray/pink/blue);\n" +
                "4. Standard win condition: move UP from the 2nd level to the 3rd;\n" +
                "5. You can move UP only 1 level, DOWN - any;\n" +
                "6. Complete Tower consists of 4 levels, from the 1st to DOME levels, gradually built\n");
        stdRules.setAlignmentX(JTextPane.LEFT_ALIGNMENT);
        stdRules.setEditable(false);
        stdRules.setFont(new Font(Font.SERIF, Font.BOLD, 18));
        panelButtom.add(stdRules, BorderLayout.CENTER);

        turnActions = new JTextPane();
        turnActions.setLocation(700, 450);
        turnActions.setBackground(Color.lightGray);
        // turnActions.setPreferredSize(new Dimension(350, 195));
        turnActions.setText("ACTIONS OF A TURN: \n" +
                "1. Place your workers;\n" +
                "2. Select worker to move;\n" +
                "3. select the Coordinate to move;\n" +
                "4. Move selected worker;\n" +
                "5. Select the Coordinate to build;\n" +
                "6. Build\n");
        turnActions.setAlignmentX(JTextPane.LEFT_ALIGNMENT);
        turnActions.setEditable(false);
        turnActions.setFont(new Font(Font.SERIF, Font.BOLD, 18));
        panelButtom.add(turnActions, BorderLayout.CENTER);

        JButton btn = new JButton("back to main");
        btn.setPreferredSize(new Dimension(70, 30));
        btn.addActionListener(e -> {
            frame.dispose();
        });
        panelButtom.add(btn);
        frame.add(panelButtom, BorderLayout.SOUTH);




        frame.setBackground(Color.GRAY);
        frame.setSize( 1650, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
