package Observer;

import java.util.ArrayList;
import java.util.List;

interface StockObservable {
    public void addStock(int stock);
    public void addObserver(Observer observer);
}

class IphoneStockObservable implements StockObservable {
    private final List<Observer> observers = new ArrayList<>();
    private int stock;

    public IphoneStockObservable(int stock) {
        this.stock = stock;
    }

    @Override
    public void addStock(int stock) {
         this.stock = stock;
        if (this.stock == 0) {
            for (Observer observer : observers) {
                observer.Notify();
            }
        }
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

}

interface Observer {
    void Notify();
}

class EmailObserver implements Observer {
    @Override
    public void Notify(){
        sendNotification();
    }
    public void sendNotification() {
        System.out.println("Sent email notifation!");
    }
}

class PhoneObserver implements Observer {
     @Override
    public void Notify(){
        sendNotification();
    }
    public void sendNotification() {
        System.out.println("Sent phone notifation!");
    }
}

public class Main {
    public static void main(String[] args) {
        Observer o1 = new EmailObserver();
        Observer o2 = new EmailObserver();
        Observer o3 = new PhoneObserver();
        Observer o4 = new PhoneObserver();

        StockObservable observable = new IphoneStockObservable(5);
        observable.addObserver(o1);
        observable.addObserver(o2);
        observable.addObserver(o3);
        observable.addObserver(o4);
        observable.addStock(3);
        observable.addStock(0);
    }
}
