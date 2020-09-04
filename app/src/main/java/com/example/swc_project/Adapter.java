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

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {//RecylerViewAdapter
    private ArrayList<RecyItem> mData ;//mPersons
//    Private ArrayList<Post> mData ;//mPersons
    private LayoutInflater mInflate;
    private Context mContext;

    public Adapter(Context context, ArrayList<RecyItem> persons){
//        public Adapter(Context context, ArrayList<Post> persons){
        this.mContext = context;
        this.mInflate = LayoutInflater.from(context);
        this.mData = persons;

    }
    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_p, parent, false) ;
        ViewHolder vh = new ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {

        RecyItem item = mData.get(position) ;

//        holder.textView1.setText(item.getTitle()) ;
//        holder.textView2.setText(item.getbody()) ;
//위아래 둘다됨
        holder.textView1.setText(mData.get(position).getTitle());
        holder.textView2.setText(mData.get(position).getbody());

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return (mData != null ? mData.size() : 0) ;
        //위에꺼가 안전함 이걸로해야됨 그래야 오류잡기 편함
//        return mData.size();
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    Adapter(ArrayList<RecyItem> list) {
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
            textView1 = itemView.findViewById(R.id.postTitle);
            textView2 = itemView.findViewById(R.id.postBody);
//            textView3 = itemView.findViewById(R.id.postNumStars);
//            imageView1 = itemView.findViewById(R.id.star);
        }
    }

}
