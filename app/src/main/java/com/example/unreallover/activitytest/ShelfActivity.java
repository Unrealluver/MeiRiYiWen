package com.example.unreallover.activitytest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.LruCache;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.unreallover.activitytest.R.drawable.i;


public class ShelfActivity extends AppCompatActivity {
    public ShelfActivity()  {
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shelf_layout);
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(bookList);
        recyclerView.setAdapter(adapter);


            new Thread(new Runnable() {
                        public void run () {
                        try {
                             Book[] books = {
                                    new Book("Apple", R.drawable.q),
                                    new Book("Banana", R.drawable.w),
                                    new Book("Orange", R.drawable.e),
                                    new Book("Watermelon", R.drawable.r),
                                    new Book("Pear", R.drawable.t),
                                    new Book("Grape", R.drawable.y),
                                    new Book("Pineapple", R.drawable.u),
                                    new Book("Strawberry",R.drawable. i),
                                    new Book("Cherry", R.drawable.o),
                                    new Book("Mango", R.drawable.p),
                                    new Book("Cherry", R.drawable.o),
                                    new Book("Mango", R.drawable.p)
                            };
                            org.jsoup.nodes.Document doc = Jsoup.connect("http://book.meiriyiwen.com/").get();
                            Elements pic = doc.select("li");
                            //final String Pic = pic.get(i).select("a").select("img").attr("src");
                            Elements title = doc.select("div.book-name");
                            for (int i = 0; i < 12; i++) {
                                books[i]= title.get(i).select("a").attr("title");}
                            //Url = title.select("a").attr("href");
                            Elements author = doc.select("div.book-author");
                          // String Author = author.get(i).text();
                            Log.i("QAQ", "QAQ");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

            }).start();



        //final String content="http://book.meiriyiwen.com";

    }



private void initFruits() {
        Book[] books = {
                new Book("Apple", R.drawable.q),
                new Book("Banana", R.drawable.w),
                new Book("Orange", R.drawable.e),
                new Book("Watermelon", R.drawable.r),
                new Book("Pear", R.drawable.t),
                new Book("Grape", R.drawable.y),
                new Book("Pineapple", R.drawable.u),
                new Book("Strawberry", R.drawable.i),
                new Book("Cherry", R.drawable.o),
                new Book("Mango", R.drawable.p),
                new Book("Cherry", R.drawable.o),
                new Book("Mango", R.drawable.p)
        };

        bookList.clear();
        for (int i = 0; i < 12; i++) {
            Random random = new Random();
            int index = random.nextInt(books.length);
            bookList.add(books[index]);
        }
    }


    //private DrawerLayout mDrawerLayout;

    private List<Book> bookList = new ArrayList<>();
    private FruitAdapter adapter;




        }
