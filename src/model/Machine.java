package model;

public class Machine {

    private Properties properties;

    public Machine(){
        properties = new Properties();
    }

    public Properties getProperties(){
        return properties;
    }

    public void addState(int numStates){
        for (int i = 0; i < numStates; i++){
            String newState = "q"+i;
            properties.getStates().add(newState);
        }
    }

    public void addAlphabet(String alphabet){
        String[] newAlphabet = alphabet.split(" ");
        for (String s : newAlphabet) {
            properties.getAlphabet().add(s);
        }
    }
}
