/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Chooser;
import org.springframework.samples.petclinic.model.SumVotes;
import org.springframework.samples.petclinic.model.Vote;
import org.springframework.samples.petclinic.model.VoteByDay;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class ResultController {

    private final ClinicService clinicService;


    @Autowired
    public ResultController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    
    /**
     * 
     * - Check all the votes on the table user selection, and return a list with the SumVotes 
     * Class to display
     *
     */
	  @RequestMapping(value = "/resultado", method = RequestMethod.GET)
	  public String processFindForm( Map<String, Object> model) 
      {
		  	ArrayList<SumVotes> oKeepWinnersList = checkVoteResults();
    	
			model.put("selections", oKeepWinnersList);
			return "chooser/resultDetails";
      }
	
	  /**
	     * 
	     * Complex algorith to find the winners 
	     * 
	     */
	private  ArrayList<SumVotes> checkVoteResults()
	{
		//get votes
        Collection<Chooser> oCollection = this.clinicService.findChoices();
        
        Iterator<Chooser> i = oCollection.iterator();
                
        LocalDate dCurrentDate = new LocalDate();//DIA D HJ
        int nDiaSemana = dCurrentDate.getDayOfWeek();
                
        LocalDate dFirstDayWeek = dCurrentDate.minusDays(nDiaSemana);        
        LocalDate dFinalWeekDay = dFirstDayWeek.plusDays(6);
        
        //the week is starting on monday = 1
        //sunday is 7 = friday is 5
        
        ArrayList<VoteByDay> oKeepWeekDaysList = new ArrayList<VoteByDay>();
        ArrayList<Vote> oInnerKeepWeekDaysList = new ArrayList<Vote>(); 
        
        //run the list to keep the week day on one collection
        Chooser oneOptionChoosed = new Chooser();
        while(i.hasNext())
        {
        	oneOptionChoosed = i.next();                
        	        	        
        	//remove days outside of the week
        	if(dFinalWeekDay.getDayOfYear() >= oneOptionChoosed.getPickedDate().getDayOfYear() &&
        			oneOptionChoosed.getPickedDate().getDayOfYear() >= dFirstDayWeek.getDayOfYear()
        			)
        	{
        		Vote oVote = new Vote();
        		oVote.setRestaurantName(oneOptionChoosed.getRestaurant().getMainName());
        		oVote.setWeekDay(oneOptionChoosed.getPickedDate().getDayOfWeek());
        		
        		oInnerKeepWeekDaysList.add(oVote);
        		
        		//print which data is in the week
        		System.out.println("restaurant " + oneOptionChoosed.getRestaurant().getMainName() + " Data: " + oneOptionChoosed.getPickedDate().toString() +
            			" user : " + oneOptionChoosed.getOwner().getLastName());	
        	}
        	//After today is not going to show
        	//print database
//        	System.out.println("restaurant " + oneOptionChoosed.getRestaurant().getMainName() + " Data: " + oneOptionChoosed.getPickedDate().toString() +
//        			" user : " + oneOptionChoosed.getOwner().getLastName());
        }

        boolean bSHouldInclude = false;
        
        //for to weekdays
        for (int ind = 1; ind <= 7; ind++)
        {
        	VoteByDay oVoteByDay = new VoteByDay();
        	
    		//runs over the total votes
    		for(Vote oCurrentVote : oInnerKeepWeekDaysList)
        	{
    			if(ind == oCurrentVote.getWeekDay())
    			{
    				bSHouldInclude = true;
    				if( oVoteByDay.getComplexVote().containsKey(oCurrentVote.getRestaurantName()))
    				{
    					int nCurrentValue = oVoteByDay.getComplexVote().get(oCurrentVote.getRestaurantName());
    					nCurrentValue++;
    					oVoteByDay.getComplexVote().put(oCurrentVote.getRestaurantName(), nCurrentValue);
    					oVoteByDay.setWeekDay(ind);
    				}
    				else
    				{
    					oVoteByDay.getComplexVote().put(oCurrentVote.getRestaurantName(), 1);
    					oVoteByDay.setWeekDay(ind);
    				}
    			}//dont need here, it will pass again for other day
        	}
    		if( bSHouldInclude)
    		{
    			oKeepWeekDaysList.add(oVoteByDay);
    			bSHouldInclude = false;
    		}
    	}
        
        ArrayList<SumVotes> oKeepWinnersList = new ArrayList<SumVotes>();
        
        boolean isDraw = false;
        
        //now prepare to show result
        //for (int indWeek = 1; indWeek < 8; indWeek++)
    	for(VoteByDay oCheckWinnerVote : oKeepWeekDaysList)	
        {
        	Integer nCountVotes = 0;
        	String sPossibleWinner = "";
        	String secondOptionRestaurant = "";
        	for(String key: oCheckWinnerVote.getComplexVote().keySet())
        	{
        		//compare values
        		int nTempValue = oCheckWinnerVote.getComplexVote().get(key);
        		if(nTempValue > nCountVotes)
        		{
        			secondOptionRestaurant = sPossibleWinner; //used on repeated cases
        			sPossibleWinner = key.toString();
        			nCountVotes = nTempValue;
        		}
    			else if(nTempValue == nCountVotes)
        		{
        			isDraw = true;
        			sPossibleWinner += " & " + key.toString();
        		}
    			else
    			{
    				secondOptionRestaurant = key.toString(); //used on repeated cases
    			}
        	}
        	SumVotes oWinner = new SumVotes();
        	if(isDraw)
        	{
        		oWinner.setRestaurantName("Empate entre - " + sPossibleWinner);
        		isDraw = false;
        	}
        	else
        	{
        		//test to not repeat restaurant
    			for(SumVotes oCheckDuplicateWinnerVote : oKeepWinnersList)		
                {
    				if(sPossibleWinner.equals(oCheckDuplicateWinnerVote.getRestaurantName()))
    				{
    					System.out.println("Not allowed repeated restaurant on the week");
    					sPossibleWinner = secondOptionRestaurant;
    					if(sPossibleWinner.length() < 1)
    					{
    						sPossibleWinner = "Restaurante repetido - dados insuficientes para indicar outro";
    					}
    				}
                }

        		oWinner.setRestaurantName(sPossibleWinner);
        	}
        	
        	oWinner.setRestaurantName(sPossibleWinner);
        	oWinner.setWeekDay(oCheckWinnerVote.getWeekDay());
        	oWinner.setTotalVotes(nCountVotes);
        	oKeepWinnersList.add(oWinner);
        	
//        	if(isDraw)
//        		System.out.println("possible empate");
//        	System.out.println("The winner is: " + sPossibleWinner + " With the following votes: " + nCountVotes.toString() +
//        			" on the day " + oCheckWinnerVote.getWeekDay());
        	
    	}
    	
    	//remove today data before 11am
    	for(SumVotes oneWinnerVoteToDelete : oKeepWinnersList)	
        {
    		if(nDiaSemana == oneWinnerVoteToDelete.getWeekDay() && new DateTime().getHourOfDay() < 11)
    		{
    			oKeepWinnersList.remove(oneWinnerVoteToDelete);
    			break;
    		}
        }
    	
    	Integer nStartingWeekDay = dFirstDayWeek.getDayOfWeek();
    	
    	//adjust week day to display
    	for(SumVotes oneWinnerVote : oKeepWinnersList)	
        {
    		int nAdjustDay = 0;
    		if(nStartingWeekDay > oneWinnerVote.getWeekDay())
    		{
    			nAdjustDay = nStartingWeekDay - oneWinnerVote.getWeekDay() - 1;
    			oneWinnerVote.setLunchTime(dFinalWeekDay.minusDays(nAdjustDay));
    		}
    		else if(nStartingWeekDay == oneWinnerVote.getWeekDay())
    		{
    			oneWinnerVote.setLunchTime(dFirstDayWeek);
    		}
    		else
    		{
    			nAdjustDay = nStartingWeekDay + oneWinnerVote.getWeekDay();
    			oneWinnerVote.setLunchTime(dFirstDayWeek.plusDays(nAdjustDay));
    		}
    		
    		
        }
    	
    	return oKeepWinnersList;
	}

}
