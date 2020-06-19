package it.polimi.ingsw;

import it.polimi.ingsw.view.MenuGUI;

import javax.swing.*;

public class App
{
    public static void main( String[] args )
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuGUI();
            }
        });

    }
}

