package com.smartdigital.medicine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

//ViewModel class for managing user data in a singleton manner
//create an instance of UserDataManager when app starts
//call the methods below to access/modify/save user data
public class UserDataManager
{
    private final ArrayList <UserMedicine> drugs = new ArrayList<>();
    private final DrugsAdapter adapter;

    //constructor of the view model
    //add anything in the constructor to be initialized when the view model is created
    public UserDataManager()
    {
        getUserData();
        adapter = new DrugsAdapter();
    }

    //call this method to retrieve the location list from a local file or a database
    public void getUserData()
    {
        //todo: get saved medicines here
    }

    //call this method to save the list to a local file or a database
    public void updateUserData()
    {
        adapter.notifyDataSetChanged();
        //todo: update current save of user data
    }

    //call this method to add a location to the list
    public void add(UserMedicine med)
    {
        drugs.add(med);
        updateUserData();
        //todo: scroll the recyclerview to the new item added
    }

    //call this method to remove saved location from the list
    public void remove(UserMedicine med)
    {
        drugs.remove(med);
        updateUserData();
    }

    //return the RecyclerView adapter
    public DrugsAdapter getAdapter()
    {
        return this.adapter;
    }

    //generate dummy data for test purpose
    public void generateDummyData()
    {
        for (int i = 1; i <= 10; i++)
        {
            drugs.add(new UserMedicine("Medicine " + i));
        }
    }




    //custom adapter for the list
    private class DrugsAdapter extends Adapter
    {
        @NonNull
        @NotNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_list_item_layout, parent, false);
            return new LocationViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position)
        {
            UserMedicine m = drugs.get(position);
            LocationViewHolder locationViewHolder = (LocationViewHolder)holder;
            locationViewHolder.name.setText(m.getName());
        }

        @Override
        public int getItemCount() {
            return drugs.size();
        }

        //custom ViewHolder for displaying each item in the list
        private class LocationViewHolder extends RecyclerView.ViewHolder
        {

            private final TextView name;
            public LocationViewHolder(View itemView)
            {
                super(itemView);
                name = itemView.findViewById(R.id.text);

            }
        }
    }
}
