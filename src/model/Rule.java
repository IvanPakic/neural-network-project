package model;

import java.util.ArrayList;

public class Rule {

    private ArrayList<Fact> conditions;
    private Fact conclusion;
    
    
    public Rule(ArrayList<Fact> conditions, Fact conclusion) {
        this.conditions = conditions;
        this.conclusion = conclusion;
    }
    public ArrayList<Fact> getConditions() {
        return conditions;
    }
    public void setConditions(ArrayList<Fact> conditions) {
        this.conditions = conditions;
    }
    public Fact getConclusion() {
        return conclusion;
    }
    public void setConclusion(Fact conclusion) {
        this.conclusion = conclusion;
    }

    
}
