package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Teams;

public class Matches {

	public File readFile(String docName) {
		return new File(docName + ".txt");
	}
	
	public void writeTeams(String docName) {
		Map<String, Teams> teamsMap = new HashMap<>();
		try {
			// Reading the file
			Scanner obj = new Scanner(readFile(docName));
			
			// Creating local and visitor objects
			Teams tLocal = new Teams();
			Teams tVisitor = new Teams();
			
			while(obj.hasNextLine()) {
				// Split line by commas to get Local and Visitor
				String[] ln = obj.nextLine().split(",");
				
				// Setting local team
				tLocal = setTeams(ln[0].trim());
				
				// Setting visitor team
				tVisitor = setTeams(ln[1].trim());
				
				// Setting scores for both teams
				setScores(tLocal, tVisitor);
				
				// Putting into the map and adding the existent score
				putToList(teamsMap, tLocal);
				putToList(teamsMap, tVisitor);
			}
		} catch (IOException e) {
			System.out.println("File not found, please verify!");
			return;
		}
		
		List<Teams> teamsList = orderingList(teamsMap);
		
		System.out.println(teamsList.toString());
	}
	
	/**
	 * 
	 * @param ln indicates the line of the file which contains the name of the team and the points on the match
	 * @return the Teams object formed
	 */
	public Teams setTeams(String ln) {
		Teams team = new Teams();
		String[] str = ln.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
		team.setName(str[0].trim());
		team.setPoints(Integer.parseInt(str[1]));
		return team;
	}
	
	/**
	 * 
	 * Validate the existence of the Teams object inside the map and add the score obtained on the match
	 * 
	 * @param teamsMap the HashMap where the teams are stored
	 * @param team the instance object with information of the Teams
	 */
	public void putToList(Map<String, Teams> teamsMap, Teams team) {
		if(team != null) {
			if(!teamsMap.isEmpty()) {
				Teams lstTeam = teamsMap.get(team.getName());
				if(lstTeam != null) {
					team.setScore(team.getScore() + lstTeam.getScore());
				}
			}
			
			teamsMap.put(team.getName(), team);
		}
	}
	
	/**
	 * This method calculate the scores earn by each team
	 * @param tLocal the local team 
	 * @param tVisitor the visitor team
	 */
	public void setScores(Teams tLocal, Teams tVisitor) {
		// Calculating score by the results of the matches if local wins, received 3 points
		if(tLocal.getPoints() > tVisitor.getPoints()) {
			tLocal.setScore(3);
		// if there's a draw, both teams earn 1 point
		} else if (tLocal.getPoints() == tVisitor.getPoints()) {
			tLocal.setScore(1);
			tVisitor.setScore(1);
		// if none of above conditions met, visitor earns 3 points because it wins
		} else {
			tVisitor.setScore(3);
		}
	}
	
	public List<Teams> orderingList(Map<String, Teams> teamsMap) {
		// Setting map to a LinkedList to proceed with ordering
		List<Teams> teamsList = new ArrayList<>(teamsMap.values());
		
		//Ordering linkedList by score in descending order, then ordering by alphabet
		Collections.sort(teamsList, Comparator.comparing(Teams::getScore).reversed().thenComparing(Teams::getName));
		
		return teamsList;
	}
}
