public interface Observer {
  public void update(Subject s);
}


public class Subject {
  private List observers = new LinkedList();


  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  public void notifyObservers() {
    Iterator it = observers.iterator();
    while (it.hasNext()) {
      Observer obs= (Observer) it.next();
      obs.update(this); 
    }
  }
}

public class Temperature extends Subject  {
  private double value;
  public double getValue() {
    return value;
  }
  public void setValue(double value) {
    This.value = value;
    notifyObservers();
  }
}

public class TermometerCelsius implements Observer  {
  public void update(Subject s) {
    double value = ((Temperature) s).getValue();
    System.out.println("Celsius: " + value);
  }
}

public class TermometerFahrenheit implements Observer {
  public void update(Subject s) {
    double value = 1.8 * ((Temperature) s).getValue() + 32;
    System.out.println("Fahrenheit: " + value);
  }
}

public class TesteTemperature {
  public static void main(String[] args) {
    Temperature t = new Temperature(10);
    t.addObserver(new TermometerCelsius ());
    t.addObserver(new TermometerFahrenheit ());
    t.setValue(100);
  }
}

