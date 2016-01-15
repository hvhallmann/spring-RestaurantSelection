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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Chooser;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Restaurant;
import org.springframework.samples.petclinic.model.SumVotes;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.model.Vote;
import org.springframework.samples.petclinic.model.VoteByDay;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

//    @RequestMapping(value = {"/resultado.html"})
//    public String showResultList(Map<String, Object> model) 
//    {
//        Chooser vets = new Vets();
//        vets.getVetList().addAll(this.clinicService.findVets());
//        model.put("vets", vets);
//        
//        return "vets/vetList";
//    }
    
    /**
     * Custom handler for displaying an owner.
     *
     * @param ownerId the ID of the owner to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/resultado.html")
    public ModelAndView showResult() 
    {
        ModelAndView mav = new ModelAndView("chooser/resultDetails");
        
//        Object test = this.clinicService.findChoices();
//        
//        mav.addObject(test);
        
        return mav;
    }
    
    
    /**
     * 
     * RODAR UMA FUNCAO AKI QUE VAI VERFICAR SE JAH FOI DEFINIDO RESTAURANTE
     * NAO PERMITIR REPETIR RESTAURANTE NA SEMANA
     * TODOS TERIAM Q VOTAR PARA VALIDAR :??
     * -->ÉNSANDO MELHOR
     * ACHO QUE O PROGRAMA VAI VERIFICAR OS ANTERIORES NA SEMANA E VAI VALIDAR CASO JAH TENHA GANHADOR
     * 
     * Called before each and every @RequestMapping annotated method.
     * 2 goals:
     * - Make sure we always have fresh data
     * - Since we do not use the session scope, make sure that Pet object always has an id
     * (Even though id is not part of the form fields)
     *
     * @param petId
     * @return Pet
     */
    @ModelAttribute("oneChoice")
    public Chooser loadOneChoice() 
    {
    	//get votes
        Collection<Chooser> oCollection = this.clinicService.findChoices();
        
        Iterator<Chooser> i = oCollection.iterator();
                
        LocalDate dCurrentDate = new LocalDate();//DIA D HJ
        int nDiaSemana = dCurrentDate.getDayOfWeek();
                
        LocalDate dFirstDayWeek = dCurrentDate.minusDays(nDiaSemana);        
        LocalDate dFinalWeekDay = dFirstDayWeek.plusDays(6);
        
        //the week is starting on monday
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
        		
        		//print which data is in
        		System.out.println("restaurant " + oneOptionChoosed.getRestaurant().getMainName() + " Data: " + oneOptionChoosed.getPickedDate().toString() +
            			" user : " + oneOptionChoosed.getOwner().getLastName());	//AKI IMPRIMO TODA A BASE
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
    						sPossibleWinner = "Restaurante repetido - dados insuficientes";
    					}
    				}
                }

        		oWinner.setRestaurantName(sPossibleWinner);
        	}
        	
        	oWinner.setRestaurantName(sPossibleWinner);
        	oWinner.setWeekDay(oCheckWinnerVote.getWeekDay());
        	oWinner.setTotalVotes(nCountVotes);
        	oKeepWinnersList.add(oWinner);
        	
        	if(isDraw)
        		System.out.println("possible empate");
        	System.out.println("The winner is: " + sPossibleWinner + " With the following votes: " + nCountVotes.toString() +
        			" on the day " + oCheckWinnerVote.getWeekDay());
        	
    	}
        
        
        Restaurant oWinnerRestaurant = new Restaurant();
        //oWinnerRestaurant.setMainName(sPossibleWinner);
        
        oneOptionChoosed.setRestaurant(oWinnerRestaurant);
        //onlyOne.setPickedDate(pickedDate); // apos arrumar as datas pode trocas
        //oneOptionChoosed.setDescription(nCountVotes.toString());
        
        return oneOptionChoosed;
    }

}
