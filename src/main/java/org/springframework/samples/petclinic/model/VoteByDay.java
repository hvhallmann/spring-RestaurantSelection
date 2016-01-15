package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VoteByDay 
{
	int weekDay;
	
    Map<String,Integer> complexVote = new HashMap<String,Integer>();
	

//	public void includeItem(SumVotes oVotes)
//	{
//		if(oVotes != null)
//		{
//			complexVote.add(oVotes);
//		}
//	}

	public int getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
	}

	public Map<String, Integer> getComplexVote() {
		return complexVote;
	}

	public void setComplexVote(Map<String, Integer> complexVote) {
		this.complexVote = complexVote;
	}
	
	
}
