package firstapp.om.secondproject.academic_faculties;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import firstapp.om.secondproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcademicFragment extends Fragment {
     private ListView listView;
     private String[] academicItems;
    public AcademicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_academic, container, false);
       listView=view.findViewById(R.id.list);
       academicItems=getResources().getStringArray(R.array.academic);
       ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,academicItems);
       listView.setAdapter(adapter);
       return view;
    }

}
