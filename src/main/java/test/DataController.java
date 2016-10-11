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
    Then you can go to "http://localhost:8080/getRows?username=user".
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

    @RequestMapping(value = "/getRows", method = RequestMethod.GET, produces = "application/json")
    public DataPacket[] getRows(@RequestParam(value="username") String name) {
    	DBHitter dao = new DBHitter();
        return dao.getRows(name);
    }
}