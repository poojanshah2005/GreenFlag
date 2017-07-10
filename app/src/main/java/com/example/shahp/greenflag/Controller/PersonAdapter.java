package com.example.shahp.greenflag.Controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shahp.greenflag.Model.Person;
import com.example.shahp.greenflag.R;

import java.util.ArrayList;

/**
 * Created by Poojan on 08/07/2017.
 */

class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {
    ArrayList<Person> people;

    public PersonAdapter(ArrayList<Person> people) {
        this.people = people;
    }

    @Override
    public PersonAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonAdapter.MyViewHolder holder, int position) {
        if(people.get(position).getEmail() != null) holder.tvEmail.setText("Email: " + people.get(position).getEmail());
        if(people.get(position).getGender()!= null)holder.tvGender.setText("Gender: " + people.get(position).getGender());
        if(people.get(position).getName() != null)holder.tvName.setText("Name: " + people.get(position).getName());
        if(people.get(position).getCoutry() != null)holder.tvCountry.setText("Country: " + people.get(position).getCoutry());
        if(people.get(position).getAddress() != null)holder.tvAddress.setText("Address: " + people.get(position).getAddress());
        if(people.get(position).getDate() != null)holder.tvDate.setText("Date of Birth: " + people.get(position).getDate());
        if(people.get(position).getAge() != null)holder.tvAge.setText("Age: " + people.get(position).getAge());
        byte[] byteArray = people.get(position).getPhoto();
        if(byteArray!= null) {
            Bitmap photo = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            if (photo != null) {
                holder.im_row_photo.setImageBitmap(photo);
            }
        }

    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvEmail;
        TextView tvGender;
        TextView tvName;
        TextView tvCountry;
        TextView tvAddress;
        TextView tvDate;
        TextView tvAge;
        ImageView im_row_photo;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvEmail = (TextView)itemView.findViewById(R.id.tvEmail);
            tvGender = (TextView)itemView.findViewById(R.id.tvGender);
            tvName = (TextView)itemView.findViewById(R.id.tvName);
            tvCountry = (TextView)itemView.findViewById(R.id.tvCountry);
            tvAddress = (TextView)itemView.findViewById(R.id.tvAddress);
            tvDate = (TextView)itemView.findViewById(R.id.tvDate);
            tvAge = (TextView)itemView.findViewById(R.id.tvAge);
            im_row_photo = (ImageView)itemView.findViewById(R.id.im_row_photo);

        }

    }


}
