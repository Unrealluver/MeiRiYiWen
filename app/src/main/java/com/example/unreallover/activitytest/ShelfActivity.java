package com.example.unreallover.activitytest;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ShelfActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shelf_layout);
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }
    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++)
        {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }



    private DrawerLayout mDrawerLayout;
    private Fruit[] fruits = {
            new Fruit("Apple", R.drawable.q),
            new Fruit("Banana",R.drawable.w),
            new Fruit("Orange", R.drawable.e),
            new Fruit("Watermelon", R.drawable.r),
            new Fruit("Pear", R.drawable.t),
            new Fruit("Grape", R.drawable.y),
            new Fruit("Pineapple", R.drawable.u),
            new Fruit("Strawberry",R.drawable.i),
            new Fruit("Cherry", R.drawable.o),
            new Fruit("Mango", R.drawable.p)
    };
    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;
}