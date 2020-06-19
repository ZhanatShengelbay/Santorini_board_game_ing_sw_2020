package it.polimi.ingsw.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditsTo extends JFrame implements ActionListener{
    JFrame content = new JFrame("Credits to: ");

    private JPanel contents;

    JLabel lblTitle;
    JLabel lblMarco;
    JLabel lblMarcoLabel;
    JLabel lblJanet;
    JLabel lblJanetLabel;
    JLabel lblLeo;
    JLabel lblLeoLabel;
    JButton btnBack;

    public CreditsTo(){

        contents = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        contents.setLayout(gridBagLayout);

        lblTitle = new JLabel("Final Project of Software Engineering B course 2019/2020 a.y. " + "      team: CG51");
        lblTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        lblTitle.setPreferredSize(new Dimension(900, 50));

        GridBagConstraints c = new GridBagConstraints();
        c.weightx= 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 1;
        contents.add(lblTitle, c);

        lblMarco = new JLabel(new ImageIcon("src/Images/marco.png"));
        lblMarco.setAlignmentX(LEFT_ALIGNMENT);
        lblMarco.setPreferredSize(new Dimension(300, 300));

        GridBagConstraints c1 = new GridBagConstraints();
        c1.weightx = 0;
        c1.weighty = 0;
        c1.gridx = 0;
        c1.gridy = 1;
        c1.weightx = 1;
        c1.weighty = 1;
        contents.add(lblMarco, c1);

        lblMarcoLabel = new JLabel("Marco Pasqualini");
        lblMarcoLabel.setAlignmentX(LEFT_ALIGNMENT);
        lblMarcoLabel.setPreferredSize(new Dimension(300, 20));

        GridBagConstraints c2 = new GridBagConstraints();
        c2.weightx = 0;
        c2.weighty = 0;
        c2.gridx = 0;
        c2.gridy = 2;
        c2.gridwidth = 1;
        c2.gridheight = 1;
        contents.add(lblMarcoLabel, c2);

        lblJanet = new JLabel((new ImageIcon("src/Images/jan.png")));
        lblJanet.setAlignmentX(LEFT_ALIGNMENT);
        lblJanet.setPreferredSize(new Dimension(300, 300));

        GridBagConstraints c3 = new GridBagConstraints();
        c3.weightx = 0;
        c3.weighty = 0;
        c3.gridx = 1;
        c3.gridy = 1;
        c3.gridwidth = 1;
        c3.gridheight = 1;
        contents.add(lblJanet, c3);

        lblJanetLabel = new JLabel("Zhanat Shengelbayeva");
        lblJanetLabel.setAlignmentX(LEFT_ALIGNMENT);
        lblJanetLabel.setPreferredSize(new Dimension(300, 20));

        GridBagConstraints c4 = new GridBagConstraints();
        c4.weightx = 0;
        c4.weighty =0;
        c4.gridx = 1;
        c4.gridy = 2;
        c4.gridwidth = 1;
        c4.gridheight = 1;
        contents.add(lblJanetLabel, c4);

        lblLeo = new JLabel(new ImageIcon("src/Images/leo.png"));
        lblLeo.setAlignmentX(LEFT_ALIGNMENT);
        lblLeo.setPreferredSize(new Dimension(300, 300));

        GridBagConstraints c5 = new GridBagConstraints();
        c5.weightx = 0;
        c5.weighty =0;
        c5.gridx = 2;
        c5.gridy = 1;
        c5.gridwidth = 1;
        c5.gridheight = 1;
        contents.add(lblLeo, c5);

        lblLeoLabel = new JLabel("Leone Pistori");
        lblLeoLabel.setAlignmentX(LEFT_ALIGNMENT);
        lblLeoLabel.setPreferredSize(new Dimension(300, 20));

        GridBagConstraints c6 = new GridBagConstraints();
        c6.weightx =0;
        c6.weighty= 0;
        c6.gridx = 2;
        c6.gridy = 2;
        c6.gridwidth = 1;
        c6.gridheight = 1;

        contents.add(lblLeoLabel, c6);

        btnBack= new JButton("back to main");
        btnBack.setSize(50, 30);
        btnBack.setLocation(1100, 600);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuGUI();
            }
        });
        GridBagConstraints c7 = new GridBagConstraints();
        c7.weightx = 0;
        c7.weighty = 0;
        c7.gridx = 2;
        c7.gridy = 3;
        c7.gridwidth = 1;
        c7.gridheight = 1;
        contents.add(btnBack, c7);
        content.add(contents);

        content.setSize(1200, 800);
        content.setLocationRelativeTo(null);
        content.setVisible(true);
        content.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new CreditsTo();
//            }
//        });
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
