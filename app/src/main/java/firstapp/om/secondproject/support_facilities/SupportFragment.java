package firstapp.om.secondproject.support_facilities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import firstapp.om.secondproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupportFragment extends Fragment {
    private ListView listView;
    private ArrayList<SupportItems> arrayList;
    private String[] supportItems;
    private int[] images;
    private SupportItems items;
    public SupportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_support, container, false);
        listView=view.findViewById(R.id.list);
        arrayList=new ArrayList<>();
        supportItems=getResources().getStringArray(R.array.support);
        images=new int[]{R.drawable.email,R.drawable.moodle,
                R.drawable.email,R.drawable.moodle};
        createContainer();
        CustomAdapter customAdapter=new CustomAdapter(arrayList,getActivity());
        listView.setAdapter(customAdapter);
       return view;
    }
    public void createContainer(){
        for (int i=0;i<images.length;i++){
            items=new SupportItems();
            items.setName(supportItems[i]);
            items.setImageView(images[i]);
            arrayList.add(items);
        }
    }
}
