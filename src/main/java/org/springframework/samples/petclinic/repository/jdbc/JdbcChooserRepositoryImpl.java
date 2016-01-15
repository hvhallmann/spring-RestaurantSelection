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
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.model.Chooser;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.ChooserRepository;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Repository;

/**
 * A simple JDBC-based implementation of the {@link OwnerRepository} interface.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Sam Brannen
 * @author Thomas Risberg
 * @author Mark Fisher
 */
@Repository
public class JdbcChooserRepositoryImpl implements ChooserRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertChoice;
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcChooserRepositoryImpl(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {

        this.insertChoice = new SimpleJdbcInsert(dataSource)
            .withTableName("userSelection")
            .usingGeneratedKeyColumns("id");

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        
        this.jdbcTemplate = jdbcTemplate;

    }


    /**
     * Loads the {@link Owner} with the supplied <code>id</code>; also loads the {@link Pet Pets} and {@link Visit Visits}
     * for the corresponding owner, if not already loaded.
     
    @Override
    public Owner findByDate(int id) throws DataAccessException {
        Owner owner;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            owner = this.namedParameterJdbcTemplate.queryForObject(
                "SELECT id, first_name, last_name, address, city, telephone FROM owners WHERE id= :id",
                params,
                BeanPropertyRowMapper.newInstance(Owner.class)
            );
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Owner.class, id);
        }
        loadPetsAndVisits(owner);
        return owner;
    }
    */

    @Override
    public void save(Chooser choice) throws DataAccessException {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(choice);
        if (choice.isNew()) {
            Number newKey = this.insertChoice.executeAndReturnKey(parameterSource);
            choice.setId(newKey.intValue());
        } else {
            this.namedParameterJdbcTemplate.update(
                "UPDATE userSelection SET owner_id=:owner_id, restaurant_id=:restaurant_id, " +
                    "pick_date=:pick_date, description=:description WHERE id=:id",
                parameterSource);
        }
    }


    /**
     * Refresh the cache of Owners that the ClinicService is holding.
    */ 
    @Override
    public Collection<Chooser> findAll() throws DataAccessException {
        List<Chooser> choices = new ArrayList<>();
        // Retrieve the list of all choices.
        choices.addAll(this.jdbcTemplate.query(
            "SELECT owner_id, restaurant_id FROM userSelection",
            BeanPropertyRowMapper.newInstance(Chooser.class)));

        return choices;
    }
    
     
}
