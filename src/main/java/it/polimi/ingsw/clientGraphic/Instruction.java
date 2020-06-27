package it.polimi.ingsw.clientGraphic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Instruction extends JFrame implements ActionListener {

    private JFrame frame;

    public Instruction(){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new JFrame();
                JPanel panel = new JPanel();
                frame.add(panel);
                GridBagLayout gridBagLayout = new GridBagLayout();
                panel.setLayout(gridBagLayout);

                String URI="/instr.gif";
                BufferedImage img = null;

                try {
                    img = ImageIO.read(getClass().getResource(URI));
                } catch (IOException h) {
                    h.printStackTrace();
                }
                assert img != null;
                Image dimg = img.getScaledInstance(490,720, Image.SCALE_SMOOTH);


                JLabel lblXmpl = new JLabel();

                lblXmpl.setIcon(new ImageIcon(dimg));

                GridBagConstraints c = new GridBagConstraints();
                c.weightx= 0;
                c.weighty = 0;
                c.gridx = 0;
                c.gridy = 0;
                c.gridwidth = 1;
                c.gridheight = 2;
                panel.add(lblXmpl, c);


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
                    frame.dispose();
                });
                panel.add(btn, c5);



                frame.setBackground(Color.GRAY);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

            }
        });


    }
 /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Instruction();
            }
        });
    }
*/
    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
