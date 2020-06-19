package it.polimi.ingsw.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ThreePlayerGod extends JFrame implements ActionListener, ListSelectionListener, ItemListener {

    private JPanel contents;
    private JPanel panelCenter;
    private JPanel panelSouth;

    private Border borderCenter = BorderFactory.createEmptyBorder(10,10,10,10);
    private Border borderContent = BorderFactory.createEmptyBorder(0,0,10,0);
    private Border borderList = BorderFactory.createLineBorder(Color.gray, 1);

    private Box boxButton = Box.createVerticalBox();
    private Box boxListAllGods = Box.createVerticalBox();
    private Box boxListGameGods = Box.createVerticalBox();
    private Box boxPower = Box.createVerticalBox();

    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnRemoveAll;

    private JLabel lblCard;
    private JLabel lblListAllGods;
    private JLabel lblListGameGods;
    private JLabel lblSelectedAllGods;
    private JLabel lblSelectedGameGods;

    private JList<String> listAllGods;
    private JList<String> listGameGods;

    private String [] allGods = {
            "APOLLO", "ARTEMIS", "ATHENA", "ATLAS", "DEMETER", "HEPHAESTUS", "MINOTAUR", "PAN", "PROMETHEUS", "APHRODITE", "ARES", "ZEUS"};

    private DefaultListModel<String> listModelAllGods = new DefaultListModel<>();
    private DefaultListModel<String> listModelGameGods = new DefaultListModel<>();

    private final Font fontBold = new Font(Font.DIALOG, Font.BOLD,18);
    private final Font fontPlain = new Font(Font.DIALOG, Font.PLAIN, 18);

    public ThreePlayerGod(){
        super("Gods");
        setFonts();
        contents = new JPanel();
        contents.setBorder(borderContent);
        contents.setLayout(new BorderLayout());
        setContentPane(contents);

        JLabel lblTitle = new JLabel("GODS: ", SwingConstants.CENTER);
        lblTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        contents.add(lblTitle, BorderLayout.NORTH);

        panelCenter = new JPanel();
        panelCenter.setBorder(borderCenter);

        lblListAllGods = new JLabel("ALL GODS: ");
        lblListAllGods.setAlignmentX(LEFT_ALIGNMENT);

        initAllGodsModel();
        listAllGods = new JList<>(listModelAllGods);
        listAllGods.setAlignmentX(LEFT_ALIGNMENT);
        listAllGods.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listAllGods.setBorder(borderList);
        JScrollPane scrollAllGods = new JScrollPane(listAllGods);
        scrollAllGods.setAlignmentX(LEFT_ALIGNMENT);
        scrollAllGods.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollAllGods.setPreferredSize(new Dimension(200,400));
        boxListAllGods.add(lblListAllGods);
        boxListAllGods.add(scrollAllGods);
        panelCenter.add(boxListAllGods);

        panelCenter.add(Box.createRigidArea(new Dimension(10,1)));

        btnAdd = new JButton("    ADD >    ");
        btnAdd.addActionListener(this);
        btnRemove = new JButton("   < REMOVE  ");
        btnRemove.addActionListener(this);
        btnRemoveAll = new JButton("<< REMOVE ALL");
        btnRemoveAll.addActionListener(this);
        btnAdd.setSize(new Dimension(50,30));
        btnRemove.setSize(new Dimension(50,30));
        btnRemoveAll.setSize(new Dimension(50,30));
        boxButton.add(btnAdd);
        boxButton.add(Box.createRigidArea(new Dimension(1, 20)));
        boxButton.add(btnRemove);
        boxButton.add(Box.createRigidArea(new Dimension(1, 20)));
        boxButton.add(btnRemoveAll);
        panelCenter.add(boxButton);

        panelCenter.add(Box.createRigidArea(new Dimension(10,1)));

        lblListGameGods = new JLabel("GAME GODS: ");
        lblListGameGods.setAlignmentX(LEFT_ALIGNMENT);

        listGameGods = new JList<>(listModelGameGods);
        listGameGods.setAlignmentX(LEFT_ALIGNMENT);
        listGameGods.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listGameGods.setBorder(borderList);
        JScrollPane scrollGameGods = new JScrollPane(listGameGods);
        scrollGameGods.setPreferredSize(new Dimension(200, 400));
        scrollGameGods.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollGameGods.setAlignmentX(LEFT_ALIGNMENT);
        boxListGameGods.add(lblListGameGods);
        boxListGameGods.add(scrollGameGods);
        panelCenter.add(boxListGameGods);
        contents.add(panelCenter, BorderLayout.CENTER);

        panelCenter.add(Box.createRigidArea(new Dimension(20,1)));

        JLabel lblGodCard = new JLabel("CARD: ");
        lblGodCard.setAlignmentX(LEFT_ALIGNMENT);

        lblCard = new JLabel(new ImageIcon());
        lblCard.setAlignmentX(LEFT_ALIGNMENT);
        lblCard.setPreferredSize(new Dimension(200,300));

        boxPower.add(lblGodCard);
        boxPower.add(Box.createRigidArea(new Dimension(100, 30)));
        boxPower.add(lblCard);
        panelCenter.add(boxPower);
        contents.add(panelCenter, BorderLayout.CENTER);

        panelSouth = new JPanel();
        JLabel lblSelectedAllGodsLabel = new JLabel("Selected ALL GODS: ");
        lblSelectedAllGods = new JLabel();
        JLabel lblSelectedGameGodsLabel = new JLabel("Selected GAME GODS: ");
        lblSelectedGameGods = new JLabel();
        JButton btnStartGame = new JButton("start the Game");
        btnStartGame.setSize(new Dimension(70, 30));
        btnStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int gameGods = listModelGameGods.getSize();
                if (gameGods == 3){
                    setGameGods();
                } else {
                    JOptionPane.showMessageDialog(null,"for 3-player game exactly 3 Gods have to be selected, please, try again");
                }
            }
        });

        panelSouth.add(lblSelectedAllGodsLabel);
        panelSouth.add(lblSelectedAllGods);
        panelSouth.add(Box.createRigidArea(new Dimension(100,1)));
        panelSouth.add(lblSelectedGameGodsLabel);
        panelSouth.add(lblSelectedGameGods);
        panelSouth.add(Box.createRigidArea(new Dimension(100, 1)));
        panelSouth.add(btnStartGame);
        contents.add(panelSouth, BorderLayout.SOUTH);

        listGameGods.addListSelectionListener(this);
        listAllGods.addListSelectionListener(this);

        setSize(new Dimension(1600, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    protected void setGameGods(){
        //pass the game gods to setup controller
    }

    private void initAllGodsModel(){
        for (String s: allGods){
            listModelAllGods.addElement(s);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnAdd){
            addItem();
            return;
        }
        if (source == btnRemove){
            removeItem();
            return;
        }
        if (source == btnRemoveAll){
            removeAllItems();
            return;
        }

    }
    private void addItem(){
        int selectedItem = listAllGods.getSelectedIndex();
        if (selectedItem == -1){
            return;
        }
        String addedItem = listAllGods.getSelectedValue();
        listModelAllGods.remove(selectedItem);
        displaySelectedItems();
        int gameGods = listModelGameGods.getSize();
        if (gameGods == 0){
            listModelGameGods.addElement(addedItem);
            return;
        }
        for (int i = 0; i < gameGods; i++){
            String item = listModelGameGods.elementAt(i);
            int compare = addedItem.compareToIgnoreCase(item);
            if (compare<0){
                listModelGameGods.add(i, addedItem);
                return;
            }
        }
        listModelGameGods.addElement(addedItem);
    }

    private void changeCard(){
        int selectedItem = listGameGods.getSelectedIndex();
        if (selectedItem == -1){
            lblCard.setIcon(null);
            return;
        }
        String cardName = listGameGods.getSelectedValue();
        cardName = "view/Images/" + cardName + ".png";
        lblCard.setIcon(new ImageIcon(cardName));
    }

    private void displaySelectedItems(){
        int selectedItem;
        String itemName;

        selectedItem = listAllGods.getSelectedIndex();
        if (selectedItem == -1){
            lblSelectedAllGods.setText("");
        } else {
            itemName = listAllGods.getSelectedValue();
            lblSelectedAllGods.setText(itemName);
        }
        selectedItem = listGameGods.getSelectedIndex();
        if (selectedItem == -1){
            lblSelectedGameGods.setText("");
        }else {
            itemName = listGameGods.getSelectedValue();
            lblSelectedGameGods.setText(itemName);
        }
    }

    private void removeItem(){
        int selectedItem = listGameGods.getSelectedIndex();
        if (selectedItem == -1){
            return;
        }
        String removedItem = listGameGods.getSelectedValue();
        listModelGameGods.remove(selectedItem);
        displaySelectedItems();

        int allGodsSize = listModelAllGods.getSize();
        if (allGodsSize == 0){
            listModelAllGods.addElement(removedItem);
            return;
        }
        for (int i = 0; i < allGodsSize; i++){
            String item = listModelAllGods.elementAt(i);
            int compare = removedItem.compareToIgnoreCase(item);
            if (compare<0){
                listModelAllGods.add(i,removedItem);
                return;
            }
        }
        listModelAllGods.addElement(removedItem);
    }

    private void removeAllItems(){
        listModelAllGods.clear();
        initAllGodsModel();
        listModelGameGods.clear();
        displaySelectedItems();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        Object source = e.getSource();
        if (source == listAllGods){
            displaySelectedItems();
            return;
        }
        if (source == listGameGods){
            changeCard();
            displaySelectedItems();
            return;
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getSource();
        if (source == boxPower){
            displaySelectedItems();
            return;
        }
    }

    private void setFonts(){
        UIManager.put("Button.font", fontBold);
        UIManager.put("Label.font", fontBold);
        UIManager.put("List.font", fontPlain);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ThreePlayerGod();
            }
        });
    }
}
