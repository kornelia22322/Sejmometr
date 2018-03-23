package model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ThreadExecutor {
    private static List<Deputy> listOfDeputies = new LinkedList<>();

    public ThreadExecutor(ArrayList<JSONObject> deputies) {
        executeThreads(deputies);
        listOfDeputies = (List<Deputy>) MapJsonToObjectThread.getSyncList();
    }

    public static List<Deputy> getListOfDeputies() {
        return listOfDeputies;
    }

    private void executeThreads(ArrayList<JSONObject> arrayList) {
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
    }
}
