package it.polimi.ingsw.client;

import javax.swing.*;

public class guiTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //ClientBackEnd model=new ClientBackEnd();
                GameGUI gameGui = new GameGUI();
                //gameGui.initGUI(model);

            }
        });
    }
}
