package game2048;

public class Driver {
    private int value;

    public Driver(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    @Override
    public String toString() {
        return value == 0 ? "" : String.valueOf(value);
    }
}