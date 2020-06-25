package it.polimi.ingsw.clientGraphic;

import it.polimi.ingsw.model.EnumDivinity;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

import static javax.swing.PopupFactory.getSharedInstance;

public class GodSetupUI extends JFrame implements ActionListener, ListSelectionListener, ItemListener {
    JLabel bckg;

    int numOfPlayer;
    BackEndGui backEnd;

    private JPanel panelCenter;
    private JPanel panelSouth;

    private Border borderCenter = BorderFactory.createEmptyBorder(10, 10, 10, 10);
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
    private JLabel lblDescription;
    private JLabel lblListAllGods;
    private JLabel lblListGameGods;
    private JLabel lblSelectedAllGods;
    private JLabel lblSelectedGameGods;

    private JList<String> listAllGods;
    private JList<String> listGameGods;

    private final Font fontBold = new Font(Font.DIALOG, Font.BOLD,18);
    private final Font fontPlain = new Font(Font.DIALOG, Font.PLAIN, 18);

    private DefaultListModel<String> listModelAllGods = new DefaultListModel<>();
    private DefaultListModel<String> listModelGameGods = new DefaultListModel<>();

    public GodSetupUI(int numOfPlayer, BackEndGui backEnd){
        this.numOfPlayer=numOfPlayer;
        this.backEnd=backEnd;
        backEnd.setSetupGui(this);
        setFonts();

        setupSplash();


    }
    public void setupSplash(){
        revalidate();
        repaint();
        setSize(1280, 720);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon(getClass().getResource("/santorini_splash.jpg"));
        bckg = new JLabel("", img, JLabel.CENTER);
        bckg.setBounds(0,0,1280, 720);
        add(bckg);

        this.setVisible(true);
    }

    public void createGodSetup(){
        //this.removeAll();
        this.setTitle("Gods select");
        JPanel contents = new JPanel();
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
        lblDescription= new JLabel();
        lblDescription.setOpaque(true);
        boxPower.add(lblGodCard);
        boxPower.add(lblDescription);

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
        btnStartGame.addActionListener(e -> {
            int gameGods = listModelGameGods.getSize();
            if (gameGods == numOfPlayer){
                setGameGods();
               new GodSetupUI(numOfPlayer,backEnd);
               this.dispose();


            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Choose "+numOfPlayer+" gods for you and your opponent"+(numOfPlayer==3?"s":""));
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

        this.revalidate();
        setSize(new Dimension(1280, 720));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void createGodChoice(String[] gods){
        this.setTitle("Gods select");
        JPanel contents = new JPanel();
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
        btnStartGame.addActionListener(e -> {
            int gameGods = listModelGameGods.getSize();
            if (gameGods == 1){
                setGameGods();
                backEnd.createBoardGui();
                this.dispose();
                backEnd.update("wait other players");
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Choose one god");
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

        for(String s : gods){
            listModelAllGods.addElement(s.toUpperCase());
        }

        setSize(new Dimension(1280, 720));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        setLocationRelativeTo(null);
        setVisible(true);


    }

    private void initAllGodsModel(){
        for(EnumDivinity s : EnumDivinity.values()){
            listModelAllGods.addElement(s.toString());
        }
    }

    protected void setGameGods(){
        StringBuilder message=new StringBuilder();
        for(int i=0;i<listModelGameGods.getSize();i++){
            message.append(listModelGameGods.get(i));
            message.append(" ");
        }

        backEnd.sendMessage(message.toString());

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

    private void changeCardGame(){
        int selectedItem = listGameGods.getSelectedIndex();
        if (selectedItem == -1){
            lblCard.setIcon(null);

            return;
        }
        String cardName = listGameGods.getSelectedValue();


        cardName =  "/godsCard/" + cardName + ".png";

        lblCard.setIcon(new ImageIcon(getClass().getResource(cardName)));


    }

    private void changeCardAll(){
        int selectedItem = listAllGods.getSelectedIndex();
        if (selectedItem == -1){
            lblCard.setIcon(null);

            return;
        }

        String cardName = listAllGods.getSelectedValue();
        cardName = "/godsCard/" + cardName + ".png";
        lblCard.setIcon(new ImageIcon(getClass().getResource(cardName)));



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
            changeCardAll();
            displaySelectedItems();
            return;
        }
        if (source == listGameGods){
            changeCardGame();
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

}
