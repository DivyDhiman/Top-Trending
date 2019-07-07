package exception_handlling;

import android.util.Log;

public class CatchException {
    public static void ExceptionSend(Exception exception){
        Log.e("exception","exception ---  "+exception);
    }
}