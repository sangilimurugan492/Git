package com.example.sangilimurugn.homepage;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class VideoFragment extends Fragment {

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_layout, container, false);
        context = getContext();
        ListView lvMovieName = (ListView) rootView.findViewById(R.id.lv_images);
        lvMovieName.setAdapter(new MovieAdapter(formMovieList()));

        return rootView;
    }

    private List<DropDown> formMovieList() {
        List<DropDown> dropdowList = new ArrayList<>();
        DropDown dropDown = new DropDown();
        dropDown.setName("Strange Weather, Isn't It?");
        dropDown.setDrawble(R.drawable.download_1);
        dropDown.setDescription("And You Will Know Us by the Trail of Dead");
        dropDown.setTime("15 HOURS AGO");
        dropdowList.add(dropDown);

        dropDown = new DropDown();
        dropDown.setName("When Your Heart Stops Beating");
        dropDown.setDescription("Some album titles are born great");
        dropDown.setTime("18 HOURS AGO");
        dropDown.setDrawble(R.drawable.download_2);
        dropdowList.add(dropDown);

        dropDown = new DropDown();
        dropDown.setName("Jumping the Tracks");
        dropDown.setDescription("Some achieve greatness and some have greatness thrust upon them. ");
        dropDown.setTime("20 HOURS AGO");
        dropDown.setDrawble(R.drawable.download_5);
        dropdowList.add(dropDown);

        dropDown = new DropDown();
        dropDown.setName("The Secret Of Elena's Tomb [EP]");
        dropDown.setDescription("Helping to drive album sales to triple platinum and beyond.");
        dropDown.setTime("22 HOURS AGO");
        dropDown.setDrawble(R.drawable.download_4);
        dropdowList.add(dropDown);

        dropDown = new DropDown();
        dropDown.setName("Tao of the Dead");
        dropDown.setDescription("The Beatles released a self titled double album with a white cover.");
        dropDown.setTime("1 DAY AGO");
        dropDown.setDrawble(R.drawable.download_5);
        dropdowList.add(dropDown);

        return dropdowList;
    }


    private class MovieAdapter extends BaseAdapter {

        List<DropDown> movieList;

        MovieAdapter(List<DropDown> movieList) {
            this.movieList = movieList;
        }

        @Override
        public int getCount() {
            return movieList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_row, parent, false);
                DropDown dropDown = movieList.get(position);
                ImageView ivMovie = (ImageView) convertView.findViewById(R.id.movie_image);
                TextView movieName = (TextView) convertView.findViewById(R.id.movie_name);
                TextView time = (TextView) convertView.findViewById(R.id.time);
                TextView discription = (TextView) convertView.findViewById(R.id.description);

                ivMovie.setImageResource(dropDown.getDrawble());
                movieName.setText(dropDown.getName());
                time.setText(dropDown.getTime());
                discription.setText(dropDown.getDescription());

            }
            return convertView;
        }
    }
}
