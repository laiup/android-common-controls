package co.laiup.adr.laiuplib.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.zip.Inflater;

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
}
