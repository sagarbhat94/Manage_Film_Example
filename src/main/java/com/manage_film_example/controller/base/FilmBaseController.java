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
package com.manage_film_example.controller.base;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import org.springframework.security.access.annotation.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import com.manage_film_example.db.manage_film_example_db.service.FilmService;
import com.manage_film_example.db.manage_film_example_db.entity.Film;

//IMPORT RELATIONS
import com.manage_film_example.db.manage_film_example_db.entity.Actor;
import com.manage_film_example.db.manage_film_example_db.entity.FilmMaker;

public class FilmBaseController {
    
    @Autowired
	FilmService filmService;



//CRUD METHODS


    //CRUD - CREATE
    @Secured({ "ROLE_PRIVATE_USER" })
		@RequestMapping(value = "/film", method = RequestMethod.POST, headers = "Accept=application/json")
	public Film insert(@RequestBody Film obj) {
		Film result = filmService.insert(obj);

	    
		//external relation cast
		ArrayList<Long> cast = obj.getCast();
		if (cast != null) {
			FilmService.Film_castService.updateRelation(result.get_id(), cast);
		}
		
		
		return result;
	}

	
    //CRUD - REMOVE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/film/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") Long id) {
		filmService.delete(id);
	}
	

    //CRUD - FIND BY Cast
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/film/findBycast/{key}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Film> findBycast(@PathVariable("key") Long idcast) {
		List<Film> list = filmService.findBycast(idcast);
		return list;
	}

    //CRUD - FIND BY FilmMaker
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/film/findByfilmMaker/{key}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Film> findByfilmMaker(@PathVariable("key") Long idfilmMaker) {
		List<Film> list = filmService.findByfilmMaker(idfilmMaker);
		return list;
	}
	
    //CRUD - GET ONE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/film/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Film get(@PathVariable Long id) {
		Film obj = filmService.get(id);
		
		
		//external relation cast
		ArrayList<Long> cast = FilmService.Film_castService.findBy_Film(id);
		obj.setCast(cast);
		
		
		return obj;
	}
	
	
    //CRUD - GET LIST
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/film", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Film> getList() {
		return filmService.getList();
	}
	
	

    //CRUD - EDIT
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/film/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public Film update(@RequestBody Film obj, @PathVariable("id") Long id) {
		Film result = filmService.update(obj, id);

	    
		//external relation cast
		ArrayList<Long> cast = obj.getCast();
		if (cast != null) {
			FilmService.Film_castService.updateRelation(id, cast);
		}
		
		
		return result;
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


	
}
