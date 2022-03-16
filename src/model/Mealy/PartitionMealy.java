package model.Mealy;

import java.util.ArrayList;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

public class PartitionMealy {

    //Arraylists

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
