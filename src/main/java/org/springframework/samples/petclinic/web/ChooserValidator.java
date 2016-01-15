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

import org.springframework.samples.petclinic.model.Chooser;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * <code>Validator</code> for <code>Pet</code> forms.
 * <p>
 * We're not using Bean Validation annotations here because it is easier to define such validation rule in Java.
 * </p>
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
public class ChooserValidator implements Validator {

    @Override
    public void validate(Object obj, Errors errors) {
        Chooser choice = (Chooser) obj;
        
        if (choice == null) 
        {
        	errors.rejectValue("owner.lastName", "required", "required");
        }
        
        if (choice.getOwner() == null) 
        {
        	errors.rejectValue("owner.lastName", "required", "required");
        }
        
        
        String name = choice.getOwner().getLastName();
        // name validation
        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("owner.lastName", "required", "required");
        }

        // type validation
        if (choice.isNew() && choice.getRestaurant() == null) {
            errors.rejectValue("restaurant.mainName", "required", "required");
        }

        // birth date validation
        if (choice.getPickedDate() == null) {
            errors.rejectValue("pickedDate", "required", "required");
        }
    }

    /**
     * This Validator validates *just* Pet instances
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Chooser.class.isAssignableFrom(clazz);
    }


}
