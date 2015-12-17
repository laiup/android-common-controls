package co.laiup.adr.laiuplib.interfaces;

/**
 * Project android-common-controls
 * Created by Ha on 11/18/2015.
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
