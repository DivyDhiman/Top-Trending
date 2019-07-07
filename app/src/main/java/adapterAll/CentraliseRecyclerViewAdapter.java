package adapterAll;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gojek.test.gojektest.R;
import java.util.ArrayList;
import java.util.HashMap;
import callBacks.RecyclerViewCallBack;
import controllerAll.Controller;


@SuppressWarnings("unchecked")
public class CentraliseRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<HashMap<String, String>> data;
    private int layout;
    private RecyclerViewCallBack recyclerViewCallBack;
    private Controller controller;

    public CentraliseRecyclerViewAdapter(Object... args) {
        this.context = (Context) args[0];
        this.data = (ArrayList<HashMap<String, String>>) args[1];
        this.layout = (int) args[2];
        this.recyclerViewCallBack = (RecyclerViewCallBack) args[3];
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;

        controller = (Controller) context.getApplicationContext();

        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        vh = new DataViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
    }

    @Override
    public int getItemCount() {
        try {
                return data.size();
        } catch (Exception e) {
            return 0;
        }
    }


    private class DataViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout parentClick, movieCartParent, movieLikeParent;
        private TextView nameTxt, commentTxt, replyTxt, reviewTxt, movieName, movieTime, movieDescription, likeCount, commentCount, movieType, textType,
                castName, removeCartTxt, statusOrderTxt, removeWishTxt, saveForLaterTxt, buyNowTxt;
        private ImageView parentImage, castImage, movieImg, userImg, ratingMovie1, ratingMovie2, ratingMovie3, ratingMovie4, ratingMovie5, imageShow, likeImage,
                cartImage;

        private DataViewHolder(View view) {
            super(view);

//            if (type.equals(context.getString(R.string.type_dashboard))) {
////                parentImage = view.findViewById(R.id.parent_image);
////                textType = view.findViewById(R.id.text_type);
//
//            }
        }
    }
}