package firstapp.om.secondproject.support_facilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import firstapp.om.secondproject.R;

public class CustomAdapter extends ArrayAdapter<SupportItems> {

    private ArrayList<SupportItems> dataSet;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        ImageView info;
    }

    public CustomAdapter(ArrayList<SupportItems> data, Context context) {
        super(context,R.layout.list_view, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SupportItems dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            viewHolder=new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_view, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);
            viewHolder.txtName.setText(dataModel.getName());
            viewHolder.info.setImageResource(dataModel.getImageView());
            String str="";
            }



        // Return the completed view to render on screen
        return convertView;
    }
}