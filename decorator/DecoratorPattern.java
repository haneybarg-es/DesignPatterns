interface I { // common interface
    void doIt();
}

class TPCChannel implements I { // concrete Channel
    String message;

    public TPCChannel (String message) {
      this.message = message;
    }


    public void doIt() {
        System.out.println("Sending " + this.message + " via TPC");
    }
}

class UPDChannel implements I { // concrete Channel
    String message;


    public UPDChannel (String message) {
      this.message = message;
    }


    public void doIt() {
        System.out.println("Sending " + this.message + " via UPD");
    }
}

abstract class ChannelDecorator implements I { // ChannelDecorator
    private I core;

    public ChannelDecorator(I inner) {
        core = inner;
    }


    public void doIt() {
        core.doIt();
    }
}

class BufferChannel extends ChannelDecorator{ // concrete decorator
    public BufferChannel(I inner) {
        super(inner);
    }


    public void doIt() {
        super.doIt();
        doX();
    }

    private void doX() {
        System.out.println("BufferChannel");
    }
}

class LoggerChannel extends ChannelDecorator{ // concrete decorator
    public LoggerChannel(I inner) {
        super(inner);
    }


    public void doIt()  {
        super.doIt();
        doY();
    }

    private void doY() {
        System.out.println("LoggerChannel");
    }
}

class ZipChannel extends ChannelDecorator{ // concrete decorator C
    public ZipChannel(I inner) {
        super(inner);
    }


    public void doIt()  {
        super.doIt();
        doZ();
    }

    private void doZ() {
        System.out.println("ZipChannel");
    }
}

public class DecoratorPattern {
    public static void main( String[] args ) {
        I[] array = {new BufferChannel(new TPCChannel("lala")), new LoggerChannel(new BufferChannel(new TPCChannel("kaka"))),
                new ZipChannel(new LoggerChannel(new BufferChannel(new UPDChannel("hihi"))))};

        for (I anArray : array) {
            anArray.doIt();
        }
    }
}
