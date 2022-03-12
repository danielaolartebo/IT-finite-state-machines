package model;
import java.util.*;
import data_structures.*;

public class Machine<Q extends Comparable<Q>,S,R> extends GraphList<Q> {
    private Q startState;
    private HashSet<S> stimulusState;
    private HashSet<R> responseState;
    private HashMap<Q, HashMap<S, Q>> state;
}
