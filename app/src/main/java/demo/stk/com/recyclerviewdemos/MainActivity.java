package demo.stk.com.recyclerviewdemos;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> list;
    MyAdapter myAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //listview的管理器
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //gridview管理器
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        //瀑布流管理器
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        //设置瀑布流Item
//        SpacesItemDecoration decoration = new SpacesItemDecoration(20);
//        recyclerView.addItemDecoration(decoration);


        initData();
        myAdapter = new MyAdapter(list, this);
        recyclerView.setAdapter(myAdapter);
        /**
         * 下拉刷新
         * */
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.remove(sum);
                        swipeRefreshLayout.setRefreshing(false);
                        myAdapter.notifyDataSetChanged();
                    }
                }, 2000);
            }
        });
    }

    protected void initData() {
        list = new ArrayList<String>();
        for (int i = 'A'; i <= 'Z'; i++) {
            list.add("" + (char) i);
        }
    }

}
