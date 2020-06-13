package it.polimi.ingsw.client;

import it.polimi.ingsw.utility.Coordinate;
import it.polimi.ingsw.view.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameGUI extends JFrame {

    static final int N_ROWS = 5;
    static final int N_COLS = 5;



    GraphicTile[][] grid=new GraphicTile[N_ROWS][N_COLS];
    JTextField textBox = new JTextField();
    Button send;
    Button power;
    GraphicTile selected;
    Border commonBorder=BorderFactory.createLineBorder(new Color(6, 9, 12), 2);



    public void initGUI(ClientBackEnd backEnd){
        this.setLayout(new BorderLayout());

        JPanel rightPanel =new JPanel();
        rightPanel.setLayout(new BorderLayout());
        JPanel buttonsPanel =new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        rightPanel.add(buttonsPanel,BorderLayout.SOUTH);

        JPanel gridPanel = createGrid(backEnd);
        this.add(gridPanel, BorderLayout.WEST);
        textBox.setPreferredSize(new Dimension(this.getWidth(),100));

        send= new SendButton(this, backEnd);
        send.setPreferredSize(new Dimension(100,100));
        buttonsPanel.add(send);

        power = new PowerButton(this, backEnd);
        power.setPreferredSize(new Dimension(100,100));
        buttonsPanel.add(power);

        this.add(textBox, BorderLayout.SOUTH);
        this.add(rightPanel, BorderLayout.EAST);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public JPanel createGrid(ClientBackEnd model){

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/floor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg= img.getScaledInstance(200,200,Image.SCALE_SMOOTH);
        JPanel result= new JPanel();
        result.setLayout(new GridLayout(5,5));

        GraphicListener listener=new GraphicListener(this, model);
        for(int i=0;i<N_ROWS;i++) {
            for (int j = 0; j < N_COLS; j++) {
                GraphicTile t = new GraphicTile(new Coordinate(i, j));
                t.addMouseListener(listener);
                t.setPreferredSize(new Dimension(200,200));
                t.setIcon(new ImageIcon(dimg));
                t.setBorder(commonBorder);
                t.setOpaque(true);
                grid[i][j]=t;
                result.add(t);
            }
        }
        return result;
    }

    public GraphicTile[][] getGrid() {
        return grid;
    }

    public JPanel playersPanel(ClientBackEnd backEnd){
        JPanel result=new JPanel();
        result.setLayout(new FlowLayout());

        for(String s : backEnd.players){
            JPanel tmp=new JPanel();
            tmp.setLayout(new BorderLayout());
            JLabel name=new JLabel();

            name.setText(s);
            name.setBackground(backEnd.getPlayerColor(s));
            tmp.add(name,BorderLayout.CENTER);



        }
        return result;

    }

    public void selectionTile(GraphicTile t){

        if(selected!=null)
            selected.setBorder(commonBorder);
        this.selected=t;
       // selected.updateGraphic("blue",2);
        selected.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3));

    }

    public void printText(String message){
        textBox.setText(message);
    }



}
