package firstapp.om.secondproject.firebase_database;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import firstapp.om.secondproject.R;
import firstapp.om.secondproject.databases.MyDatabaseHelperClass;
import firstapp.om.secondproject.updated.NavigationMainActivity;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirebaseNewBookFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirebaseNewBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirebaseNewBookFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button save,display;
    private EditText title,author,description;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Book mBook;
    private Source mSource;
    private String mTitle,mAuthor,mDescription;
    private ArrayList<Source> bookArrayList;
    private OnFragmentInteractionListener mListener;
    CircleImageView profile;
    private ImageView camera;
    private final int CAMERA_REQUEST=1;
    Bitmap bitmap;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    public FirebaseNewBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirebaseNewBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirebaseNewBookFragment newInstance(String param1, String param2) {
        FirebaseNewBookFragment fragment = new FirebaseNewBookFragment();
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
        ((NavigationMainActivity)getActivity()).setTitle("New Book");
        View view= inflater.inflate(R.layout.fragment_firebase_new_book, container, false);
        title=view.findViewById(R.id.title);
        author=view.findViewById(R.id.author);
        firebaseDatabase=FirebaseDatabase.getInstance();

        databaseReference=firebaseDatabase.getReference("book");

        //-------------------------
         firebaseStorage=FirebaseStorage.getInstance();


        //----------------------------------------------


         description=view.findViewById(R.id.description);
        save=view.findViewById(R.id.save);
        display=view.findViewById(R.id.display);
        profile=view.findViewById(R.id.profile);
        camera=view.findViewById(R.id.camera);
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        //String userid=user.get;
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST);
            }
        });
         save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(bitmap!=null){
                  final int rand= (int) Math.floor(Math.random()*20);
                   storageReference=firebaseStorage.getReference("images/"+rand);
                   ByteArrayOutputStream baos = new ByteArrayOutputStream();
                   bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                   byte[] data = baos.toByteArray();
                   UploadTask uploadTask = storageReference.putBytes(data);
                   Task<Uri> urlTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                       @Override
                       public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                           if (!task.isSuccessful()) {
                               throw task.getException();
                           }
                           else{
                         storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                             @Override
                             public void onSuccess(Uri uri) {
                                 Log.d("TAG",uri.toString());
                            mBook=new Book();
                            mBook.setmTitle(title.getText().toString().trim());
                            mBook.setmAuthor(author.getText().toString().trim());
                            mBook.setmDescription(description.getText().toString().trim());
                            mBook.setmImageUrl(uri.toString());
                            String key=databaseReference.push().getKey();
                            databaseReference.child(key).setValue(mBook);
                             }
                         });

                           }

                           return storageReference.getDownloadUrl();
                       }
                   }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                       @Override
                       public void onComplete(@NonNull Task<Uri> task) {
                           if (task.isSuccessful()) {
                               Uri downloadUri = task.getResult();
                           } else {
                               // Handle failures
                               // ...
                           }
                       }
                   });
   }
               else{
                   Toast.makeText(getActivity(),"Please Capture or Select Image",Toast.LENGTH_LONG).show();
               }

           }
       });
       display.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               FragmentTransaction fragmentTransaction=
                       getActivity().getSupportFragmentManager().beginTransaction();
               Fragment fragment=FirebaseAllBookFragment.newInstance(null,null);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMERA_REQUEST && resultCode==RESULT_OK){
            Bundle bundle=data.getExtras();
            bitmap=(Bitmap) bundle.get("data");
            profile.setImageBitmap(bitmap);
        }

    }
}
