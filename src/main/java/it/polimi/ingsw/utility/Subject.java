package it.polimi.ingsw.utility;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject<T> {
    //Observer pattern
    private List<Observer<T>> list= new ArrayList<Observer<T>>();

    public void addObserver(Observer<T> observer) {
        list.add(observer);
    }

    public void notify(T message){
        for(Observer<T> o : list) o.update(message);
    }

}
