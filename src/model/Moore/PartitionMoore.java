package model.Moore;

import java.util.ArrayList;

public class PartitionMoore {

    private ArrayList<ArrayList<StateMoore>> mooreP;

    public PartitionMoore(){
        mooreP = new ArrayList<>();
    }

    public ArrayList<ArrayList<StateMoore>> getMooreP() {
        return mooreP;
    }

    public void setMooreP(ArrayList<ArrayList<StateMoore>> mooreP) {
        this.mooreP = mooreP;
    }
}
