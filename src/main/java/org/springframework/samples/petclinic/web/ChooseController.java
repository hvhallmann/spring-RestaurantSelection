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

import java.util.Collection;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Chooser;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Restaurant;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller used to showcase what happens when an exception is thrown
 *
 * @author Michael Isvy
 *         <p/>
 *         Also see how the bean of type 'SimpleMappingExceptionResolver' has been declared inside
 *         /WEB-INF/mvc-core-config.xml
 */
@Controller
public class ChooseController {


    private final ClinicService clinicService;


    @Autowired
    public ChooseController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }
    
    @InitBinder("chooser")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    @InitBinder("chooser")
    public void initChoiceBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new ChooserValidator());
    }
    
    @ModelAttribute("employees")
    public Collection<Owner> populateEmployees() {
        return this.clinicService.findOwners();
    }
    
    @ModelAttribute("restaurants")
    public Collection<Restaurant> populateRestaurants() {
        return this.clinicService.findRestaurants();
    }

//    @RequestMapping(value = {"/escolher.html"})
//    public String showOptionsList(Map<String, Object> model) {
//    	
//    	Chooser chooser = new Chooser();        
//        model.put("chooser", chooser);
//    	
//        return "chooser/createOrUpdatePickedOption";
//    }
    
    @RequestMapping(value = "/escolher.html", method = RequestMethod.GET)
    public String initCreationFormToChoose(Chooser chooser, BindingResult result, ModelMap model ) {
    	
    	//chooser = new Chooser();        
        model.put("chooser", new Chooser());
    	
        return "chooser/createOrUpdatePickedOption";
    }

    
    private Owner findUniqueOwner(String sLastName)
    {
    	Collection<Owner> toInsert = this.clinicService.findOwnerByLastName(sLastName);
    	
    	Owner finalOwner = new Owner();
    	
    	Iterator<Owner> iter = toInsert.iterator();
    	while (iter.hasNext())
    	{
    		finalOwner = iter.next();
    	}
    	
    	return finalOwner;
    }
    
    private boolean checkForUniqueInsert(Chooser toInsert)
    {
    	boolean isNotUnique = false;
    	
    	Collection<Chooser> alreadyInserted = this.clinicService.findChoices();
    	
    	Chooser currentOwner = new Chooser();
    	
    	Iterator<Chooser> iter = alreadyInserted.iterator();
    	while (iter.hasNext())
    	{
    		currentOwner = iter.next();
    		
    		//same day
    		if(toInsert.getPickedDate().dayOfYear().equals(currentOwner.getPickedDate().dayOfYear()))
    		{
    			//same person voting
    			if(toInsert.getOwner().getLastName().equals(currentOwner.getOwner().getLastName()))
    			{
    				isNotUnique = true;
    			}
    		}
    	}
    	
    	return isNotUnique ;
    }
    
    @RequestMapping(value = "/escolher.html", method = RequestMethod.POST)
    public String processCreationForm(@Valid Chooser chooser, BindingResult result, ModelMap model) 
    {
    	if(chooser == null)
    		System.out.println("null!!!");
    	
    	if(chooser.getRestaurant() == null || chooser.getOwner() == null || chooser.getPickedDate() == null)
    	{
    		System.out.println("Problema com a data!!!");
    		result.rejectValue("owner.lastName", "required", "required");
    		return "chooser/createOrUpdatePickedOption";
    	}
    	if (result.hasErrors()) 
        {
            model.put("chooser", chooser);
            return "chooser/createOrUpdatePickedOption";
        } 
    	
    	System.out.println("Passou com rest " + chooser.getRestaurant().getMainName());
    	System.out.println("Passou com user " + chooser.getOwner().getLastName());
    	System.out.println("Passou com data " + chooser.getPickedDate());
    	
    	//Find User id    	    
    	Owner finalOwner = findUniqueOwner(chooser.getOwner().getLastName());    	
    	
    	chooser.setOwner(finalOwner);

    	//Find Restaurant    	
    	chooser.setRestaurant(this.clinicService.findRestaurantByName(chooser.getRestaurant().getMainName()));
    	
    	//Check one employee by day to vote
    	if (checkForUniqueInsert(chooser))
    	{
	    	System.out.println("usuario duplicado mesmo dia copm falhas!!!");
	    	result.rejectValue("owner.lastName", "duplicate", "usuario duplicado mesmo dia");
    		return "chooser/createOrUpdatePickedOption";
	    	//return "redirect:/escolher.html";
	        //result.rejectValue("name", "duplicate", "already exists");
	    }
    	
    	this.clinicService.saveUserChoice(chooser);
    	
    	System.out.println("Passou por aki");
    	
    	//arrumar esse redirecionamento!!
    	return "redirect:/";
    	//return "redirect:/escolher.html";
    }

    
    @RequestMapping("/ChooseVets.json")
    public
    @ResponseBody
    Vets showResourcesVetList() {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet objects
        // so it is simpler for JSon/Object mapping
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());
        return vets;
    }

}
