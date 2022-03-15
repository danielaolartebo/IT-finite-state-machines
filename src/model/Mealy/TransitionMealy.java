package model.Mealy;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

public class TransitionMealy {

    //Atributes

    private StateMealy initialState;
    private String estimulo;
    private String request;
    private StateMealy finalState;

    //Constructor

    public TransitionMealy(StateMealy initialState, String estimulo, String request, StateMealy finalState){
        this.initialState = initialState;
        this.estimulo = estimulo;
        this.request = request;
        this.finalState = finalState;
    }

    //Getters and setters

    public StateMealy getInitialState() {
        return initialState;
    }

    public void setInitialState(StateMealy initialState) {
        this.initialState = initialState;
    }

    public String getEstimulo() {
        return estimulo;
    }

    public void setEstimulo(String estimulo) {
        this.estimulo = estimulo;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public StateMealy getFinalState() {
        return finalState;
    }

    public void setFinalState(StateMealy finalState) {
        this.finalState = finalState;
    }

    public String getNameI(){
        return initialState.getState();
    }

    public String getNameF(){
        return finalState.getState();
    }
}