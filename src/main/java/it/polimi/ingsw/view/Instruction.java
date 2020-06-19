package it.polimi.ingsw.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Instruction extends JFrame implements ActionListener {

    private JFrame frame;

    public Instruction(){

        frame = new JFrame();
        JPanel panel = new JPanel();
        frame.add(panel);
        GridBagLayout gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);

        ImageIcon imgpn = new ImageIcon("src/Images/smallinstr.gif");
        JLabel lblXmpl = new JLabel(imgpn);
        lblXmpl.setSize(690, 450);

        GridBagConstraints c = new GridBagConstraints();
        c.weightx= 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 2;
        panel.add(lblXmpl, c);

        ImageIcon img = new ImageIcon("src/Images/tower.jpg");
        JLabel lblTwr = new JLabel(img);
        lblTwr.setSize(200, 195);


        GridBagConstraints c2 = new GridBagConstraints();
        c2.weightx = 0;
        c2.weighty = 0;
        c2.gridwidth = 1;
        c2.gridheight = 1;
        c2.gridx = 1;
        c2.gridy = 0;
        panel.add(lblTwr, c2);

        ImageIcon imgAcn = new ImageIcon("src/Images/santorini.gif");
        JLabel label = new JLabel(imgAcn);
        label.setSize(700, 450);

        GridBagConstraints c3 = new GridBagConstraints();
        c3.weightx = 0;
        c3.weighty = 0;
        c3.gridwidth = 1;
        c3.gridheight = 1;
        c3.gridx = 2;
        c3.gridy = 0;
        panel.add(label, c3);

        GridBagConstraints c4 = new GridBagConstraints();
        c4.weightx = 0;
        c4.weighty = 0;
        c4.gridx = 1;
        c4.gridy = 1;
        c4.gridwidth = 2;
        c4.gridheight = 1;

        JLabel lblTxt = new JLabel();
        lblTxt.setSize(300, 200);
        lblTxt.setText("TO KNOW THE GOD'S POWER, PAY ATTENTION TO BUTTOM PART OF THE GOD IMAGE IN THE GOD SELECTION PROCESS");
        panel.add(lblTxt, c4);

        GridBagConstraints c5 = new GridBagConstraints();
        c5.weightx = 0;
        c5.weighty = 0;
        c5.gridx = 2;
        c5.gridy = 2;
        c5.gridwidth = 1;
        c5.gridheight = 1;
        JButton btn = new JButton("back to main");
        btn.setBackground(Color.orange);
        btn.addActionListener(e -> {
            new MenuGUI();
        });
        panel.add(btn, c5);



        frame.setBackground(Color.GRAY);
        frame.setSize( 1650, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Instruction();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
