package it.polimi.ingsw.view;

import javax.swing.*;

public class ViewMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuGUI();
            }
        });
    }
}
