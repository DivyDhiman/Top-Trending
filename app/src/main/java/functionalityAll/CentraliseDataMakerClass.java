package functionalityAll;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONObject;
import java.util.HashMap;

import staticClasses.Config;

@SuppressWarnings("unchecked")
public class CentraliseDataMakerClass {
    private Context context;
    private String callingUI;
    private Object object;
    private HashMap<String, String> dataGet;
    private JSONObject data;


    public void DataMaker(Object... args) throws Exception {
        context = (Context) args[0];
        callingUI = (String) args[1];
        object = args[2];
        dataGet = (HashMap<String, String>) args[3];

        Log.e("dataGet", "dataGet" + dataGet.toString());
        try {
            callAPIMethod(callingUI);
        } catch (Exception e) {
            Log.e("EXCEPTIONOCCURSS", "EXCEPTIONOCCURSS" + e);
        }
    }

    private void callAPIMethod(String callingUI) throws Exception {
        switch (callingUI) {
            case Config.trendingRetrievingAPI:
                new AsyncTaskAPIClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, context, callingUI, object, dataGet);
                break;
        }
    }
}
