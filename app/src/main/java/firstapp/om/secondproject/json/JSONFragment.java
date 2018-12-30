package firstapp.om.secondproject.json;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import firstapp.om.secondproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JSONFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JSONFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JSONFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //----------------------------

    private RecyclerView recyclerView;
    //JSON API
    //private,public
    private String URL_API="https://api.androidhive.info/json/movies.json";
    private String TITLE="title";
    private String IMAGE_URL="image";
    private String RATING="rating";
    private String RELEASE_YEAR="releaseYear";
    private JsonArrayRequest jsonArrayRequest;
    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private RequestQueue newRequest;
    private Movies movies;
    private ArrayList<Movies> moviesArrayList;
    private MyRecyclerAdapterJSON recyclerAdapterJSON;
//-----------------------------------------------
    private OnFragmentInteractionListener mListener;

    public JSONFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JSONFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JSONFragment newInstance(String param1, String param2) {
        JSONFragment fragment = new JSONFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_json, container, false);
        recyclerView=view.findViewById(R.id.json_recycler);
        moviesArrayList=new ArrayList<>();
        newRequest=Volley.newRequestQueue(getActivity());
        jsonArrayRequest=new JsonArrayRequest(URL_API, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
          //Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_LONG).show();
                try {

                    jsonArray=response;
                    for (int i=0;i<jsonArray.length();i++){
                        movies=new Movies();
                        jsonObject=jsonArray.getJSONObject(i);
                        movies.setTitle(jsonObject.getString(TITLE));
                        movies.setRating(jsonObject.getDouble(RATING));
                        movies.setReleaseYear(jsonObject.getInt(RELEASE_YEAR));
                        movies.setImageUrl(jsonObject.getString(IMAGE_URL));
                        moviesArrayList.add(movies);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RecyclerView.LayoutManager manager=
                        new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(manager);
                recyclerAdapterJSON=new MyRecyclerAdapterJSON(moviesArrayList,getActivity());
                recyclerView.setAdapter(recyclerAdapterJSON);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        newRequest.add(jsonArrayRequest);


       return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
