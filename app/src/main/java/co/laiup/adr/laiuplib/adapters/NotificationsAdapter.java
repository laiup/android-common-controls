package co.laiup.adr.laiuplib.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.zip.Inflater;

import co.laiup.adr.laiuplib.Log.L;
import co.laiup.adr.laiuplib.R;
import co.laiup.adr.laiuplib.models.Notification;

/**
 * Project android-common-controls
 * Created by Ha on 11/24/2015.
 */
public class NotificationsAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Notification> data;
    private LayoutInflater inflater;
    private DismissActionModeListener dismissActionModeListener;

    // list for tracking choice
    private ArrayList<Boolean> tracking;

    public NotificationsAdapter(Context context, ArrayList<Notification> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_notification, parent, false);
        return new VHItem(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        initTrackingList();
        holder.itemView.setSelected(tracking.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VHItem extends RecyclerView.ViewHolder {

        public VHItem(View view) {
            super(view);

        }
    }

    /*
     * Init tracking list base on size of input data
     */
    public void initTrackingList() {
        if (tracking == null) {
            tracking = new ArrayList<>();
            int i;
            for (i = 0; i < data.size(); i++) {
                tracking.add(false);
            }
        }
    }

    /*
     * Reset all item to unselected state
     */
    public void unselectedAllItem() {
        if (tracking != null) {
            int i, size = data.size();
            for (i = 0; i < size; i++) {
                if (tracking.get(i)) {
                    tracking.set(i, false);
                    notifyItemChanged(i);
                }
            }
        }
    }

    /*
     * Set selected / unselected for item
     */
    public void setSelectedState(int position) {
        L.m("State:" + tracking.get(position));
        tracking.set(position, !tracking.get(position));
        L.m("State After:" + tracking.get(position));
        notifyItemChanged(position);
        if(isUnselectedAll()) {
            dismissActionModeListener.onUnselectedAll();
        }
    }

    /*
     * Check if all item is in unselected state
     */
    public boolean isUnselectedAll() {
        for(Boolean isSelected : tracking) {
            if(isSelected) {
                return false;
            }
        }
        return true;
    }

    public interface DismissActionModeListener {
        void onUnselectedAll();
    }

    public void setDismissActionModeListener(DismissActionModeListener dismissActionModeListener) {
        this.dismissActionModeListener = dismissActionModeListener;
    }
}
