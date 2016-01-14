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
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.model.Visit;
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
        Collection<Chooser> oCollection = this.clinicService.findChoices();
        
        Iterator<Chooser> i = oCollection.iterator();
                
        LocalDate oCurrentDate = new LocalDate();
        
        Map<String,Integer> mapResult = new HashMap<String,Integer>();
        
        Chooser onlyOne = new Chooser();
        while(i.hasNext())
        {
        	onlyOne = i.next();                
        	
        	if(oCurrentDate.dayOfYear().equals(onlyOne.getPickedDate().dayOfYear()))
        	{
        		if(mapResult.containsKey(onlyOne.getRestaurant().getMainName()))
        		{
        			int nCurrentValue = mapResult.get(onlyOne.getRestaurant().getMainName());
        			nCurrentValue++;
        			mapResult.put(onlyOne.getRestaurant().getMainName(), nCurrentValue);
        		}
        	}
        	else
        	{
        		mapResult.put(onlyOne.getRestaurant().getMainName(), 1);
        	}
        	
        	//System.out.println(onlyOne.getDescription());	
        }
        
        Integer nCountVotes = 0;
        String sPossibleWinner = "";
        for(String key: mapResult.keySet())
        {
        	int nTempValue = mapResult.get(key);
        	if(nTempValue > nCountVotes)
        	{
        		sPossibleWinner = key.toString();
        		nCountVotes = nTempValue;
        	}
        	//System.out.println(key + " - " + vehicles.get(key));
        }                    	        		                
        
        System.out.println("The winner is: " + sPossibleWinner + " With the following votes: " + nCountVotes.toString());
        
        return onlyOne;
    }

}
