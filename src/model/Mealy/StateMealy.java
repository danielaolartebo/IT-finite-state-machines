package model.Mealy;

public class StateMealy {

    private String state;
    private boolean conexo;

    public StateMealy(String state){
        this.state = state;
        conexo = false;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isConexo() {
        return conexo;
    }

    public void setConexo(boolean conexo) {
        this.conexo = conexo;
    }
}
