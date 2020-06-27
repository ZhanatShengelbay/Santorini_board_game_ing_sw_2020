package it.polimi.ingsw.clientGraphic;


import it.polimi.ingsw.view.Event;
import it.polimi.ingsw.model.EnumDivinity;

import it.polimi.ingsw.model.ModelView;
import it.polimi.ingsw.model.TypeBlock;
import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.view.Client;

import java.util.Scanner;

public class CLI implements Observer<Object>, Runnable {

    Client client;
    Scanner stdin = new Scanner(System.in);
    Thread t0;
    String[][] board = new String[15][19];
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\033[1;31m";
    public static final String ANSI_PURPLE = "\033[1;35m";
    public static final String ANSI_BLUE = "\033[1;34m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[102m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";


    public CLI(Client client){
        this.client = client;
        System.out.println("\n 6MMMMb\\                                               68b           68b \n" +
                                   "6M'    `                       /                       Y89           Y89 \n" +
                                   "MM          ___    ___  __    /M       _____   ___  __ ___ ___  __   ___ \n" +
                                   "YM.       6MMMMb   `MM 6MMb  /MMMMM   6MMMMMb  `MM 6MM `MM `MM 6MMb  `MM \n" +
                                   " YMMMMb  8M'  `Mb   MMM9 `Mb  MM     6M'   `Mb  MM69 \"  MM  MMM9 `Mb  MM \n" +
                                   "     `Mb     ,oMM   MM'   MM  MM     MM     MM  MM'     MM  MM'   MM  MM \n" +
                                   "      MM ,6MM9'MM   MM    MM  MM     MM     MM  MM      MM  MM    MM  MM \n" +
                                   "      MM MM'   MM   MM    MM  MM     MM     MM  MM      MM  MM    MM  MM \n" +
                                   "L    ,M9 MM.  ,MM   MM    MM  YM.  , YM.   ,M9  MM      MM  MM    MM  MM \n" +
                                   "MYMMMM9  `YMMM9'Yb._MM_  _MM_  YMMM9  YMMMMM9  _MM_    _MM__MM_  _MM__MM_\n");
        initializeBoard();
    }

    public void initializeBoard(){
        for(int i=0; i < 15; i++){
            for(int j=0; j < 19; j++){
                if(i==0 | i==2 | i==3 | i==5 | i==6 | i==8 | i==9 | i==11 | i==12 | i==14){
                    if(j%4 == 3) board[i][j] = " ";
                    else board[i][j] = "-";
                }
                else{
                    if(j%2 == 0) board[i][j] = "|";
                    else board[i][j] = " ";
                }
            }
        }
    }

    public int xFromGridToBoard(int x){
        if(x == 0) return 1;
        else if(x == 1) return 4;
        else if(x == 2) return 7;
        else if(x == 3) return 10;
        else return 13;
    }

    public int yFromGridToBoard(int y){
        if(y == 0) return 1;
        else if(y == 1) return 5;
        else if(y == 2) return 9;
        else if(y == 3) return 13;
        else return 17;
    }

    public void printBoard(){
        for(int i=0; i < 15; i++){
            for(int j=0; j < 19; j++){
                System.out.print(board[i][j]);
                if(j == 18) System.out.print("\n");
            }
        }
    }

    public synchronized boolean checkInput(String input){
        if(input != null && !input.replace(" ", "").isEmpty()){
            return true;
        }
        else return false;
    }

    public void handle(String string){
        System.out.println(string);
    }

    public void handle(ModelView model){

        if(model.getMessage()!=null)
            handle(model.getMessage());
        for(int i=0; i < 5; i++){
            for(int j=0; j < 5; j++){
                if(model.getGrid().getTile(i, j).getHeight() == TypeBlock.FLOOR){
                    board[xFromGridToBoard(i)][yFromGridToBoard(j)] = ANSI_GREEN_BACKGROUND;
                }
                else if(model.getGrid().getTile(i, j).getHeight() == TypeBlock.FIRST){
                    board[xFromGridToBoard(i)][yFromGridToBoard(j)] = ANSI_CYAN_BACKGROUND;
                }
                else if(model.getGrid().getTile(i, j).getHeight() == TypeBlock.SECOND){
                    board[xFromGridToBoard(i)][yFromGridToBoard(j)] = CYAN_BACKGROUND_BRIGHT;
                }
                else if(model.getGrid().getTile(i, j).getHeight() == TypeBlock.THIRD){
                    board[xFromGridToBoard(i)][yFromGridToBoard(j)] = ANSI_YELLOW_BACKGROUND;
                }
                else if(model.getGrid().getTile(i, j).getHeight() == TypeBlock.DOME){
                    board[xFromGridToBoard(i)][yFromGridToBoard(j)] = WHITE_BACKGROUND_BRIGHT;
                }

                if(model.getGrid().getTile(i, j).getWorker() != null){
                    for(int c=0; c < model.getNumOfPlayers(); c++){
                        if(model.getGrid().getTile(i, j).getWorker().getPlayer().getPlayerID().equals(model.getPlayer(c)) && c==0) board[xFromGridToBoard(i)][yFromGridToBoard(j)] = board[xFromGridToBoard(i)][yFromGridToBoard(j)] + ANSI_RED;
                        if(model.getGrid().getTile(i, j).getWorker().getPlayer().getPlayerID().equals(model.getPlayer(c)) && c==1) board[xFromGridToBoard(i)][yFromGridToBoard(j)] = board[xFromGridToBoard(i)][yFromGridToBoard(j)] + ANSI_BLUE;
                        if(model.getGrid().getTile(i, j).getWorker().getPlayer().getPlayerID().equals(model.getPlayer(c)) && c==2) board[xFromGridToBoard(i)][yFromGridToBoard(j)] = board[xFromGridToBoard(i)][yFromGridToBoard(j)] + ANSI_PURPLE;
                    }
                    board[xFromGridToBoard(i)][yFromGridToBoard(j)] = board[xFromGridToBoard(i)][yFromGridToBoard(j)] + "&";
                }
                else board[xFromGridToBoard(i)][yFromGridToBoard(j)] = board[xFromGridToBoard(i)][yFromGridToBoard(j)] + " ";

                board[xFromGridToBoard(i)][yFromGridToBoard(j)] = board[xFromGridToBoard(i)][yFromGridToBoard(j)] + ANSI_RESET;
            }
        }
        printBoard();
    }

    public void handle(Event event){
        switch (event){
            case SETUP:
                System.out.println("Select gods for you and your opponent from this list:\n");
                for(EnumDivinity s : EnumDivinity.values()){
                    System.out.println(" "+s.name()+"\n");
                }
                break;

            case GODCHOICE:
                System.out.println("Select gods from this list:\n");
                break;
            case WIN:
                System.out.println("You win!!:\n");
                break;
            case LOST:
                System.out.println("You lost!!:\n");

        }
    }
    public Thread asyncReadFromConsole(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String input = stdin.nextLine();
                        if (checkInput(input)){
                            if(input.toUpperCase().compareTo("QUIT") ==  0) {
                                client.send("quit");
                                return;
                            }
                            client.send(input);
                        }
                        else System.out.println("Can't send empty strings");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
        return t;
    }

    @Override
    public void run() {
        try{
            t0 = asyncReadFromConsole();
            t0.join();
            stdin.close();
        } catch(InterruptedException e){
            System.out.println("Connection closed from the it.polimi.ingsw.client side");
        }
    }

    @Override
    public void update(Object message) {
        if(message instanceof String) handle((String)message);
        else if(message instanceof ModelView) handle((ModelView)message);
        else if(message instanceof Event)handle((Event)message);
        else if(message instanceof String[]){
            String [] input=(String[])message;
            for(int i=0;i<input.length;i++){
                handle(input[i]);
            }
        }
    }

}
