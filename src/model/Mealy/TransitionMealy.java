package model.Mealy;

public class TransitionMealy {

    private StateMealy initialState;
    private String estimulo;
    private String request;
    private StateMealy finalState;

    public TransitionMealy(StateMealy initialState, String estimulo, String request, StateMealy finalState){
        this.initialState = initialState;
        this.estimulo = estimulo;
        this.request = request;
        this.finalState = finalState;
    }

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
}