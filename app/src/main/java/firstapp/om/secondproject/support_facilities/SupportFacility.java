package firstapp.om.secondproject.support_facilities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import firstapp.om.secondproject.R;

public class SupportFacility extends AppCompatActivity {
     private ListView listView;
     private ArrayList<SupportItems> arrayList;
     private String[] supportItems;
     private int[] images;
     private SupportItems items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_facility);
        listView=findViewById(R.id.list);
        arrayList=new ArrayList<>();
        supportItems=getResources().getStringArray(R.array.support);
        images=new int[]{R.drawable.email,R.drawable.moodle,
                R.drawable.email,R.drawable.moodle};
        createContainer();
        CustomAdapter customAdapter=new CustomAdapter(arrayList,SupportFacility.this);
        listView.setAdapter(customAdapter);

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
