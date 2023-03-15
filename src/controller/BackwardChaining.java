package controller;

import java.util.ArrayList;

import model.Fact;
import model.Rule;

public class BackwardChaining {

    
    private ArrayList<Fact> facts;
    private ArrayList<Rule> conclusions;
    private ArrayList<Rule> rules;

    
    public BackwardChaining(ArrayList<Rule> rules) {
        this.rules = rules;
        facts = new ArrayList<Fact>();
        conclusions = new ArrayList<Rule>();
    }
    
    public void setFacts(ArrayList<Fact> facts) {
		this.facts = facts;
	}
    
    public void setRules(ArrayList<Rule> rules) {
		conclusions.clear();
    	this.rules = rules;
	}
    
    public void setConclusions(ArrayList<Rule> conclusions) {
    	this.conclusions = conclusions;
	}
    
    public ArrayList<Rule> getConclusions() {
		return conclusions;
	}
    
    private boolean goThroughFacts(Fact fact) {

        for(Fact comparator : facts) {
            if(fact.getFactID() == comparator.getFactID())
                return true;
        }
        return false;
    }

    private ArrayList<Rule> goThroughRules(Fact fact) {

        ArrayList<Rule> rulesToCheck = new ArrayList<Rule>();

        for(Rule comparator : rules) {
            if(comparator.getConclusion().getFactID() == fact.getFactID())
                rulesToCheck.add(comparator);
        }
 
        return rulesToCheck;
    }

    private boolean checkForConditions(ArrayList<Fact> goalConditions) {
        for(Fact condition: goalConditions) {
            if(!execute(condition))return false;
        }
        return true;
    }

    private boolean checkForConclusions(ArrayList<Rule> goalRules) {

        for(Rule conclusion: goalRules) {
            if(checkForConditions(conclusion.getConditions())) {
            	conclusions.add(conclusion);
                return true;
            }

        }

        return false;
    }

    public boolean execute(Fact goal) {
   
        if(goThroughFacts(goal)) {
            return true;
        }


        ArrayList<Rule> goalRules = goThroughRules(goal);

        if( checkForConclusions(goalRules)) {
            return true;
        }

        return false;
    }
   
    
}