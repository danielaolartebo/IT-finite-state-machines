package model.Moore;

public class TransitionMoore {

    private StateMoore initialState;
    private String estimulo;
    private StateMoore finalState;

    public TransitionMoore(StateMoore initialState, String estimulo, StateMoore finalState){
        this.initialState = initialState;
        this.estimulo = estimulo;
        this.finalState = finalState;
    }

    public StateMoore getInitialState() {
        return initialState;
    }

    public void setInitialState(StateMoore initialState) {
        this.initialState = initialState;
    }

    public String getEstimulo() {
        return estimulo;
    }

    public void setEstimulo(String estimulo) {
        this.estimulo = estimulo;
    }

    public StateMoore getFinalState() {
        return finalState;
    }

    public void setFinalState(StateMoore finalState) {
        this.finalState = finalState;
    }

    public String getRequestMooreState(){
        return initialState.getRequest();
    }

    public String getNameI(){
        return initialState.getState();
    }

    public String getNameF(){
        return finalState.getState();
    }
}
