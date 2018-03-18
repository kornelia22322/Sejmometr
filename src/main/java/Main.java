import org.json.JSONObject;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<JSONObject> arrayList = new DataProvider.Builder().build().getJsonObjects();
        Thread[] threads = new Thread[arrayList.size()];

        for(int i = 0; i < arrayList.size(); i++) {
            threads[i] = new Thread(new SingleSearchThread(arrayList.get(i), "Jarosław Kaczyński"));
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

        System.out.println(SingleSearchThread.getDeputy().getName());
    }
}
