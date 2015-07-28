package com.yazao.instamaterial.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.yazao.instamaterial.R;
import com.yazao.instamaterial.activity.MainActivity;
import com.yazao.instamaterial.util.Utils;
import com.yazao.instamaterial.view.SquaredFrameLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by shaopingzhai on 15/7/10.
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity context;
    private int itemsCount=0;
    private  int lastAnimatedPosition=-1;
    private int ANIMATED_ITEMS_COUNT=2;


    public FeedAdapter(Activity activity) {
        this.context=activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);

        return new CellFeedViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        runEnterAnimation(holder.itemView,position);
        CellFeedViewHolder viewHolder = (CellFeedViewHolder) holder;

        if (position % 2 == 0) {
            viewHolder.ivFeedCenter.setImageResource(R.drawable.img_feed_center_1);
            viewHolder.ivFeedBottom.setImageResource(R.drawable.img_feed_bottom_1);
        } else {
            viewHolder.ivFeedCenter.setImageResource(R.drawable.img_feed_center_2);
            viewHolder.ivFeedBottom.setImageResource(R.drawable.img_feed_bottom_2);
        }

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private void runEnterAnimation(View itemView, int position) {

        // 动画 在 第0，1个item上面执行
        if (position>=ANIMATED_ITEMS_COUNT -1){
            return;
        }
        if(position>lastAnimatedPosition){
            lastAnimatedPosition=position;

            //先将item 设置到屏幕外面
            itemView.setTranslationY(Utils.getScreenHeight(context));
            itemView.animate().translationY(0).setDuration(700).setInterpolator(new DecelerateInterpolator()).start();;
        }

    }

    @Override
    public int getItemCount() {
        return itemsCount;
    }


    public static class CellFeedViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.ivFeedCenter)
        ImageView ivFeedCenter;
        @InjectView(R.id.ivFeedBottom)
        ImageView ivFeedBottom;


        public CellFeedViewHolder(View inflate) {
            super(inflate);
            ButterKnife.inject(this,inflate);

        }
    }

    public void updateItems(){
        itemsCount=10;
        this.notifyDataSetChanged();
    }
}
