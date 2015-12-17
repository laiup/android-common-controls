package co.laiup.adr.laiuplib.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import co.laiup.adr.laiuplib.Log.L;
import co.laiup.adr.laiuplib.R;
import co.laiup.adr.laiuplib.adapters.NewsFeedAdapter;
import co.laiup.adr.laiuplib.models.Story;
import co.laiup.adr.laiuplib.networks.VolleySingleton;

public class NewsFeedFragment extends Fragment implements NewsFeedAdapter.ClickListener {
    // #declare variable
    // #for request API
    VolleySingleton volleySingleton;
    RequestQueue requestQueue;
    ArrayList<Story> data;

    // #for view component
    RecyclerView rvList;
    NewsFeedAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    // #root view
    View root;

    public NewsFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_news_feed, container, false);
        // #init necessary components
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(new Story("Story " + i));
        }
        rvList = (RecyclerView) root.findViewById(R.id.recycler_view);
        adapter = new NewsFeedAdapter(getContext(), data);
        adapter.setClickListener(this);
        layoutManager = new LinearLayoutManager(getContext());
        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(adapter);


//        makeRequest();
        return root;
    }

    /*
     * #function: create request from API #API_xxx
     * #input :
     */
    private void makeRequest() {
        /*
         * #instruction: create new custom request with parameters
         * #1. Priority : determine priority of this request compare with the others in the same queue
         * #2. Request Method : POST | GET | ...
         * #3. API URL : url to your API
         * #4. Map<String, String> : parameters (if this is POST request)
         * #5. Response Listener : return JSONObject of API response
         * #6. Response Error : return Volley Error object
         *--> remember to
         * #add request to queue
         */
//        VolleyCustomRequest request = new VolleyCustomRequest(
//                Request.Priority.HIGH, //#1
//                Request.Method.GET, // #2
//                Constants.API, // #3
//                null, // #4
//                new Response.Listener<JSONObject>() { // #5
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // check if response # null
//                        if(response != null) {
//                            // call JSONParser to parse this result into Object you desired
//                            ArrayList<Story> tempList = JSONParser.storyParser(response);
//                            if(tempList != null) {
//                                copyArrayList(tempList);
//                                adapter.notifyDataSetChanged();
//                            }
//                        }
//                    }
//                },
//                new Response.ErrorListener() { // #6
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        L.m(error.toString());
//                    }
//                });
//        // #add request to queue
//        requestQueue.add(request);
    }

    /*
     * #function: import response list into adapter's data
     * #input: temp list contain response data
     */
    public void copyArrayList(ArrayList<Story> tempList) {
        for(int i = 0; i < tempList.size(); i++)
        {
            data.add(tempList.get(i));
        }
    }

    @Override
    public void onItemClicked(View view, int position) {
        switch (view.getId()) {
            case R.id.iv_like:
                L.s(view, "Like");
                break;
            case R.id.iv_comment:
                L.s(view, "Comment");
                break;
            case R.id.iv_share:
                L.s(view, "Share");
                break;
            case R.id.iv_dislike:
                L.s(view, "Dislike");
                break;
            case R.id.iv_option:
                L.s(view, "Option");
                break;
            default:
                L.s(view, "Item");
                break;
        }
    }
}
