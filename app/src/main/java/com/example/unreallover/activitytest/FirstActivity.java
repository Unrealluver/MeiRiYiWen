package com.example.unreallover.activitytest;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class FirstActivity extends AppCompatActivity// implements View.OnClickListener  不屏蔽这一句就会出现Error:(21, 8) 错误: FirstActivity不是抽象的, 并且未覆盖OnClickListener中的抽象方法onClick(View)
{
    TextView responseText;
    TextView responseTitle;
    TextView responseAuthor;
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_list);

        }
        responseTitle = (TextView) findViewById(R.id.response_title);
        responseAuthor = (TextView) findViewById(R.id.response_author);
        responseText = (TextView) findViewById(R.id.response_text);
        sendRequestWithHttpURLConnection();
        FloatingActionButton random = (FloatingActionButton)
                findViewById(R.id.random);
        random.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.random) {
                    sendRequestWithHttpURLConnection();
                }
                Toast.makeText(FirstActivity.this, "正在随机……", Toast.LENGTH_SHORT).show();
            }
        });

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.random);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this, "FAB clicked", Toast.LENGTH_
                        SHORT).show();
            }
        });*/
    }

    private static final String TAG = "FirstActivity";
    private void sendRequestWithHttpURLConnection() {
        Log.e(TAG, "sendRequestWithHttpURLConnection: " );
// 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: "+Thread.currentThread() );
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://meiriyiwen.com/random/iphone");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    // 下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    showResponse(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    public void showResponse(final String response) {
        /*开始提取标题*/
        int t1=response.indexOf("title",0);
        int t2=response.indexOf("-", t1);
        int t3=response.indexOf("-", t2+1);
        int t4=response.indexOf("<p>", t3);
        int t5=response.lastIndexOf("</p>");//括号里再加上  ，t4  会有神奇的事情发生
        final String title1=response.substring(t1+6,t2);
        final String title=title1+"";
        final String author1=response.substring(t2+1,t3);
        final String author=" \n"+author1+" \n";
        final String text3=response.substring(t4+3,t5);
        final String text2=text3.replaceAll("<p>","");
        final String text1=text2.replaceAll("</p>","\n\n        ");
        final String text="        "+text1;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
// 在这里进行 UI 操作，将结果显示到界面上
                responseText.setText(text);
                responseAuthor.setText(author);
                responseTitle.setText(title);
            }
        });
    }



    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.collect:
                Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).
                        show();
                break;
            case R.id.download:
                Toast.makeText(this, "正在缓存", Toast.LENGTH_SHORT).
                        show();
                break;
            case R.id.setting_item:
                Intent intent = new Intent(FirstActivity.this,ShelfActivity.class);
                startActivity(intent);
                break;
            case R.id.quit_item:
                Toast.makeText(this,"正在退出…",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return true;
    }
}




