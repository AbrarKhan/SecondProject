package firstapp.om.secondproject.support_facilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import firstapp.om.secondproject.R;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private ArrayList<SupportItems> dataSet;
    private Context mContext;

    public MyRecyclerAdapter(ArrayList<SupportItems> dataSet, Context mContext) {
        this.dataSet = dataSet;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.list_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            SupportItems items=dataSet.get(position);
            holder.textView.setText(items.getName());
            holder.circleImageView.setImageResource(items.getImageView());

            }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CircleImageView circleImageView;
        public MyViewHolder(View itemView) {
            super(itemView);
             textView=itemView.findViewById(R.id.name);
             circleImageView=itemView.findViewById(R.id.item_info);
        }
    }
}
