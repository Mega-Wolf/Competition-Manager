package other;

import static org.junit.Assert.*;
import org.junit.Test;

import other.Team;

public class TeamTest {


	@Test
	public void AnyTestSchool() {
		Team AnyTeam = new Team("","ANY");
		assertFalse(AnyTeam.isValid());
	}
	
	@Test
	public void OtherTestSchool() {
		Team OtherSchool = new Team("Other School","");
		assertFalse(OtherSchool.isValid());
	}
	
	@Test
	public void SBRTestSchool() {
		Team SBRTeam = new Team ("Schönbein-Realschule","SBR");		
		assertEquals("Schönbein-Realschule", SBRTeam.getSchool());
		assertEquals("SBR", SBRTeam.getAbbreviation());
	}
	
	@Test
	public void testGetSchool() {
		Team testTeam = new Team("Random Schule","RS");
		String expected = "Random Schule";
		assertEquals(expected, testTeam.getSchool());		
	}
	
	@Test
	public void testGetAbbreviation() {		
		Team testTeam = new Team("Random Schule","RS");
		String expected = "RS";
		assertEquals(expected, testTeam.getAbbreviation());
	}
	
	@Test
	public void sameAbbrTest() {
		Team myTeam = new Team("myTeam","ABC");
		Team yourTeam = new Team("yourTeam","ABC");
		assertEquals(myTeam.getAbbreviation(),yourTeam.getAbbreviation());
	}
	
	@Test
	public void sameSchoolTest() {
		Team myTeam = new Team("myTeam","ABC");
		Team yourTeam = new Team("myTeam","DEF");
		assertEquals(myTeam.getSchool(),yourTeam.getSchool());
	}

}
