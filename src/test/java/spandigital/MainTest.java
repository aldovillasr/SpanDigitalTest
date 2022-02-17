package spandigital;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.Matches;
import model.Teams;

public class MainTest {
	
	Matches matches = new Matches();

	@Test
	public void testReadingFile() {
		Assert.assertTrue(matches.readFile("League Results").isFile());
		
	}
	
	@Test
	public void testFileNotFound() {
		Assert.assertFalse(matches.readFile("Lague Results").isFile());
	}
	
	@Test
	public void testLocalScoreCorrect() {
		Teams tLocal = new Teams("Lions", 3);
		Teams tVisitor = new Teams("Grouches", 4);
		
		matches.setScores(tLocal, tVisitor);
		
		Assert.assertEquals(0, tLocal.getScore());
	}
	
	@Test
	public void testVisitorScoreCorrect() {
		Teams tLocal = new Teams("Lions", 3);
		Teams tVisitor = new Teams("Grouches", 4);
		
		matches.setScores(tLocal, tVisitor);
		
		Assert.assertEquals(3, tVisitor.getScore());
	}
	
	@Test
	public void testSetTeamsCorrect() {
		Teams team = matches.setTeams("Football Club Awesome 1");
		Assert.assertEquals("Football Club Awesome", team.getName());
	}
	
	@Test
	public void testSetTeamsIncorrect() {
		Teams team = matches.setTeams("FC Awesome 1");
		Assert.assertNotEquals("Football Club Awesome", team.getName());
	}
	
	@Test
	public void testPutToListCorrect() {
		Teams teams1 = new Teams("Lions", 3, 3);
		Teams teams2 = new Teams("Grouches", 4, 1);
		Teams teams3 = new Teams("Tarantulas", 3, 3);
		Teams teams4 = new Teams("FC Awesome", 4, 0);
		Teams teams5 = new Teams("Grouches", 3, 1);
		Teams teams6 = new Teams("Grouches", 4, 3);
		Teams teams7 = new Teams("Tarantulas", 3, 3);
		Teams teams8 = new Teams("Grouches", 4, 1);
		
		Map<String, Teams> teamsMap = new HashMap<>();
		
		matches.putToList(teamsMap, teams1);
		matches.putToList(teamsMap, teams2);
		matches.putToList(teamsMap, teams3);
		matches.putToList(teamsMap, teams4);
		matches.putToList(teamsMap, teams5);
		matches.putToList(teamsMap, teams6);
		matches.putToList(teamsMap, teams7);
		matches.putToList(teamsMap, teams8);
		
		Assert.assertEquals(6, teamsMap.get("Grouches").getScore());
	}
	
	@Test
	public void testNullMap() {
		Map<String, Teams> teamsMap = new HashMap<>();
		matches.putToList(teamsMap, null);
		Assert.assertEquals(0, teamsMap.size());
	}

}
