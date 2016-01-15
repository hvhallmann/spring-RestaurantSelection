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
package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Simple JavaBean domain object representing a choose option.
 *
 * @author Henrique Hallmann
 */
@Entity
@Table(name = "userSelection")
public class Chooser extends BaseEntity {

    /**
     * Holds value of property date.
     */
    @Column(name = "pick_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate pickedDate;

    /**
     * Holds value of property description.
     */    
    @Column(name = "description")
    private String description;

    /**
     * Holds value of property owner.
     */
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    
    /**
     * Holds value of property restaurant.
     */
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    /**
     * Creates a new instance of Visit for the current date
     */
    public Chooser() {
        this.pickedDate = new LocalDate();
    }
    /**
     * Getter for property date.
     *
     * @return Value of property date.
     */
    public LocalDate getPickedDate() {
        return this.pickedDate;
    }
    /**
     * Setter for property date.
     *
     * @param date New value of property date.
     */
    public void setPickedDate(LocalDate pickedDate) {
        this.pickedDate = pickedDate;
    }

    /**
     * Getter for property description.
     *
     * @return Value of property description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for property description.
     *
     * @param description New value of property description.
     */
    public void setDescription(String description) {
        this.description = description;
    }


	public Owner getOwner() {
		return owner;
	}


	public void setOwner(Owner owner) {
		this.owner = owner;
	}


	public Restaurant getRestaurant() {
		return restaurant;
	}


	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

    

}
