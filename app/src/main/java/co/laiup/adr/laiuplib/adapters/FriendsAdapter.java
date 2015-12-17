package co.laiup.adr.laiuplib.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Collections;

import co.laiup.adr.laiuplib.Log.L;
import co.laiup.adr.laiuplib.R;
import co.laiup.adr.laiuplib.interfaces.ItemTouchHelperAdapter;
import co.laiup.adr.laiuplib.models.Friend;
import co.laiup.adr.laiuplib.models.Story;

/**
 * Project LaiupLib
 * Created by Ha on 10/7/2015.
 */
public class FriendsAdapter extends RecyclerView.Adapter implements ItemTouchHelperAdapter {
    // #1 : define view type if your list have several kinds of view
    // #2 : some necessary variable
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Friend> data;

    // #click listener
    private ClickListener clickListener;

    // #3 : constructor
    public FriendsAdapter(Context context, ArrayList<Friend> data) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
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
        View view = inflater.inflate(R.layout.item_friend, parent, false);
        return new VHItem(view);
    }

    /*
     * @function : this method will bind data into view (created from onCreateViewHolder method)
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int position) {
        VHItem holder = (VHItem) vHolder;
        Friend item = data.get(position);
        holder.tvPostTime.setText("--" + item.getId());
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

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(data, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(data, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public class VHItem extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RoundedImageView ivAvatar;
        private TextView tvNickname, tvPostTime, tvChat;
        private LinearLayout main;


        public VHItem(View view) {
            super(view);
            main = (LinearLayout) view.findViewById(R.id.main);
            ivAvatar = (RoundedImageView) view.findViewById(R.id.iv_avatar);
            tvNickname = (TextView) view.findViewById(R.id.tv_nickname);
            tvPostTime = (TextView) view.findViewById(R.id.tv_post_time);
            tvChat = (TextView) view.findViewById(R.id.tv_chat);
            main.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
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
