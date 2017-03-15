package com.example.aaron.lab16aaronohare;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aaron.lab16aaronohare.model.News;
import com.example.aaron.lab16aaronohare.parsers.XMLParser;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    private TextView output;
    private ProgressBar pBar;
    private List<MyTask> myTasks;
    private static final String PHOTOS_BASE_URL ="http://10.0.2.2/OOPDCA2/images/";


    ArrayList<News> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

//        output = (TextView) findViewById(R.id.output);
//        pBar = (ProgressBar) findViewById(R.id.pBar);

        myTasks = new ArrayList<>();

        MyTask task = new MyTask();
        task.execute("http://10.0.2.2/OOPDCA2/myFeed.rss");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    protected void updateDisplay() {
       // output.append(message + "\n");
//        if(newsList != null){
//            for(News news : newsList){
//                output.append(news.getTitle() + "\n" + news.getDescreption() + "\n" + news.getLink() +"\n \n \n");
//            }
//        }
        NewsAdapter adapter = new NewsAdapter(this, R.layout.news_item, newsList);
        setListAdapter(adapter);
    }

    private class MyTask extends AsyncTask<String, String, ArrayList<News>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (myTasks.size() == 1) {
                pBar.setVisibility(View.VISIBLE);
            }
         //updateDisplay("Starting Task");
        }


        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            //get xml data
            String content = HttpManager.getData(strings[0]);

            newsList = XMLParser.parseFeed(content);

            for (News news : newsList){
                try{
                    String imageUrl = PHOTOS_BASE_URL + news.getEnlosureURL();
                    InputStream in = (InputStream) new URL(imageUrl).getContent();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    news.setBitmap(bitmap);
                    in.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
            return newsList;

         }

//        protected void onProgressUpdate(String... strings) {
//            super.onProgressUpdate(strings);
//            for (int i = 0; i < strings.length; i++) {
//             //   updateDisplay("Working with " + strings[i]);
//            }
//        }

        @Override
        protected void onPostExecute(ArrayList <News> news) {
            super.onPostExecute(news);
            //newsList = XMLParser.parseFeed(s);
            updateDisplay();

            myTasks.remove(this);
//            if (myTasks.size() == 0) {
////                pBar.setVisibility(View.INVISIBLE);
//            }
        }
    }
}
