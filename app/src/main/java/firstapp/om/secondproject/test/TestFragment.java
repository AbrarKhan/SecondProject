package firstapp.om.secondproject.test;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import firstapp.om.secondproject.R;
import firstapp.om.secondproject.json.Movies;
import firstapp.om.secondproject.json.MyRecyclerAdapterJSON;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
//------------------------------

private String URL_API="https://newsapi.org/v2/everything?q=trump&apiKey=b1d199d1dd544c378445aaeb6bd74802";
    private String ARTICLES="articles";
    private String NAME="name";
    private String AUTHOR="author";
    private String TITLE="title";
    private String DESCRIPTION="description";
    private String IMAGE="urlToImage";
    private String SOURCE="source";
    private JsonArrayRequest jsonArrayRequest;
    private JsonObjectRequest jsonObjectRequest;
    private JSONArray jsonArray;
    private JSONObject jsonObject1,jsonObject2;
    private RequestQueue newRequest;
    private Contact contact;
    private Books books;
    private Phone phone;
    private ArrayList<Books> moviesArrayList;
    private MyRecyclerAdapterTest recyclerAdapterJSON;
    private RecyclerView recyclerView;
    //----------------------------------
    private OnFragmentInteractionListener mListener;

    public TestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment newInstance(String param1, String param2) {
        TestFragment fragment = new TestFragment();
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
        View view= inflater.inflate(R.layout.fragment_test, container, false);
        recyclerView=view.findViewById(R.id.test);
        moviesArrayList=new ArrayList<>();
        newRequest=Volley.newRequestQueue(getActivity());
        jsonObjectRequest=new JsonObjectRequest(URL_API, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    jsonArray= response.getJSONArray(ARTICLES);
                    for (int i=0;i<jsonArray.length();i++){
                        jsonObject1=jsonArray.getJSONObject(i);
                        jsonObject2=jsonObject1.getJSONObject(SOURCE);
                        books=new Books();
                        books.setName(jsonObject2.getString(NAME));
                        books.setAuthor(jsonObject1.getString(AUTHOR));
                        books.setDescription(jsonObject1.getString(DESCRIPTION));
                        books.setTitle(jsonObject1.getString(TITLE));
                        books.setImage(jsonObject1.getString(IMAGE));
                        moviesArrayList.add(books);

                    }
                    RecyclerView.LayoutManager manager=
                    new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                   recyclerView.setLayoutManager(manager);
                   recyclerAdapterJSON=new MyRecyclerAdapterTest(moviesArrayList,getActivity());
                   recyclerView.setAdapter(recyclerAdapterJSON);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
//        jsonObjectRequest=new JsonObjectRequest(URL_API,null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                //Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_LONG).show();
//                try {
//
//                    jsonArray=response;
//                    for (int i=0;i<jsonArray.length();i++){
//                        contact=new Contact();
//                        phone=new Phone();
//                        jsonObject=jsonArray.getJSONObject(i);
//                        contact.setName(jsonObject.getString(NAME));
//                        contact.setEmail(jsonObject.getString(EMAIL));
//                        JSONObject obj=jsonObject.getJSONObject(PHONE);
//                           phone.setHome(obj.getString(HOME));
//                           phone.setMobile(obj.getString(MOBILE));
//                        contact.setPhone(phone);
//                        moviesArrayList.add(contact);
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                RecyclerView.LayoutManager manager=
//                        new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
//                recyclerView.setLayoutManager(manager);
//                recyclerAdapterJSON=new MyRecyclerAdapterTest(moviesArrayList,getActivity());
//                recyclerView.setAdapter(recyclerAdapterJSON);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
        newRequest.add(jsonObjectRequest);



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
