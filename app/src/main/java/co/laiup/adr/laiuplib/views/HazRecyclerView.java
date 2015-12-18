package co.laiup.adr.laiuplib.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hha07 on 12/18/2015.
 */
public class HazRecyclerView extends RecyclerView {
    public HazRecyclerView(Context context) {
        super(context);
    }

    public HazRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HazRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void addOnLayoutChangeListener(OnLayoutChangeListener listener) {
        super.addOnLayoutChangeListener(listener);
    }
}
