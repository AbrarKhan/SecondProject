package firstapp.om.secondproject.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import firstapp.om.secondproject.R;
import firstapp.om.secondproject.json.Movies;

public class MyRecyclerAdapterTest extends RecyclerView.Adapter<MyRecyclerAdapterTest.MyViewHolder> {
    private ArrayList<Books> dataSet;
    private Context mContext;
    private CardView cardView;

    private OnAdapterItemClick onClick;
    public interface OnAdapterItemClick{
        void onItemClick(int position);
    }
    public void newMethod(OnAdapterItemClick onClick){
        this.onClick=onClick;
    }
    public MyRecyclerAdapterTest(ArrayList<Books> dataSet, Context mContext) {
        this.dataSet = dataSet;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.test_adapter,parent,false);
        cardView=view.findViewById(R.id.card_view);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Books items = dataSet.get(position);
        holder.textView1.setText(items.getAuthor());
        holder.textView2.setText(items.getDescription());
        holder.textView3.setText(items.getName());
        holder.textView4.setText(items.getTitle());
        Picasso.with(mContext).load(items.getImage()).into(holder.circleImageView);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView1,textView2,textView3,textView4;
        CircleImageView circleImageView;
        public MyViewHolder(View itemView) {
            super(itemView);
             textView1=itemView.findViewById(R.id.title);
             textView2=itemView.findViewById(R.id.rating);
             textView3=itemView.findViewById(R.id.release_year);
            textView4=itemView.findViewById(R.id.release_year_test);
            circleImageView=itemView.findViewById(R.id.item_info);
        }
    }

}
