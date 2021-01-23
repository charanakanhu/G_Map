package com.kanhucharan.apimap.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kanhucharan.apimap.Model.ProfileModel;
import com.kanhucharan.apimap.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder> {

    private List<ProfileModel> moviesList;
    private List<ProfileModel> exampleListFull;
    Context context;
    int c=0;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id,name,emp_id,cat,cat_id,dec,cont_no,address;
        CircleImageView imageView;
        CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            id =  view.findViewById(R.id.id);
            name =  view.findViewById(R.id.name);
            emp_id =  view.findViewById(R.id.emp_id);
            cat =  view.findViewById(R.id.cat);
            cat_id =  view.findViewById(R.id.catid);
            dec =  view.findViewById(R.id.dec);
            cont_no =  view.findViewById(R.id.cont);
            address =  view.findViewById(R.id.add);
            imageView=view.findViewById(R.id.profile_image);
            cardView=view.findViewById(R.id.c11);
        }
    }


    public ProfileAdapter(List<ProfileModel> moviesList, Context context ) {
        this.moviesList = moviesList;
        this.context = context;
        exampleListFull = new ArrayList<>(moviesList);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ProfileModel profileModel = moviesList.get(position);
        holder.id.setText(profileModel.getId());
        holder.name.setText(profileModel.getName());
        holder.cat.setText(profileModel.getCat());
        holder.address.setText(profileModel.getAddress());
        holder.cat_id.setText(profileModel.getCat_id());
        holder.dec.setText(profileModel.getDec());
        holder.cont_no.setText(profileModel.getCont_no());
        holder.emp_id.setText(profileModel.getEmp_id());
        Picasso.get().load(profileModel.getEmp_id()).fit().centerCrop()
                .placeholder(R.drawable.ic_baseline_person_24)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
