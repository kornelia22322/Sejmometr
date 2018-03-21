import org.json.JSONObject;
import sun.rmi.server.Activation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ArrayList<JSONObject> arrayList = new DataProvider.Builder().build().getJsonObjects();
        Thread[] threads = new Thread[arrayList.size()];

        for(int i = 0; i < arrayList.size(); i++) {
            threads[i] = new Thread(new MapJsonToObjectThread(arrayList.get(i)));
        }

        for(int i = 0; i < arrayList.size(); i++) {
            threads[i].start();
        }

        for(int i = 0; i < arrayList.size(); i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<Deputy> list = MapJsonToObjectThread.getSyncList();
        int i = 0;

        for(Deputy deputy : list) {
            if(deputy.getName().length() < 1) {
                list.remove(deputy);
            }
        }

        Collections.sort(list, new CompareByAttendance());

        list.stream()
                .sorted(new CompareByAttendance())
                .forEach(item -> System.out.println(item.getAttendance()));

    }
}
