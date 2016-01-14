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
package org.springframework.samples.petclinic.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Restaurant;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.RestaurantRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Repository;

/**
 * A simple JDBC-based implementation of the {@link VetRepository} interface.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Sam Brannen
 * @author Thomas Risberg
 * @author Mark Fisher
 * @author Michael Isvy
 */
@Repository
public class JdbcRestaurantRepositoryImpl implements RestaurantRepository {

    private JdbcTemplate jdbcTemplate;
    
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public JdbcRestaurantRepositoryImpl(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) 
    {
        
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    	
    	this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Refresh the cache of Restaurants that the ClinicService is holding.
     */
    @Override
    public Collection<Restaurant> findAll() throws DataAccessException {
        List<Restaurant> restaurants = new ArrayList<>();
        // Retrieve the list of all restaurants.
        restaurants.addAll(this.jdbcTemplate.query(
            "SELECT main_name FROM restaurants",
            BeanPropertyRowMapper.newInstance(Restaurant.class)));
        
        return restaurants;
    }
    
    /**
     * Return one id of a Restaurant name
     */
    @Override
    public Restaurant findIdByMainName(String sMainName) throws DataAccessException{
    	
    	Restaurant restaurant;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("mainName", sMainName);
            restaurant = this.namedParameterJdbcTemplate.queryForObject(
                "SELECT id, first_name, last_name, address, city, telephone FROM owners WHERE id= :id",
                params,
                BeanPropertyRowMapper.newInstance(Restaurant.class)
            );
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Owner.class, sMainName);
        }
        
        return restaurant;
    }
}
