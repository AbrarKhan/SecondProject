package firstapp.om.secondproject.firebase_database;

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


public class MyRecyclerAdapterAllBooks extends RecyclerView.Adapter<MyRecyclerAdapterAllBooks.MyViewHolder> {
    private ArrayList<Book> dataSet;
    private Context mContext;
    private  Book items;


    public MyRecyclerAdapterAllBooks(ArrayList<Book> dataSet, Context mContext) {
        this.dataSet = dataSet;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.book_adapter,parent,false);

        return new MyViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
           items=dataSet.get(position);
            holder.textView1.setText(items.getmAuthor());
            holder.textView2.setText(items.getmDescription());
            holder.textView3.setText(items.getmTitle());
            Picasso.with(mContext).load(items.getmImageUrl()).into(holder.profile);
            }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView1,textView2,textView3;
        CircleImageView profile;

        public MyViewHolder(View itemView) {
            super(itemView);
             textView1=itemView.findViewById(R.id.title);
             textView2=itemView.findViewById(R.id.author);
             textView3=itemView.findViewById(R.id.description);
             profile=itemView.findViewById(R.id.profile);

        }
    }

}
