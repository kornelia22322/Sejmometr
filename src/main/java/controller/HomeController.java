package controller;

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
    public Map<Integer, String> getDeputiesList() {
        ArrayList<JSONObject> arrayList = new DataProvider.Builder().build().getJsonObjects();
        ThreadExecutor threadExecutor = new ThreadExecutor(arrayList);

        List<Deputy> deputyLinkedList = ThreadExecutor.getListOfDeputies();
        Map deputiesMap = convertListToMap(deputyLinkedList);
        return deputiesMap;
    }

    private Map<String, String> convertListToMap(List<Deputy> deputiesList) {
        Map<String, String> deputiesMap = new HashMap<>();
        Map map = new HashMap();
        for(Deputy deputy: deputiesList) {
            deputiesMap.put("id", Integer.toString(deputy.getId()));
            deputiesMap.put("name", deputy.getName());
        }
        map.put("result", deputiesMap);
        return map;
    }


}
