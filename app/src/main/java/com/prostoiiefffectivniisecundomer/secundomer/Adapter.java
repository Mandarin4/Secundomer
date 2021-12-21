package com.prostoiiefffectivniisecundomer.secundomer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context context;
    private List<ListItem> mArray;

    public Adapter(Context context, List<ListItem> list) {
        this.context = context;
        this.mArray = list;

    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.setData(mArray.get(position).getTime1());
        holder.text1.setText("#" + Integer.toString(position+1));
        holder.text2.setText(mArray.get(position).getTime1());
        holder.text3.setText("+"+mArray.get(position).getTime2());
    }

    @Override
    public int getItemCount() {
        return mArray.size();
    }

//    public void updateAdapter(List<ListItem> newList){
//        mArray.clear();
//        mArray.addAll(newList);
//        notifyDataSetChanged();
//    }

    class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        private TextView text1, text2, text3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
            text3 = itemView.findViewById(R.id.text3);

        }
//        public void setData(String title){
//            text1.setText(title);
//        }

        @Override
        public void onClick(View view) {
        }
    }
}
