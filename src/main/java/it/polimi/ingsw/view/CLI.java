package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.utility.Subject;

import java.util.Scanner;

public class CLI extends Subject<String> implements Graphic, Observer<Object>, Runnable {

    Scanner stdin = new Scanner(System.in);
    boolean active = true;

    public synchronized boolean isActive(){
        return active;
    }

    public synchronized void setActive(boolean active){
        this.active = active;
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

    public void handle(Model model){
        model.getGrid().print();
    }

    public Thread asyncReadFromConsole(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isActive()) {
                        String input = stdin.nextLine();
                        if (checkInput(input)){
                            if(input.compareTo("quit") ==  0) {
                                System.out.println("CLI CLOSING");
                                CLI.this.notify("quit");
                                break;
                            }
                            CLI.this.notify(input);
                        }
                        else System.out.println("Can't send empty strings");
                    }
                } catch (Exception e){
                    setActive(false);
                    stdin.close();
                }
            }
        });
        t.start();
        return t;
    }

    @Override
    public void run() {
        try{
            Thread t0 = asyncReadFromConsole();
            t0.join();
        } catch(InterruptedException e){
            System.out.println("Connection closed from the client side");
        } finally {
            setActive(false);
            stdin.close();
        }
    }

    @Override
    public void update(Object message) {
        if(message instanceof String) handle((String)message);
        else if(message instanceof Model) handle((Model)message);
    }
}
