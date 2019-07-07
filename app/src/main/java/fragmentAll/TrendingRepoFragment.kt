package fragmentAll


import adapterAll.CentraliseRecyclerViewAdapter
import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gojek.test.gojektest.R

import callBacks.DatabaseCallback
import callBacks.ParentApiCallback
import callBacks.RecyclerViewCallBack
import controllerAll.Controller
import exception_handlling.CatchException
import kotlinx.android.synthetic.main.trending_repo_fragment.*
import staticClasses.Config

class TrendingRepoFragment : Fragment() {
    private var controller: Controller? = null
    private var rootView: View? = null
    private var databaseCallback: DatabaseCallback? = null;
    private var centraliseRecyclerViewAdapter: CentraliseRecyclerViewAdapter? = null
    private var mDataGetter: MutableMap<String, String>? = java.util.HashMap()
    private val recyclerViewCallBack: RecyclerViewCallBack = RecyclerViewCallBack { _ -> }
    private var data: ArrayList<HashMap<String, String>>? = ArrayList()
    private var parentApiCallback: ParentApiCallback? = ParentApiCallback { args ->
        try {
            val callingUI = args[0] as String;
            val response = args[1] as String;
            Log.e("response", "response" + response)

        } catch (e: Exception) {
            CatchException.ExceptionSend(e)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        controller = activity!!.applicationContext as Controller
        rootView = inflater.inflate(R.layout.trending_repo_fragment, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpCallBack()
    }

    private fun setUpCallBack() {
        parentApiCallback = ParentApiCallback { }
        databaseCallback = DatabaseCallback { }
        initialise();
    }

    private fun initialise() {
//        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        centraliseRecyclerViewAdapter = CentraliseRecyclerViewAdapter(context!!, data!!, R.layout.trending_repo_adapter_view, getString(R.string.trending_repo_adapter_view), recyclerViewCallBack)
//        trendingListRecyclerView.layoutManager = linearLayoutManager
//        trendingListRecyclerView.adapter = centraliseRecyclerViewAdapter
//
//        if (controller!!.isNetWorkStatusAvailable(context)) {
//            swipeRefresh.isRefreshing = true
////            controller!!.errorLayout(errorDataParent, topLayout, errorImg, errorMessageTxt, R.drawable.loading_data, getString(R.string.loading_drama_movie_list), View.GONE)
//            callAPI()
//        } else {
////            controller!!.errorLayout(errorDataParent, topLayout, errorImg, errorMessageTxt, R.drawable.empty_data, getString(R.string.enable_internet), View.VISIBLE)
////            dramaList.visibility = View.GONE
//        }
//
//        swipeRefresh.setOnRefreshListener {
//            if (controller!!.isNetWorkStatusAvailable(context)) {
//                swipeRefresh.isRefreshing = true
                callAPI()
//            } else {
//                swipeRefresh.isRefreshing = false
//                if (data!!.size > 0) {
////                    controller!!.snackBarShow(context, coordinatorLayout, getString(R.string.enable_internet))
//                } else {
////                    controller!!.errorLayout(errorDataParent, topLayout, errorImg, errorMessageTxt, R.drawable.empty_data, getString(R.string.enable_internet), View.VISIBLE)
////                    dramaList.visibility = View.GONE
//                }
//            }
//        }
    }

    private fun callAPI() {
//        controller!!.DataMaker(activity, Config.trendingRetrievingAPI, parentApiCallback, mDataGetter)
    }

}
