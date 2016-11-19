package test;

import java.sql.SQLException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
	
/*
    In order to get this to run, just go in command line to the project folder
    and run "mvnw spring-boot:run". That will make it run.
    Then you can go to "http://localhost:8080/getAccounts?username=user".
    Another option is go to the project folder and run "mvnw clean package". Then if you go to the project
    folder, go to the target folder, there will be something called
    CapstoneRestService-0.1.0.jar   You can run that, and it will do the same as if you ran
    "mvnw spring-boot:run", then you can hit the url previously given.
*/
	
	public DataController()
	{
    	//Eventually, I think we'll put the DAO in this constructor, and make all
		//the database queries threaded within the DBHitter class
	}

    @RequestMapping(value = "/getAccounts", method = RequestMethod.GET, produces = "application/json")
    public UserPacket[] getRows(@RequestParam(value="username") String name) {
    	DBHitter dao = new DBHitter();
        return dao.getAccounts(name);
    }
    
    @RequestMapping(value = "/getAccountsByEmail", method = RequestMethod.GET, produces = "application/json")
    public UserPacket[] getAccountsByEmail(@RequestParam(value="email") String email) {
    	DBHitter dao = new DBHitter();
        return dao.getAccountsByEmail(email);
    }
    
    @RequestMapping(value = "/addAccount", method = RequestMethod.GET, produces = "application/json")
    public int addRow(@RequestParam(value="username") String name, @RequestParam(value="password") String password, @RequestParam(value="email") String email) {
    	DBHitter dao = new DBHitter();
    	return dao.addAccount(name, password, email);
    }
    
    @RequestMapping(value = "/getPreferences", method = RequestMethod.GET, produces = "application/json")
    public PreferencePacket[] getPreferences(@RequestParam(value="userID") int userID) {
    	DBHitter dao = new DBHitter();
        return dao.getPreferences(userID);
    }
    
    @RequestMapping(value = "/getAllFood", method = RequestMethod.GET, produces = "application/json")
    public FoodPacket[] getAllFood() {
    	DBHitter dao = new DBHitter();
        return dao.getAllFood();
    }

    @RequestMapping(value = "/getSuggestion", method = RequestMethod.GET, produces = "application/json")
    public PreferencePacket getSuggestion(@RequestParam(value="userID") int userID) throws SQLException{
    	DBHitter dao = new DBHitter();
        return dao.getSuggestion(userID);
    }

    @RequestMapping(value = "/addPreference", method = RequestMethod.GET, produces = "application/json")
    public int addPreference(@RequestParam(value="userID") int userID,
    							@RequestParam(value="foodType") String foodType,
    							@RequestParam(value="foodName") String foodName,
    							@RequestParam(value="rating") int rating) throws SQLException{
    	DBHitter dao = new DBHitter();
        return dao.addPreference(userID, foodType, foodName, rating);
    }
    
    @RequestMapping(value = "/getNewSuggestion", method = RequestMethod.GET, produces = "application/json")
    public PreferencePacket getNewSuggestion() throws SQLException{
    	DBHitter dao = new DBHitter();
        return dao.getNewSuggestion();
    }
    
    @RequestMapping(value = "/getFoodType", method = RequestMethod.GET, produces = "application/json")
    public FoodPacket getFoodType(@RequestParam(value="foodName") String foodName) throws SQLException{
    	DBHitter dao = new DBHitter();
        return dao.getFoodType(foodName);
    }

    @RequestMapping(value = "/searchUsersByName", method =RequestMethod.GET, produces = "application/json")
    public FriendPacket[] searchUsersByName(@RequestParam("username") String username) throws SQLException{
    	DBHitter dao = new DBHitter();
        return dao.searchUsersByName(username);
    }

    @RequestMapping(value = "/addFriend", method=RequestMethod.GET, produces = "application/json")
    public boolean addFriend(@RequestParam("fromID") int fromID, @RequestParam("toID") int toID) throws SQLException{
        DBHitter dao = new DBHitter();
        return dao.addFriend(fromID, toID);
    }
}



