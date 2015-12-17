package co.laiup.adr.laiuplib.adapters;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.laiup.adr.laiuplib.Log.L;
import co.laiup.adr.laiuplib.R;
import co.laiup.adr.laiuplib.models.Story;

/**
 * Project LaiupLib
 * Created by Ha on 10/7/2015.
 */
public class NewsFeedAdapter extends RecyclerView.Adapter {
    // #1 : define view type if your list have several kinds of view
    // #2 : some necessary variable
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Story> data;

    // #touch animation
    private Animation animScaleIn, animScaleOut;

    // #click listener
    private ClickListener clickListener;

    // #3 : constructor
    public NewsFeedAdapter(Context context, ArrayList<Story> data) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
        animScaleIn = AnimationUtils.loadAnimation(context, R.anim.button_zoom_in);
        animScaleOut = AnimationUtils.loadAnimation(context, R.anim.button_zoom_out);
    }

    // #4 : which data ~ which view type
    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    /*
     * @function : this method will create view for each data depend on viewType returned.
     * viewType will be return by what you determined in getItemViewType method.
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_newsfeed, parent, false);
        return new VHItem(view);
    }

    /*
     * @function : this method will bind data into view (created from onCreateViewHolder method)
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int position) {
        VHItem holder = (VHItem) vHolder;
        Story story = data.get(position);
/*        holder.tvTitle.setText(video.getTitle());
        holder.tvDescription.setText(video.getDescription());
        holder.tvPublishedAt.setText(video.getPublishedAt());
        holder.tvChannel.setText(video.getChannelTitle());
        Glide.with(context)
                .load(video.getThumbnailDefault())
                .error(R.drawable.thumbnail)
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivThumbnail);*/
    }

    // return total view item (maybe it's less than or greater than your data size
    @Override
    public int getItemCount() {
        return data.size();
    }

    private class VHItem extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivAvatar, ivPhoto, ivOption, ivLike, ivComment, ivShare, ivDislike;
        private TextView tvNickname, tvPostTime, tvPostLocation, tvStatus, tvLikeCount, tvDislikeCount;


        public VHItem(View view) {
            super(view);
            ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar);
            ivPhoto = (ImageView) view.findViewById(R.id.iv_photo);
            ivOption = (ImageView) view.findViewById(R.id.iv_option);
            ivLike = (ImageView) view.findViewById(R.id.iv_like);
            ivComment = (ImageView) view.findViewById(R.id.iv_comment);
            ivShare = (ImageView) view.findViewById(R.id.iv_share);
            ivDislike = (ImageView) view.findViewById(R.id.iv_dislike);
            tvNickname = (TextView) view.findViewById(R.id.tv_nickname);
            tvPostTime = (TextView) view.findViewById(R.id.tv_post_time);
            tvPostLocation = (TextView) view.findViewById(R.id.tv_post_location);
            tvStatus = (TextView) view.findViewById(R.id.tv_status);
            tvLikeCount = (TextView) view.findViewById(R.id.tv_like_count);
            tvDislikeCount = (TextView) view.findViewById(R.id.tv_dislike);

            ivLike.setOnClickListener(this);
            ivComment.setOnClickListener(this);
            ivShare.setOnClickListener(this);
            ivDislike.setOnClickListener(this);
            ivOption.setOnClickListener(this);
            ivLike.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent ev) {
                    switch (ev.getActionMasked()) {
                        case MotionEvent.ACTION_DOWN:
                            L.m("View onTouch ACTION_DOWN");
                            ivLike.startAnimation(animScaleOut);
                            break;
                        case MotionEvent.ACTION_UP:
                            L.m("View onTouch ACTION_UP");
                            ivLike.startAnimation(animScaleIn);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            L.m("View onTouch ACTION_MOVE");
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            L.m("View onTouch ACTION_CANCEL");
                            ivLike.startAnimation(animScaleIn);
                            break;
                    }
                    return false;
                }
            });
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null) {
                clickListener.onItemClicked(v, getAdapterPosition());
            }
        }
    }

    public interface ClickListener {
        void onItemClicked(View view, int position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
