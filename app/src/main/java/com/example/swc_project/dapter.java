package com.example.swc_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class dapter extends RecyclerView.Adapter<dapter.ViewHolder> {//RecylerViewAdapter
    private ArrayList<ComItem> mData ;//mPersons
    private LayoutInflater mInflate;
    private Context mContext;//전역변수 선언

    public dapter(Context context, ArrayList<ComItem> persons){
        this.mContext = context;
        this.mInflate = LayoutInflater.from(context);
        this.mData = persons;

    }
    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;//이거 선언
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_post_detail, parent, false) ;
        ViewHolder vh = new ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(dapter.ViewHolder holder, int position) {

        ComItem item = mData.get(position) ;

        holder.textView1.setText(item.getNickname()) ;
        holder.textView2.setText(item.getComment()) ;
//위아래 둘다됨
//        holder.textView1.setText(mData.get(position).getNickname());
//        holder.textView2.setText(mData.get(position).getComment());


    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return (mData != null ? mData.size() : 0) ;
        //위에꺼가 안전함 이걸로해야됨 그래야 오류잡기 편함
//        return mData.size();
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    dapter(ArrayList<ComItem> list) {
//        Adapter(ArrayList<Post> list) {
        mData = list ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;
        //        TextView textView3;
        ImageView imageView1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.pd_tv1);
            textView2 = itemView.findViewById(R.id.pd_tv2);
//            textView3 = itemView.findViewById(R.id.postNumStars);
//            imageView1 = itemView.findViewById(R.id.star);
        }
    }

}
