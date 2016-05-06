package match;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import other.Round;
import other.Team;

public class MatchCollection {
	private Match[] matches = new Match[64];	//TODO
	
	public List<Match> getMatches(Team team) {
		List<Match> returnList = new ArrayList<Match>();
		for (Match m : matches) {
			if (m.getTeams()[0] == team || m.getTeams()[1] == team) {
				returnList.add(m);
			}
		}
		return returnList;
	}
	
	
	public List<Match> getMatches(Group group) {
		List<Match> returnList = new ArrayList<Match>();
		for (Match m : matches) {	
			if (m instanceof GroupMatch && ((GroupMatch) m).getGroup() == group) {
				returnList.add(m);
			}
		}
		return returnList;
	}
	
	public List<Match> getMatches(Round round) {
		List<Match> returnList = new ArrayList<Match>();
		for (Match m : matches) {	
			if (m instanceof RoundMatch && ((RoundMatch) m).getRound() == round) {
				returnList.add(m);
			}
		}
		return returnList;
	}
	

}