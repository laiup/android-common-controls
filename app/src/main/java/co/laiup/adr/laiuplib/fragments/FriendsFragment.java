package co.laiup.adr.laiuplib.fragments;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import co.laiup.adr.laiuplib.App;
import co.laiup.adr.laiuplib.Log.L;
import co.laiup.adr.laiuplib.R;
import co.laiup.adr.laiuplib.adapters.FriendsAdapter;
import co.laiup.adr.laiuplib.extras.Utils;
import co.laiup.adr.laiuplib.models.Friend;

public class FriendsFragment extends Fragment implements FriendsAdapter.ClickListener {

    // #constant
    public static final String SAVED_INSTANT_DATA = "friend_list";

    // #view component
    RecyclerView rvList;
    RecyclerView.LayoutManager layoutManager;
    FriendsAdapter adapter;
    ItemTouchHelper itemTouchHelper;

    // #data
    ArrayList<Friend> friends;

    // root view
    View root;

    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_friends, container, false);
        rvList = (RecyclerView) root.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        friends = new ArrayList<>();
        adapter = new FriendsAdapter(getContext(), friends);
        adapter.setClickListener(this);
        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(adapter);

        // check if data has already initiated.
        if (savedInstanceState == null) {
            initData();
        } else {
            ArrayList<Friend> savedData = savedInstanceState.getParcelableArrayList(SAVED_INSTANT_DATA);
            if (savedData != null) {
                friends.clear();
                for (Friend fr : savedData) {
                    friends.add(fr);
                }
                adapter.notifyDataSetChanged();
            } else {
                initData();
            }
        }

        ItemTouchHelper.Callback itemTouchCallback = new ItemTouchHelper.Callback() {
            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
                /*
                 * this makeFlag statement below let you can drag item up down, left to right ...
                 * above makeMovementFlags : you just can drag up and down.
                 */
                // return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.START | ItemTouchHelper.END);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                L.m("onMove");
                adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                L.m("onSwiped");
                if (direction == ItemTouchHelper.START || direction == ItemTouchHelper.LEFT) {
                    L.m("LEFT");
                    openContextMenu();
                } else {
                    L.m("RIGHT");
                }
                adapter.onItemDismiss(viewHolder.getAdapterPosition());
            }

            /*
             * Override method below for : add background color when swipe left or right, add icon to under item
             * Same to Inbox by Google
             */
            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                /*
                if (actionState != ItemTouchHelper.ACTION_STATE_SWIPE) {
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                } else {
                    View itemView = viewHolder.itemView;
                    Paint p = new Paint();
                    Bitmap icon;
                    if (dX > 0) {
                        icon = BitmapFactory.decodeResource(App.getAppContext().getResources(), R.drawable.ic_bin);
                        Bitmap resize = Bitmap.createScaledBitmap(icon, (int) Utils.convertDpToPx(getContext(), 32), (int) Utils.convertDpToPx(getContext(), 32), false);
                        // background color
                        p.setColor(ContextCompat.getColor(App.getAppContext(), R.color.green_800));
                        // Draw Rect with varying right side, equal to displacement dX

                        c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
                                (float) itemView.getBottom(), p);

                        // Set the image icon for Right swipe
                        float marginLeft = (float) itemView.getLeft() + Utils.convertDpToPx(App.getAppContext(), 16);

                        float marginTop = (float) itemView.getTop() + (((float) itemView.getBottom() - (float) itemView.getTop()) / 2 - icon.getHeight() / 2);
                        c.drawBitmap(resize, marginLeft, marginTop, p);
                    } else {
                        icon = BitmapFactory.decodeResource(App.getAppContext().getResources(), R.drawable.ic_bin);
                        Bitmap resize = Bitmap.createScaledBitmap(icon, (int) Utils.convertDpToPx(getContext(), 32), (int) Utils.convertDpToPx(getContext(), 32), false);
                        // set background for left swipe
                        p.setColor(ContextCompat.getColor(App.getAppContext(), R.color.orange_500));


                        // Draw Rect with varying left side, equal to the item's right side
                        // plus negative displacement dX
                        c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                                (float) itemView.getRight(), (float) itemView.getBottom(), p);

                        //Set the image icon for Left swipe
                        float marginLeft = (float) itemView.getRight() - Utils.convertDpToPx(App.getAppContext(), 16) - icon.getWidth();
                        float marginTop = (float) itemView.getTop() + ((float) itemView.getBottom() - (float) itemView.getTop() - icon.getHeight()) / 2;
                        c.drawBitmap(resize, marginLeft, marginTop, p);
                    }

                    // Fade out the view as it is swiped out of the parent's bounds
                    final float alpha = 1.0f - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
                    viewHolder.itemView.setAlpha(alpha);
                    viewHolder.itemView.setTranslationX(dX);

                }
                */
            }
        };
        itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rvList);
        return root;
    }

    /*
     * Sample Data
     */
    private void initData() {
        for (int i = 0; i < 10; i++) {
            friends.add(new Friend("" + i, "Ha", "Hoang", "Ha Hoang"));
        }
        adapter.notifyDataSetChanged();
    }

    /*
     * Custom menu popup
     * This will create a custom menu and set position to show it up somewhere on your screen
     */
    private void openContextMenu() {
        final Dialog popup = new Dialog(getContext(), R.style.DialogSlideAnim);
        popup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        popup.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        popup.setContentView(R.layout.context_menu_friends);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(popup.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        /*
        // This is for set position of popup view at anywhere you want on screen
        lp.gravity = Gravity.TOP | Gravity.END;
        lp.y = (int) Utils.convertDpToPx(getContext(), 48);
        lp.x = (int) Utils.convertDpToPx(getContext(), 0);
        */
        popup.getWindow().setAttributes(lp);

        // init dialog component
        TextView btnCancel = (TextView) popup.findViewById(R.id.tv_cancel);
        TextView btnArchived = (TextView) popup.findViewById(R.id.tv_archived);
        final EditText etArchived = (EditText) popup.findViewById(R.id.et_archived_name);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.s(v, "Cancel");
                popup.dismiss();
            }
        });

        btnArchived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etArchived.getText().toString().trim();
                if (name.equals("")) {
                    L.s(v, "Please enter name");
                } else {
                    L.s(root, "Archived : " + name);
                    popup.dismiss();
                }
            }
        });

        popup.show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(SAVED_INSTANT_DATA, friends);
        L.m("onSaveInstanceState");
    }

    @Override
    public void onPause() {
        super.onPause();
        L.m("onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        L.m("onResume");
    }

    @Override
    public void onItemClicked(View view, int position) {
        L.s(view, "Click on " + position);
    }
}
