package firstapp.om.secondproject.moodle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import firstapp.om.secondproject.R;
import firstapp.om.secondproject.databases.MyDatabaseHelperClass;
import firstapp.om.secondproject.databases.Student;
import firstapp.om.secondproject.updated.NavigationMainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private  Button already_register,register;
    private EditText name,user_id,password;
    private MyDatabaseHelperClass helperClass;
    private String fName,mUser,mPass;
    private Student student;
    private FragmentTransaction fragmentTransaction;
    private long aLong;
    private OnFragmentInteractionListener mListener;
    private  Fragment fragment;
    public RegistrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationFragment newInstance(String param1, String param2) {
        RegistrationFragment fragment = new RegistrationFragment();
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

        View view =inflater.inflate(R.layout.fragment_registration, container, false);
        fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();

       //----------------------------------
        name=view.findViewById(R.id.name);
        user_id=view.findViewById(R.id.userId);
        password=view.findViewById(R.id.password);
        register=view.findViewById(R.id.register);
        already_register=view.findViewById(R.id.already_register);
        //------------------------------------------------------


        helperClass=new MyDatabaseHelperClass(getActivity());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fName=name.getText().toString();
                mUser=user_id.getText().toString();
                mPass=password.getText().toString();
                student=new Student();
                student.setName(fName);
                student.setUser_id(mUser);
                student.setPassword(mPass);
                aLong=helperClass.insertStudent(student);
                if (aLong>0){
                    Toast.makeText(getActivity(),"Registration Successfull !!",Toast.LENGTH_LONG).show();
                    fragment=MoodleLoginFragment.newInstance("VLG (Moodle)",null);
                    fragmentTransaction.replace(R.id.main_container,fragment);
                    fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
                    fragmentTransaction.commit();
                }
            }
        });

        already_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment=MoodleLoginFragment.newInstance("VLG (Moodle)",null);
                fragmentTransaction.replace(R.id.main_container,fragment);
                fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
                fragmentTransaction.commit();
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
