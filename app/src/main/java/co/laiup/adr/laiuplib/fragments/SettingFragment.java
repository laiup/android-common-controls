package co.laiup.adr.laiuplib.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import co.laiup.adr.laiuplib.Log.L;
import co.laiup.adr.laiuplib.R;

public class SettingFragment extends Fragment {

    // #root view
    private View root;

    // #components
    ImageView test;
    GestureDetector gestureDetector;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_setting, container, false);

        test = (ImageView) root.findViewById(R.id.iv_like);
        final Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.button_scale);

        gestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                L.m("onDown");
                test.startAnimation(anim);
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                L.m("onShowPress");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                L.m("onSingleTapUp");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                L.m("onScroll");
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                L.m("onLongPress");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                L.m("onFling");
                return true;
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        test.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });

        // return view
        return root;
    }


}
