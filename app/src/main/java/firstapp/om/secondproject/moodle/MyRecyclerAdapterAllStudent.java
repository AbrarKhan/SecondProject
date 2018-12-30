package firstapp.om.secondproject.moodle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import firstapp.om.secondproject.R;
import firstapp.om.secondproject.databases.Student;


public class MyRecyclerAdapterAllStudent extends RecyclerView.Adapter<MyRecyclerAdapterAllStudent.MyViewHolder> {
    private ArrayList<Student> dataSet;
    private Context mContext;
    private CardView cardView;

    
    
    public MyRecyclerAdapterAllStudent(ArrayList<Student> dataSet, Context mContext) {
        this.dataSet = dataSet;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.all_student_adapter,parent,false);
        //cardView=view.findViewById(R.id.card_view);
        return new MyViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            Student items=dataSet.get(position);
            holder.textView1.setText(items.getName());
            holder.textView2.setText(items.getUser_id());
            holder.textView3.setText(items.getPassword());
            }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView1,textView2,textView3;

        public MyViewHolder(View itemView) {
            super(itemView);
             textView1=itemView.findViewById(R.id.name);
             textView2=itemView.findViewById(R.id.for_id);
             textView3=itemView.findViewById(R.id.for_pass);
        }
    }

}
