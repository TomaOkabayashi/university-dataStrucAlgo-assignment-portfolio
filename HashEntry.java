// From Prac 7
public class HashEntry {
    private String key;
    private Shop value;
    private int state; // Using integer constants instead of enum

    public static final int UNUSED = 0;
    public static final int USED = 1;
    public static final int FREE = 2;

    public HashEntry() {
        key = null;
        value = null;
        state = UNUSED; // Initialize to UNUSED
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Shop value) {
        this.value = value;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
