package com.aipai.ljw.gameblackmarket.Activity;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.SwitchPreference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.aipai.ljw.gameblackmarket.R;
import com.aipai.ljw.gameblackmarket.View.AttachRecyclerViewFloatingButton;

import java.util.ArrayList;
import java.util.List;

import static com.aipai.ljw.gameblackmarket.R.menu.menu_main;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

    List<String> listdata;
    Toolbar toolbar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         //我是程序员二  我修改了代码
        //我是程序员一  我在弄主分支  与子分支的实验

        ////我是程序员一  我再次弄主分支  与子分支的实验
////我是程序员一  我再次弄主分支  与子分支的实验  第三次
        toolbar = (Toolbar) findViewById(R.id.tooldar);
        initToolbar(toolbar);
        if (null!=toolbar){


            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openOrCloseDrawer();
                }
            });

        }


        listdata=new ArrayList<>();
        for (int d=0;d<18;d++){
            listdata.add("lihao"+d);
        }



        initDrawerLayout();
        initRecyclerview();

        AttachRecyclerViewFloatingButton attachRecyclerViewFloatingButton= (AttachRecyclerViewFloatingButton) findViewById(R.id.fab);
        attachRecyclerViewFloatingButton.attachRecyclerView(recyclerView);

    }








    //打开或关闭 侧滑菜单
    private void openOrCloseDrawer() {
        if (daDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            daDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            daDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    RecyclerView recyclerView;
    private void initRecyclerview() {


        recyclerView= (RecyclerView) findViewById(R.id.recycerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new RecyclerView.Adapter() {


            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                Context context = parent.getContext();
                final View view = LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false);
                return new MyViewholder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                MyViewholder myViewholder = (MyViewholder) holder;
                myViewholder.text.setText(listdata.get(position));
            }

            @Override
            public int getItemCount() {
                return listdata.size();
            }

            class MyViewholder extends RecyclerView.ViewHolder {

                TextView text;

                public MyViewholder(View itemView) {
                    super(itemView);
                    text = (TextView) itemView.findViewById(R.id.text);

                }
            }
        });

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    ActionBarDrawerToggle  actionBarDrawerToggle;
    DrawerLayout daDrawerLayout;
    private void initDrawerLayout() {
        daDrawerLayout= (DrawerLayout) findViewById(R.id.drawerlayout);
        ListView listView= (ListView) findViewById(R.id.listview);

        final LayoutInflater inflater=LayoutInflater.from(getApplicationContext());
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return listdata.size();
            }

            @Override
            public Object getItem(int position) {
                return listdata.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView==null){
                    convertView=inflater.inflate(R.layout.listview_item,parent,false);
                }

                TextView text= (TextView) convertView.findViewById(R.id.text);
                text.setText((String)getItem(position));

                return convertView;
            }
        });

        actionBarDrawerToggle =new ActionBarDrawerToggle
                (this,daDrawerLayout,0,0){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        daDrawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    private SearchView searchView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);



        //toolbar  还有更多的使用  参考actionbar
        //搜索框的操作
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);//获取搜索框 view
        //searchItem.expandActionView();
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        ComponentName componentName = getComponentName();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName));
        searchView.setQueryHint("搜素");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
//                recyclerAdapter.getFilter().filter(s);
                return true;
            }
        });
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
//                recyclerAdapter.setUpFactor();
//                refreshLayout.setEnabled(false);//设置下拉刷新不可用
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
//                refreshLayout.setEnabled(true);
                return true;
            }
        });
        return true;
    }


//    下拉刷新的监听
    @Override
    public void onRefresh() {

    }
}
