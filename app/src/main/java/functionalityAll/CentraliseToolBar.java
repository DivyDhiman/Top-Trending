package functionalityAll;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import callBacks.ToolbarCallBack;
import controllerAll.Controller;

/**
 * Created by Abhay dhiman
 */

public class CentraliseToolBar implements View.OnClickListener {

    private Context context;
    private Toolbar toolbar;
    private AppCompatActivity appCompatActivity;
    private ToolbarCallBack toolbarCallBack;
    private TextView optionTextView;
    private String typeValue,header,searchString ="";
    private Controller controller;

    public void toolBarCustom(Object... args) {
        this.typeValue = (String) args[0];
        this.context = (Context) args[1];
        this.toolbarCallBack = (ToolbarCallBack) args[2];

        appCompatActivity = ((AppCompatActivity) context);
        controller = (Controller) context.getApplicationContext();

//        toolbar = appCompatActivity.findViewById(R.id.centralise_toolbar);
//        appCompatActivity.setSupportActionBar(toolbar);
//
//        startViewClick = toolbar.findViewById(R.id.start_view_click);
//        startViewBigger = toolbar.findViewById(R.id.start_view_bigger);

//        if (typeValue.equals(context.getString(R.string.add_review_toolbar))) {
//            header = (String) args[3];
//            showHideView(0, toolbar);
//        }
    }

    private void showHideView(int val, Toolbar toolbar) {

        switch (val) {
            case 0:
//                startViewClick.setVisibility(View.VISIBLE);
//                startViewBigger.setVisibility(View.VISIBLE);
//                startTxtClick.setVisibility(View.VISIBLE);
//                startTxt.setVisibility(View.VISIBLE);
//
//                startViewBigger.setBackgroundResource(R.drawable.whiteback);
//                startTxt.setText(header);
//                startViewClick.setOnClickListener(this);
//
//                startTxt.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
//                toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.toolbar_add_review));
                break;
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.start_view_click:
//                toolbarCallBack.toolBarClick(context.getString(R.string.start_view));
//                break;
        }
    }


}