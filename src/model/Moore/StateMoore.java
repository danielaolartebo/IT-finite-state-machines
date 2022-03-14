package model.Moore;

public class StateMoore {

    private String state;
    private boolean conexo;
    private String request;

    public StateMoore(String state){
        this.state = state;
        conexo = false;
        request = "";
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
}
