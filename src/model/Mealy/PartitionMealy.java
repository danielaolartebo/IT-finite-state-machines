package model.Mealy;

import java.util.ArrayList;

public class PartitionMealy {

    private ArrayList<ArrayList<StateMealy>> hsPartition;

    public PartitionMealy(){
        hsPartition = new ArrayList<>();
    }

    public ArrayList<ArrayList<StateMealy>> getHsPartition() {
        return hsPartition;
    }

    public void setHsPartition(ArrayList<ArrayList<StateMealy>> hsPartition) {
        this.hsPartition = hsPartition;
    }
}
