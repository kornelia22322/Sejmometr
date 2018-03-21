import org.json.JSONObject;
import sun.rmi.server.Activation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ArrayList<JSONObject> arrayList = new DataProvider.Builder().build().getJsonObjects();
        ThreadExecutor threadExecutor = new ThreadExecutor(arrayList);

        List<Deputy> deputyLinkedList = ThreadExecutor.getListOfDeputies();

        for(Deputy deputy: deputyLinkedList) {
            System.out.println(deputy.getName() + " " + deputy.getBirthdate());
        }

    }
}
