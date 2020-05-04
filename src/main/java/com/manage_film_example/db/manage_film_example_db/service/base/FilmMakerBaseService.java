/* 
* Generated by
* 
*      _____ _          __  __      _     _
*     / ____| |        / _|/ _|    | |   | |
*    | (___ | | ____ _| |_| |_ ___ | | __| | ___ _ __
*     \___ \| |/ / _` |  _|  _/ _ \| |/ _` |/ _ \ '__|
*     ____) |   < (_| | | | || (_) | | (_| |  __/ |
*    |_____/|_|\_\__,_|_| |_| \___/|_|\__,_|\___|_|
*
* The code generator that works in many programming languages
*
*			https://www.skaffolder.com
*
*
* You can generate the code from the command-line
*       https://npmjs.com/package/skaffolder-cli
*
*       npm install -g skaffodler-cli
*
*   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
*
* To remove this comment please upgrade your plan here: 
*      https://app.skaffolder.com/#!/upgrade
*
* Or get up to 70% discount sharing your unique link:
*       https://app.skaffolder.com/#!/register?friend=5eb0390982f82d390e783cc3
*
* You will get 10% discount for each one of your friends
* 
*/
package com.manage_film_example.db.manage_film_example_db.service.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.manage_film_example.db.manage_film_example_db.entity.FilmMaker;
import com.manage_film_example.db.manage_film_example_db.service.FilmMakerService;

//IMPORT RELATIONS
import com.manage_film_example.db.manage_film_example_db.entity.FilmMaker;

@Service
public class FilmMakerBaseService {

	private static NamedParameterJdbcTemplate jdbcTemplate;
	
		@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	


    //CRUD METHODS
    
    //CRUD - CREATE
    	
	public FilmMaker insert(FilmMaker obj) {
		Long id = jdbcTemplate.queryForObject("select max(_id) from `filmmaker`", new MapSqlParameterSource(), Long.class);
		obj.set_id(id == null ? 1 : id + 1);
		String sql = "INSERT INTO `filmmaker` (`_id`, `name`,`surname`) VALUES (:id,:name,:surname)";
			SqlParameterSource parameters = new MapSqlParameterSource()
		    .addValue("id", obj.get_id())
			.addValue("name", obj.getName())
			.addValue("surname", obj.getSurname());
		
		jdbcTemplate.update(sql, parameters);
		return obj;
	}
	
    	
    //CRUD - REMOVE
    
	public void delete(Long id) {
		String sql = "DELETE FROM `FilmMaker` WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
		
		jdbcTemplate.update(sql, parameters);
	}

    	
    //CRUD - GET ONE
    	
	public FilmMaker get(Long id) {
	    
		String sql = "select * from `FilmMaker` where `_id` = :id";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
	    
	    return (FilmMaker) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(FilmMaker.class));
	}


    	
        	
    //CRUD - GET LIST
    	
	public List<FilmMaker> getList() {
	    
		String sql = "select * from `FilmMaker`";
		
	    SqlParameterSource parameters = new MapSqlParameterSource();
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(FilmMaker.class));
	    
	    
	}

    	
    //CRUD - EDIT
    	
	public FilmMaker update(FilmMaker obj, Long id) {

		String sql = "UPDATE `FilmMaker` SET `name` = :name,`surname` = :surname  WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id)
			.addValue("name", obj.getName())
			.addValue("surname", obj.getSurname());
		jdbcTemplate.update(sql, parameters);
	    return obj;
	}
	
    	
    
    
    
    

    
    /*
     * CUSTOM SERVICES
     * 
     *	These services will be overwritten and implemented in FilmMakerService.java
     */
    

}
