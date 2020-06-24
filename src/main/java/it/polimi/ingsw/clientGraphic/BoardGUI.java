package it.polimi.ingsw.clientGraphic;

import it.polimi.ingsw.utility.Coordinate;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static javax.swing.PopupFactory.*;

public class BoardGUI extends JFrame {

    static final int N_ROWS = 5;
    static final int N_COLS = 5;


    GraphicTile[][] grid;
    JTextField textBox = new JTextField();

    JPanel rightPanel;
    JPanel gridPanel;
    Button send;
    Button power;
    GraphicTile selected;
    Border commonBorder=BorderFactory.createLineBorder(new Color(6, 9, 12), 2);

    public BoardGUI(String title) throws HeadlessException {
        super(title);
    }

    public void initBoard(BackEndGui backEnd) {

        this.setLayout(new BorderLayout());

        rightPanel=new JPanel();
        rightPanel.setLayout(new BorderLayout());
        JPanel buttonsPanel =new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        //playersPanel(backEnd);



        createGrid(backEnd);
        textBox.setPreferredSize(new Dimension(this.getWidth(),80));

        send= new SendButton(this, backEnd);
        send.setPreferredSize(new Dimension(100,100));
        buttonsPanel.add(send);

        power = new PowerButton(this, backEnd);
        power.setPreferredSize(new Dimension(100,100));
        buttonsPanel.add(power);

        rightPanel.add(buttonsPanel,BorderLayout.SOUTH);

        this.setResizable(false);
        this.add(textBox, BorderLayout.SOUTH);
        this.add(rightPanel, BorderLayout.EAST);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                backEnd.sendMessage("QUIT");
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

    }

    public void createGrid(BackEndGui model){

        grid=new GraphicTile[N_ROWS][N_COLS];
        BufferedImage img = null;
        try {

            img = ImageIO.read(getClass().getResource("/tileImage/floor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert img != null;
        Image dimg= img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        gridPanel=new JPanel();
        gridPanel.setLayout(new GridLayout(5,5));

        GraphicListener listener=new GraphicListener(this, model);
        for(int i=0;i<N_ROWS;i++) {
            for (int j = 0; j < N_COLS; j++) {
                GraphicTile t = new GraphicTile(new Coordinate(i, j));
                t.addMouseListener(listener);
                t.setPreferredSize(new Dimension(150,150));
                t.setIcon(new ImageIcon(dimg));
                t.setBorder(commonBorder);
                t.setOpaque(true);
                grid[i][j]=t;
                gridPanel.add(t);
            }
        }
        this.add(gridPanel,BorderLayout.WEST);
        this.pack();;

    }

    public GraphicTile[][] getGrid() {
        return grid;
    }

    public void playersPanel(BackEndGui backEnd){
        JPanel result=new JPanel();
        result.setLayout(new GridLayout(3,1));


        for(String s : backEnd.players){
            JPanel tmp=new JPanel();

            final Popup[] popup = new Popup[1];
            tmp.setLayout(new BorderLayout());
            JLabel pic=new JLabel();
            JLabel name=new JLabel();
            BufferedImage img = null;
            try {
                img = ImageIO.read(getClass().getResource("/godsImage/" +backEnd.getPlayersGods().get(s).toLowerCase()+".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert img != null;
            Image dimg= img.getScaledInstance(128, 200, Image.SCALE_SMOOTH);
            pic.setIcon(new ImageIcon(dimg));
            pic.setHorizontalAlignment(SwingConstants.CENTER);
            name.setText(s.toUpperCase());
            name.setHorizontalAlignment(SwingConstants.CENTER);
            Color color=backEnd.getPlayerColor(s);
            //too dark the normal blue
            if(color.equals(Color.BLUE))
                color=new Color(0x4E96CB);
            name.setBackground(color);
            name.setOpaque(true);

            /*tmp.add(name);
            tmp.add(pic);*/
            tmp.add(name,BorderLayout.SOUTH);
            tmp.add(pic,BorderLayout.CENTER);



            tmp.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    JLabel description=new JLabel(PowerDescription.getDescription(backEnd.getPlayersGods().get(s).toUpperCase()));
                    popup[0] = getSharedInstance().getPopup(e.getComponent(), description, e.getXOnScreen(), e.getYOnScreen());
                    popup[0].show();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(popup[0]!=null)
                        popup[0].hide();
                }
            });
            result.add(tmp);


        }

        rightPanel.add(result,BorderLayout.NORTH);
        rightPanel.updateUI();
        this.pack();

    }
    public GraphicTile getTile(Coordinate coordinate){
        return grid[coordinate.getX()][coordinate.getY()];

    }

    public void selectionTile(GraphicTile t){

        if(selected!=null)
            selected.setBorder(commonBorder);
        this.selected=t;
        SwingUtilities.invokeLater(() -> selected.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3)));


    }


    public void printText(String message){
        textBox.setVisible(false);
        textBox.setText(message);
        textBox.setVisible(true);
    }



}
