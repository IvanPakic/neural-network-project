package controller;

import java.util.ArrayList;

import model.Fact;
import model.Rule;

public class ForwardChaining {

	private ArrayList<Fact> facts;
	private ArrayList<Rule> rules;
	private Fact goal;
	private ArrayList<Rule> conclusions;
	
	public ForwardChaining(ArrayList<Rule> rules) {
		this.rules = rules;	
		conclusions = new ArrayList<Rule>();
	}
	
	public void setRules(ArrayList<Rule> rules) {
		conclusions.clear();
		this.rules = rules;
	}
	
	public ArrayList<Rule> getConclusions() {
		return conclusions;
	}
	
	public void setGoal(Fact goal) {
		this.goal = goal;
	}
	public void setFacts(ArrayList<Fact> facts) {
		this.facts = facts;
	}
	
	//vraca nul ako nema redudanse u suprotnom vraca nove conditione
	private boolean checkRules(ArrayList<Fact> conditions, Fact fact){
		
		if(conditions.isEmpty()) // ako smo zakljucili vraca true
			return false;
		
		for(int i = 0; i < conditions.size() ; i++) { // redukuje conditione
			if(fact.getFactID() == conditions.get(i).getFactID()) {
				conditions.remove(i);
			}
		}
		
		if(conditions.isEmpty()) {
			// ako smo zakljucili vraca true
			
			return true;
		}
		
		return false; // false ako nismo zakljucili	
	
	}
	
	private boolean sideChaining(Fact fact) { // pomocna funkcija za exe
		
		if(fact.getFactID()==goal.getFactID()) {
			return true;
		}
		
		int index = 0;
		while(index < rules.size()) {
			if(checkRules(rules.get(index).getConditions(), fact)) {
				// zakljucili
				conclusions.add(rules.get(index));
		
				if( sideChaining(rules.get(index).getConclusion()) ) {
					
					return true;
				}
			}
			
			index++;
		}
			
		return false;	
	}

	public boolean executeChaining(Fact golTarget) {
		setGoal(golTarget);
		for(Fact fact: facts) {
			if(sideChaining(fact))return true;
		}
		
		return false;
	}
	
}
