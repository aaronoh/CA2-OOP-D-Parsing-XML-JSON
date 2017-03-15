package com.example.aaron.lab16aaronohare;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aaron.lab16aaronohare.model.News;

import java.util.List;

/**
 * Created by aaron on 01/03/2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {


        private Context context;

        private List<News> newsList;

        public NewsAdapter(Context context, int resource, List<News> objects) {
            super(context, resource, objects);
            this.context = context;
            this.newsList = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.news_item, parent, false);

            //Display title in TextView widget,
            // position is the position in the list
            News news = newsList.get(position);

            // R.id.newsItemTextView is the textView in news_item.xml
            TextView tv = (TextView) view.findViewById(R.id.newsItemTextView);
            tv.setText(news.getEnlosureURL());

            ImageView image = (ImageView) view.findViewById(R.id.newsItemImageView);
            image.setImageBitmap(news.getBitmap());

            return view;
        }

    }

