package model.Moore;

import java.util.ArrayList;

public class StateMoore {

    private String state;
    private boolean conexo;
    private String request;
    private ArrayList<StateMoore> finStates;

    public StateMoore(String state, String request){
        this.state = state;
        conexo = false;
        this.request = request;
        finStates = new ArrayList<>();
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

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public ArrayList<StateMoore> getFinStates() {
        return finStates;
    }

    public void setFinStates(ArrayList<StateMoore> finStates) {
        this.finStates = finStates;
    }
}
