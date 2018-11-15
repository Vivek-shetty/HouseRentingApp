package com.example.hp.ajentrent;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mview;
    CardView ParenetLayout;

    public ViewHolder(View itemView) {
        super(itemView);

        mview=itemView;

//        //item click
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mClickListener.onItemClick(view, (int) getItemId());
//            }
//        });
//
//        //item long click
//        itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                mClickListener.onItemLongClick(view,getAdapterPosition());
//                return true;
//            }
//        });

        }


    //set deyails in recyclerview row
    public void setDetails(Context ctx,String Location,int Amount,String House_Type,String image)
    {

        TextView location=mview.findViewById(R.id.location);
        TextView Amount1 =mview.findViewById(R.id.labelAmount);
        TextView HouseType=mview.findViewById(R.id.house_type);
        ImageView mimageView=mview.findViewById(R.id.imageView);
        ParenetLayout = mview.findViewById(R.id.parentLayout);

        //set data to views
        location.setText(String.valueOf(Location));
        Amount1.setText(String.valueOf(Amount));
        HouseType.setText(String.valueOf(House_Type));

        Picasso.get().load(image).into(mimageView);

    }
}


//    private ViewHolder.ClickListener mClickListener;
//    //interface to send call backs
//
//    public interface ClickListener{
//        void onItemClick(View view,int position);
//        void onItemLongClick(View view, int position);
//
//    }
//
//    public void setOnClickListener(ViewHolder.ClickListener clickListener){
//        mClickListener=clickListener;
//
//    }
