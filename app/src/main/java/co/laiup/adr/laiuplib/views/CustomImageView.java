package co.laiup.adr.laiuplib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import co.laiup.adr.laiuplib.Log.L;
import co.laiup.adr.laiuplib.R;

/**
 * Project android-common-controls
 * Created by Ha on 11/17/2015.
 */
public class CustomImageView extends ImageView{

    public CustomImageView(Context context) {
        super(context);
        final Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.button_scale);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                L.m("View dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                L.m("View dispatchTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                L.m("View dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                L.m("View dispatchTouchEvent ACTION_CANCEL");
                break;
        }
        boolean result = false;
        result = super.dispatchTouchEvent(ev);
        L.m("View dispatchTouchEvent RETURN : " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                L.m("View onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                L.m("View onTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                L.m("View onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                L.m("View onTouchEvent ACTION_CANCEL");
                break;
        }
        boolean result = true;
        L.m("View onTouchEvent RETURN : " + result);
        return result;
    }
}
