package controller;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import model.DataBaseController;
import model.Fact;
import model.Rule;

public class MainController {
	private boolean b;
	private ArrayList<Fact> facts;
	private ArrayList<Fact> fact;
	private ArrayList<Fact> conclusions;
	private ArrayList<Rule> rules;
	private Fact goal;
	private DataBaseController dbc;
	private ForwardChaining fc;
	private BackwardChaining bc;
	
	public MainController() {
		b = false;
		facts = new ArrayList<Fact>();
		conclusions = new ArrayList<Fact>();
		goal = new Fact();
		dbc = new DataBaseController();
		fc = new ForwardChaining(dbc.getRules());
		bc = new BackwardChaining(dbc.getRules());
		
	}
	
	public void setAll() {
		
	}
	
	public boolean chaning(Fact g, boolean fOrB, ArrayList<Fact> premise){
		b=fOrB;
		if(fOrB) {
			return forwardChaning(g, premise);
		}else {
			return backwardChaning(g, premise);
		}
		
	}
	
	public boolean forwardChaning(Fact g, ArrayList<Fact> premise) {
		fc.setRules(dbc.getRules());
		fc.setFacts(premise);
		return fc.executeChaining(g);
	}
	
	public boolean backwardChaning(Fact g, ArrayList<Fact> premise) {
		bc.setFacts(premise);
		bc.setRules(dbc.getRules());
		return bc.execute(g);
	}
	
	public ObservableList<Fact> getGreenController(){
		return dbc.getGreen();
	}
	
	public ObservableList<Fact> getPurpleController(){
		return dbc.getPurple();
	}
	
	public String conclusionToString(Rule rule) {
		String s = "";
		for(int i = 0; i<rule.getConditions().size(); i++) {
			if(i!=0) 
				s+=" + ";
			s+=rule.getConditions().get(i).getFactDescription();
		}
		s+=" = " + rule.getConclusion().getFactDescription() + "\n";
		return s;
	}
	
	public String getMyConclusion() {
		ArrayList<Rule> rules = fc.getConclusions();
		if(b) {
			rules = fc.getConclusions();
		 }else {
			 rules = bc.getConclusions();
		 }

		String string = "";
		for(Rule rule : rules) {
				string+= conclusionToString(rule);		
			
		}
		
		return string;
	}
	
	
}
