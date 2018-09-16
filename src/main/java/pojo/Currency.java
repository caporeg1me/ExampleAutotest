package pojo;

public class Currency {

    private float prev;
    private float current;
    private String state;

    public Currency(float prev, float current, String state) {
        this.prev = prev;
        this.current = current;
        this.state = state;
    }

    public Currency() {

    }

    public float getPrev() {
        return prev;
    }

    public void setPrev(float prev) {
        this.prev = prev;
    }

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
