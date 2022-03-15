package model.Mealy;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

public class StateMealy {

    //Atributes

    private String state;
    private boolean conexo;

    //Constructor

    public StateMealy(String state){
        this.state = state;
        conexo = false;
    }

    //Getters and setters

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
