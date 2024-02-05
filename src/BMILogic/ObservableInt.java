package BMILogic;

public interface ObservableInt {

    void addObserver(ObserverInt observer);

    void notifyObservers();
}
