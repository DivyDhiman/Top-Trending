package functionalityAll

import android.content.Context
import android.os.AsyncTask
import android.util.Log

import com.gojek.test.gojektest.R
import com.google.gson.Gson

import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashMap

import callBacks.ParentApiCallback
import controllerAll.Controller
import exception_handlling.CatchException
import staticClasses.APIParseKeys
import staticClasses.Config

/**
 * Created by Abhay dhiman
 */


class AsyncTaskAPIClass : AsyncTask<Any, String, String>() {
    private var response: String? = null
    private var callingUI: String? = null
    private var type_callback: String? = null
    private val apiMessage: String? = null
    private val apiMessageShow: String? = null
    private val apiStatus: String? = null
    private val userID: String? = null
    private val isBuyer: String? = null
    private val unreadMessageCount: String? = null
    private val returnString: String? = null
    private var context: Context? = null
    private val apiResponse = ApiResponse()
    private var parentApiCallback: ParentApiCallback? = null
    private var dataSendBackMain: ArrayList<HashMap<String, Any>>? = null
    private var dataInnerMain: ArrayList<HashMap<String, Any>>? = null
    private var dataSendBack: HashMap<String, Any>? = null
    private var dataInner: HashMap<String, Any>? = null
    private var dataGet: HashMap<String, Any>? = null
    private val parent: JSONObject? = null
    private val meta: JSONObject? = null
    private val dataObject: JSONObject? = null
    private val data: JSONObject? = null
    private val dataArray: JSONArray? = null
    private var controller: Controller? = null
    private val tel_code: String? = null
    private val imageURL: String? = null
    private val jsonObject: JSONObject? = null
    private val status: String? = null

    override fun onPreExecute() {
        super.onPreExecute()
    }

    //Hit all api in Background Task
    override fun doInBackground(vararg args: Any): String? {
        try {
            this.context = args[0] as Context
            this.callingUI = args[1] as String
            type_callback = "parent"
            controller = context!!.applicationContext as Controller
            this.parentApiCallback = args[2] as ParentApiCallback
        } catch (e: Exception) {
            CatchException.ExceptionSend(e)
        }


        if (callingUI == Config.trendingRetrievingAPI) {
            dataGet = args[3] as HashMap<String, Any>
        }

        try {
            when (callingUI) {
                Config.trendingRetrievingAPI -> {
                    Log.e("ItsWork", "ItsWork"+ callingUI);
                    Log.e("dataObject", "dataObject" + dataGet!!)
                    response = apiResponse.ResponseGet(context!!, Config.trendingRetrievingAPIURL)
                    Log.e("response", "response" + response!!)
                }
            }
        } catch (e: Exception) {
            CatchException.ExceptionSend(e)
        }

        try {
            when (callingUI) {
                Config.trendingRetrievingAPI -> {
                    val responseBaseJSONArray = JSONArray()
                    dataSendBackMain = ArrayList()
                    for (i in 0..responseBaseJSONArray.length()) {
                        val responseBaseJSONObject = responseBaseJSONArray.getJSONObject(i)
                        val author = responseBaseJSONObject.getString(APIParseKeys.author)
                        val name = responseBaseJSONObject.getString(APIParseKeys.name)
                        val url = responseBaseJSONObject.getString(APIParseKeys.url)
                        val description = responseBaseJSONObject.getString(APIParseKeys.description)
                        val language = responseBaseJSONObject.getString(APIParseKeys.language)
                        val languageColor = responseBaseJSONObject.getString(APIParseKeys.languageColor)
                        val stars = responseBaseJSONObject.getString(APIParseKeys.stars)
                        val forks = responseBaseJSONObject.getString(APIParseKeys.forks)
                        val currentPeriodStars = responseBaseJSONObject.getString(APIParseKeys.currentPeriodStars)

                        dataSendBack = HashMap()
                        dataSendBack!![APIParseKeys.author] = author
                        dataSendBack!![APIParseKeys.name] = name
                        dataSendBack!![APIParseKeys.url] = url
                        dataSendBack!![APIParseKeys.description] = description
                        dataSendBack!![APIParseKeys.language] = language
                        dataSendBack!![APIParseKeys.languageColor] = languageColor
                        dataSendBack!![APIParseKeys.stars] = stars
                        dataSendBack!![APIParseKeys.forks] = forks
                        dataSendBack!![APIParseKeys.currentPeriodStars] = currentPeriodStars

                        val buildByJSONArray = responseBaseJSONObject.getJSONArray(APIParseKeys.builtBy)

                        dataInnerMain = ArrayList()
                        for (j in 0..buildByJSONArray.length()) {
                            val buildByJSONObject = buildByJSONArray.getJSONObject(j)
                            dataInner = HashMap()
                            val href = buildByJSONObject.getString(APIParseKeys.href)
                            val avatar = buildByJSONObject.getString(APIParseKeys.avatar)
                            val username = buildByJSONObject.getString(APIParseKeys.username)

                            dataInner!![APIParseKeys.href] = href
                            dataInner!![APIParseKeys.avatar] = avatar
                            dataInner!![APIParseKeys.username] = username
                            dataInnerMain!!.add(dataInner!!)
                            onPostExecute("hello")
                        }

                        val gson = Gson()
                        dataSendBack!![APIParseKeys.builtBy] = gson.toJson(dataInnerMain)
                        dataSendBackMain!!.add(dataSendBack!!)
                    }
                }
            }
        } catch (e: Exception) {
            CatchException.ExceptionSend(e)
        }

        return null
    }

    override fun onPostExecute(file_url: String) {
        try {
            //Check is Response is empty or not
            if (response == null) {
                response = context!!.getString(R.string.error)
            }

            when (callingUI) {
                Config.trendingRetrievingAPI -> if (response == context!!.getString(R.string.error_Http_not_found)) {
                    parentApiCallback!!.dataCallBack(callingUI, response)
                } else if (response == context!!.getString(R.string.error_Http_internal)) {
                    parentApiCallback!!.dataCallBack(callingUI, response)
                } else if (response == context!!.getString(R.string.error_Http_other)) {
                    parentApiCallback!!.dataCallBack(callingUI, response)
                } else if (response == context!!.getString(R.string.error)) {
                    parentApiCallback!!.dataCallBack(callingUI, response)
                } else if (response == Config.statusError) {
                    parentApiCallback!!.dataCallBack(callingUI, response, apiMessage)
                } else if (response == Config.messageError) {
                    parentApiCallback!!.dataCallBack(callingUI, response, apiMessage)
                } else {
                    parentApiCallback!!.dataCallBack(callingUI, response, dataSendBackMain)
                }
            }
        } catch (e: Exception) {
            CatchException.ExceptionSend(e)
        }

    }
}