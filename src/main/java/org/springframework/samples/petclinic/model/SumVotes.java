package org.springframework.samples.petclinic.model;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

//this one is to store after the sum of the votes
public class SumVotes 
{
	public Integer weekDay;//dont use this
	
	public String restaurantName;
	
	public Integer totalVotes;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	public LocalDate lunchTime;	

	public Integer getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(Integer weekDay) {
		this.weekDay = weekDay;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public Integer getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(Integer totalVotes) {
		this.totalVotes = totalVotes;
	}

	public LocalDate getLunchTime() {
		return lunchTime;
	}

	public void setLunchTime(LocalDate lunchTime) {
		this.lunchTime = lunchTime;
	}
}
