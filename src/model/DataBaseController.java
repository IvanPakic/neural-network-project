package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataBaseController {
	private ArrayList<Rule> rules;
	private ArrayList<Fact> allFacts;

	public DataBaseController() {
		rules = new ArrayList<Rule>();
		allFacts = new ArrayList<Fact>();
		readData();

	}

	private Fact getFactByID(int x) {
		for (Fact fact : allFacts)
			if (x == fact.getFactID())
				return fact;
		return null;
	}

	private Fact parseFact(String stringToParse) {

		if (stringToParse.equals(""))
			return null;
		boolean bool = false;
		int id;
		String factString;
		String[] listOfStrings = stringToParse.split("/");
		id = Integer.parseInt(listOfStrings[0]);
		factString = listOfStrings[1];
		int value = Integer.parseInt(listOfStrings[2]);

		Fact fact = new Fact(bool, factString, id, value);

		return fact;
	}

	private void readFacts() {
		try {
			// the file to be opened for reading
			FileInputStream fis = new FileInputStream("FactsBase.txt");
			Scanner sc = new Scanner(fis); // file to be scanned
			// returns true if there is another line to read
			while (sc.hasNextLine()) {
				Fact newFact = parseFact(sc.nextLine());
				if (newFact == null)
					continue;
				allFacts.add(newFact);
			}
			sc.close(); // closes the scanner
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Rule parseRule(String stringToParse) {

		if (stringToParse.equals(""))
			return null;

		String[] listOfStrings = stringToParse.split("=");
		ArrayList<Fact> conditions = new ArrayList<Fact>();

		int index = Integer.parseInt(listOfStrings[1]);
		Fact conclusion = getFactByID(index);

		String[] listOfStrings2 = listOfStrings[0].split("@");
		for (String str : listOfStrings2) {
			int i = Integer.parseInt(str);

			Fact cond = getFactByID(i);
			if (cond != null)
				conditions.add(getFactByID(i));
		}

		Rule rule = new Rule(conditions, conclusion);
		return rule;

	}

	private void readRules() {
		try {
			// the file to be opened for reading
			FileInputStream fis = new FileInputStream("RulesBase.txt");
			Scanner sc = new Scanner(fis); // file to be scanned
			// returns true if there is another line to read
			while (sc.hasNextLine()) {
				Rule newRule = parseRule(sc.nextLine());
				if (newRule == null)
					continue;
				rules.add(newRule);
			}
			sc.close(); // closes the scanner
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readData() {
		readFacts();
		readRules();
	}

	public ArrayList<Fact> getFacts() {
		return (ArrayList<Fact>) allFacts.clone();

	}

	public ArrayList<Rule> getRules() {
		rules.clear();
		readRules();
		return (ArrayList<Rule>) rules.clone();

	}
	
	public ObservableList<Fact> getGreen(){
		ObservableList<Fact> obGreenList = FXCollections.observableArrayList();
		for(Fact fact:allFacts) 
			if(fact.getFactID()<=17 && fact.getFactID() >= 1) 
				obGreenList.add(fact);
		
		
		return obGreenList;
	}

	public ObservableList<Fact> getPurple(){
		ObservableList<Fact> obPurpleList = FXCollections.observableArrayList();
		for(Fact fact:allFacts) 
			if(fact.getFactID()>17 || fact.getFactID() < 1) 
				obPurpleList.add(fact);
		
		
		return obPurpleList;
	}
	
}