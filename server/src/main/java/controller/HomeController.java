package controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.DataProvider;
import model.Deputy;
import model.ThreadExecutor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @RequestMapping("/")
    @JsonView(views.Views.HomeView.class)
    public List<Deputy> getDeputiesList() {
        ArrayList<JSONObject> arrayList = new DataProvider.Builder().build().getJsonObjects();
        ThreadExecutor threadExecutor = new ThreadExecutor(arrayList);
        List<Deputy> deputyLinkedList = ThreadExecutor.getListOfDeputies();
        return deputyLinkedList;
    }
}
