package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.Matches;


public class ApplicationMain {
	public static void main(String[] args) {
		System.out.println("Welcome to the League!");
		System.out.println("----------------------");
		System.out.println("Please type the name of the file that contains the matches information:");
		Matches matches = new Matches();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			// Reading the name of the file
			String docName = reader.readLine();
			matches.writeTeams(docName);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
