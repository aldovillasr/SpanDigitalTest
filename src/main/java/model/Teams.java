package model;

public class Teams {

	String name;
	int points;
	int score;
	
	public Teams() {}
	
	public Teams(String name, int points) {
		this.name = name;
		this.points = points;
	}
	
	public Teams(String name, int points, int score) {
		this.name = name;
		this.points = points;
		this.score = score;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return this.score;
	}
	
	@Override
	public String toString() {
		return "{Name: " + this.name + ", Score: " + this.score + "}";
	}
}
