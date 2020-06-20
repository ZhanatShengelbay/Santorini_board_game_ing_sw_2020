package it.polimi.ingsw.view;

import it.polimi.ingsw.client.ClientBackEnd;
import it.polimi.ingsw.client.MenuGUI;

import javax.swing.*;

public class initGui {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClientBackEnd tmp=new ClientBackEnd(null);
                new MenuGUI(tmp);
            }
        });

    }
}
