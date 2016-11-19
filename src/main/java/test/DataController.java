package test;

import java.util.Collections;

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
    
    @RequestMapping(value = "/addAccount", method = RequestMethod.GET, produces = "application/json")
    public int addRow(@RequestParam(value="username") String name, @RequestParam(value="password") String password, @RequestParam(value="email") String email) {
    	DBHitter dao = new DBHitter();
        return dao.addAccount(name, password, email);
    }
    
    @RequestMapping(value = "/getPreferences", method = RequestMethod.GET, produces = "application/json")
    public PreferencePacket[] getPreferences(@RequestParam(value="username") String name) {
    	DBHitter dao = new DBHitter();
        return dao.getPreferences(name);
    }
}



