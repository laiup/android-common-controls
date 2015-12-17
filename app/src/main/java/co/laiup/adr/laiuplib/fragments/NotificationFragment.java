package co.laiup.adr.laiuplib.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import co.laiup.adr.laiuplib.Log.L;
import co.laiup.adr.laiuplib.R;
import co.laiup.adr.laiuplib.adapters.NotificationsAdapter;
import co.laiup.adr.laiuplib.models.Friend;
import co.laiup.adr.laiuplib.models.Notification;

public class NotificationFragment extends Fragment {

    // #root view
    private View root;

    // #components
    RecyclerView rvList;
    NotificationsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Notification> notifications;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_notification, container, false);

        rvList = (RecyclerView) root.findViewById(R.id.recycler_view);
        notifications = new ArrayList<>();
        adapter = new NotificationsAdapter(getContext(), notifications);
        layoutManager = new LinearLayoutManager(getContext());
        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(adapter);

        // init data for recycler view
        initData();

        // toolbar overlay for action mode (long click on recycler item)
        final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                /*
                 * #Create menu for action mode
                 * #Inflate menu from menu (xml). You can  custom your menu in that xml file
                 */
                View customActionMode = LayoutInflater.from(getActivity()).inflate(R.layout.action_mode, new LinearLayout(getContext()), false);
//                mode.setCustomView(customActionMode);
                getActivity().getMenuInflater().inflate(R.menu.menu_action_mode, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_mode_share:
                        L.s(root, "Share");
                        mode.finish();
                        break;
                    case R.id.action_mode_save:
                        L.s(root, "Saved");
                        break;
                    default:
                        L.s(root, "Action Mode");
                        break;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                L.s(root, "Destroyed");
            }
        };

        // click - long click listener for recycler view item
        rvList.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rvList, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                L.s(root, "OnClick " + position);
            }

            @Override
            public void onLongClick(View view, int position) {
                L.m("OnLongClick " + position);
                ((AppCompatActivity) getActivity()).startSupportActionMode(actionModeCallback);
            }
        }));

        // return view
        return root;
    }

    /*
     * Sample Data
     */
    private void initData() {
        for (int i = 0; i < 10; i++) {
            notifications.add(new Notification("" + i, "Maria's just commented in your post"));
        }
        adapter.notifyDataSetChanged();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    /*
     * #This class' used for custom OnItemTouchListener of RecyclerView
     * #Implement click | longClick
     * #Because : addOnItemTouchListener for RecyclerView can't get position of item
     * #Explain : touch event start :
     * # Go to onInterceptTouchEvent -> gestureDetector.onTouchEvent(e) in if statement
     *     If gestureDetector return TRUE : (that means onSingleTapUp return TRUE) -> call onClick()
     *     If gestureDetector return FALSE : (that means onSingleTapUp return FALSE) -> and TouchEvent go to onLongPress -> call onLongClick()
     */
    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        RecyclerTouchListener(Context context, final RecyclerView rv, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);
                    // #find element under touch position
                    View child = rv.findChildViewUnder(e.getX(), e.getY());
                    // #check if under touch position exist any view component
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, rv.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            // #find element under touch position
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            // #check if under touch position exist any view component
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
