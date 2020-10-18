package com.example.swc_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment4_Adapter extends RecyclerView.Adapter<Fragment4_Adapter.MyViewHolder> {
    private ArrayList<f4_ap_item> data = null;
    private LayoutInflater mInflate;
    private Context mContext;

    public Fragment4_Adapter(Context context, ArrayList<f4_ap_item> list){
        this.data = list;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.fragment4_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final String check1 = data.get(position).getSvcNm();
        final String check2 = data.get(position).getSvcCts();

        holder.text1.setText(data.get(position).getSvcNm());
        holder.text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Fragment4_detail.class);
                intent.putExtra("title",check1);
                intent.putExtra("title",check2);

                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (data != null ? data.size() : 0) ;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView text1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.text1);
        }
    }
}
