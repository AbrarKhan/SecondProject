package firstapp.om.secondproject.firebase_database;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import firstapp.om.secondproject.R;
import firstapp.om.secondproject.updated.NavigationMainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirebaseAllBookFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirebaseAllBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirebaseAllBookFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView allBook;
    private ArrayList<Book> bookArrayList;
    private Book book;
    private MyRecyclerAdapterAllBooks recyclerAdapterAllBooks;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private OnFragmentInteractionListener mListener;

    public FirebaseAllBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirebaseAllBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirebaseAllBookFragment newInstance(String param1, String param2) {
        FirebaseAllBookFragment fragment = new FirebaseAllBookFragment();
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
        ((NavigationMainActivity)getActivity()).getSupportActionBar().hide();

        View view= inflater.inflate(R.layout.fragment_firebase_all_book, container, false);
         TextView textView=view.findViewById(R.id.header_title);
         textView.setText("All Books");
         allBook=view.findViewById(R.id.all_book);
         firebaseDatabase=FirebaseDatabase.getInstance();
         databaseReference=firebaseDatabase.getReference("book");
         bookArrayList=new ArrayList<>();
         databaseReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                          for(DataSnapshot snap:dataSnapshot.getChildren()){
                          Book books=snap.getValue(Book.class);
                          bookArrayList.add(books);

                 }
                  RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                  allBook.setLayoutManager(layoutManager);
                  recyclerAdapterAllBooks=new MyRecyclerAdapterAllBooks(bookArrayList,getActivity());
                  allBook.setAdapter(recyclerAdapterAllBooks);

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });
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
