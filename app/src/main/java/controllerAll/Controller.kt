package controllerAll

import android.app.Application
import android.content.Context
import android.util.Log

import exception_handlling.CatchException
import functionalityAll.CentraliseCheck
import functionalityAll.CentraliseDataMakerClass

/**
 * Created by Abhay dhiman
 */

//Controller centralised call calling in whole application extended Application
class Controller : Application() {
    private val centraliseCheck = CentraliseCheck()
    private val centraliseDataMakerClass = CentraliseDataMakerClass()


    //Check Internet connections
    fun isNetWorkStatusAvailable(context: Context): Boolean {
        return centraliseCheck.isNetWorkStatusAvailable(context)
    }

    fun DataMaker(vararg args: Any) {
        try {
            centraliseDataMakerClass.DataMaker(*args)
        } catch (e: Exception) {
            Log.e("DrawerExpSa", "DrawerExpSa$e")
            CatchException.ExceptionSend(e)
        }

    }

}