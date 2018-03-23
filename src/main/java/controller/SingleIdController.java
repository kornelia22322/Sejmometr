package controller;

import model.Deputy;
import model.ThreadExecutor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class SingleIdController {

    @RequestMapping(value = "/deputy/{id}", method = GET)
    public Deputy getSingleDeputy(@PathVariable("id") int id) {
        List<Deputy> deputyLinkedList = ThreadExecutor.getListOfDeputies();
        for(Deputy deputy: deputyLinkedList) {
            if(deputy.getId() == id) {
                return deputy;
            }
        }
        return null;
    }
}
