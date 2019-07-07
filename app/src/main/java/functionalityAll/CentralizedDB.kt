package functionalityAll

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.lang.reflect.Type
import java.util.ArrayList
import java.util.HashMap

import callBacks.DatabaseCallback
import exception_handlling.CatchException
import staticClasses.ConfigDB
import staticClasses.DataBaseKeys


class CentralizedDB(context: Context, version: Int) : SQLiteOpenHelper(context, ConfigDB.DB_NAME, null, version) {
    private var getJobData: ArrayList<HashMap<String, Any>>? = null
    private val fromJsonJobItemData: ArrayList<HashMap<String, String>>? = null
    private val getJobDataSub: HashMap<String, Any>? = null
    private var tableName: String? = null
    private var sqLiteDatabase: SQLiteDatabase? = null
    private var centralizedDB: CentralizedDB? = null
    private var databaseCallback: DatabaseCallback? = null
    private val dataObject: JSONArray? = null

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(ConfigDB.CREATING_DB_TRENDING_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + ConfigDB.jobData)
            onCreate(db)
        } catch (e: Exception) {
            Log.e("DB_Version_Exception", "- $e")
            CatchException.ExceptionSend(e)
        }

    }

    fun db_close(db: SQLiteDatabase) {
        db.close()
    }

    @Throws(Exception::class)
    fun dataInsertionFunction(vararg args: Any) {
        tableName = args[0] as String
        centralizedDB = args[1] as CentralizedDB
        databaseCallback = args[2] as DatabaseCallback
        sqLiteDatabase = centralizedDB!!.writableDatabase

        if (tableName == ConfigDB.jobData) {
            getJobData = args[3] as ArrayList<HashMap<String, Any>>
            for (i in getJobData!!.indices) {
                val getJobSub = getJobData!![i]
                val cv = ContentValues()
                cv.put(DataBaseKeys.author, getJobSub[DataBaseKeys.author].toString())
                cv.put(DataBaseKeys.name, getJobSub[DataBaseKeys.name].toString())
                cv.put(DataBaseKeys.url, getJobSub[DataBaseKeys.url].toString())
                cv.put(DataBaseKeys.description, getJobSub[DataBaseKeys.description].toString())
                cv.put(DataBaseKeys.language, getJobSub[DataBaseKeys.language].toString())
                cv.put(DataBaseKeys.languageColor, getJobSub[DataBaseKeys.languageColor].toString())
                cv.put(DataBaseKeys.stars, getJobSub[DataBaseKeys.stars].toString())
                cv.put(DataBaseKeys.forks, getJobSub[DataBaseKeys.forks].toString())
                cv.put(DataBaseKeys.currentPeriodStars, getJobSub[DataBaseKeys.currentPeriodStars].toString())
                cv.put(DataBaseKeys.builtBy, getJobSub[DataBaseKeys.builtBy].toString())
                sqLiteDatabase!!.insert(tableName, "", cv)
            }
            databaseCallback!!.getDatabaseCallback(ConfigDB.TYPE_SET_DATA)
        }
    }

    @Throws(Exception::class)
    fun dataRetrievalFunction(vararg args: Any) {
        tableName = args[0] as String
        centralizedDB = args[1] as CentralizedDB
        databaseCallback = args[2] as DatabaseCallback
        val requestType = args[3] as String

        sqLiteDatabase = centralizedDB!!.writableDatabase
        val cursor: Cursor

        if (tableName == ConfigDB.jobData) {
            cursor = sqLiteDatabase!!.rawQuery("Select * from " + tableName + " ORDER BY " + ConfigDB.KEY_ID + " asc", null)
            if (cursor.count > 0) {
                getJobData = ArrayList()
                while (cursor.moveToNext()) {
                    val getJobDataSub = HashMap<String, Any>()
                    getJobDataSub[DataBaseKeys.author] = cursor.getString(cursor.getColumnIndex(DataBaseKeys.author))
                    getJobDataSub[DataBaseKeys.name] = cursor.getString(cursor.getColumnIndex(DataBaseKeys.name))
                    getJobDataSub[DataBaseKeys.url] = cursor.getString(cursor.getColumnIndex(DataBaseKeys.url))
                    getJobDataSub[DataBaseKeys.description] = cursor.getString(cursor.getColumnIndex(DataBaseKeys.description))
                    getJobDataSub[DataBaseKeys.language] = cursor.getString(cursor.getColumnIndex(DataBaseKeys.language))
                    getJobDataSub[DataBaseKeys.languageColor] = cursor.getString(cursor.getColumnIndex(DataBaseKeys.languageColor))
                    getJobDataSub[DataBaseKeys.stars] = cursor.getString(cursor.getColumnIndex(DataBaseKeys.stars))
                    getJobDataSub[DataBaseKeys.forks] = cursor.getString(cursor.getColumnIndex(DataBaseKeys.forks))
                    getJobDataSub[DataBaseKeys.currentPeriodStars] = cursor.getString(cursor.getColumnIndex(DataBaseKeys.currentPeriodStars))
                    getJobDataSub[DataBaseKeys.builtBy] = cursor.getString(cursor.getColumnIndex(DataBaseKeys.builtBy))
                    getJobData!!.add(getJobDataSub)
                }
                databaseCallback!!.getDatabaseCallback(requestType, getJobData)
            } else {
                databaseCallback!!.getDatabaseCallback(ConfigDB.TYPE_EMPTY_ALL_DATA)
            }
        }
    }


    fun isDataAvailable(tableName: String, centralizedDB: CentralizedDB): Boolean {
        sqLiteDatabase = centralizedDB.writableDatabase
        val cursorCheck = sqLiteDatabase!!.rawQuery("Select * from $tableName", null)
        if (cursorCheck.count > 0) {
            cursorCheck.close()
            return true
        } else {
            cursorCheck.close()
            return false
        }
    }

    @Throws(Exception::class)
    fun deleteTableDB(tableName: String, centralizedDB: CentralizedDB) {
        sqLiteDatabase = centralizedDB.writableDatabase
        Log.e("testing_deletetable", "- $tableName")
        val deleteProgress = sqLiteDatabase!!.delete(tableName, null, null)
    }

    fun DB_Delete(vararg args: Any) {
        centralizedDB = args[0] as CentralizedDB
        sqLiteDatabase = centralizedDB!!.readableDatabase
        sqLiteDatabase!!.close()
    }
}