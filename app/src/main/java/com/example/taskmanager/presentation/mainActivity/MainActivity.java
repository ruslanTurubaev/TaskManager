package com.example.taskmanager.presentation.mainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.data.utils.Constants;
import com.example.taskmanager.R;
import com.example.taskmanager.presentation.amendOrCreateTaskActivity.AmendOrCreateTaskActivity;
import com.example.taskmanager.presentation.arrayAdapters.MainArrayAdapter;
import com.example.taskmanager.presentation.arrayAdapters.MainListItems;
import com.example.taskmanager.presentation.loginActivity.LoginActivity;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Button tasksButton;
    private TextView title;
    private ListView listView;
    private MainViewModel mainViewModel;
    private ArrayList<MainListItems> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.root_layout);

        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(30);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        listView = findViewById(R.id.main_listView);
        tasksButton = findViewById(R.id.tasks_button);
        title = findViewById(R.id.tv_title);
        MainArrayAdapter arrayAdapter = new MainArrayAdapter(this, R.layout.main_list_item, items);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getItemsLiveData().observe(this, mainListItems -> {
            items.clear();
            items.addAll(mainListItems);
            arrayAdapter.notifyDataSetChanged();
        });

        mainViewModel.getIsMyTasks().observe(this, isMyTask -> {
            if(isMyTask){
                tasksButton.setText(R.string.all_tasks);
                title.setText(R.string.my_tasks);
            }
            else {
                tasksButton.setText(R.string.my_tasks);
                title.setText(R.string.all_tasks);
            }
        });
    }

    public void onClickSignOut(View view){
        mainViewModel.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void onClickNewTask(View view){
        Intent intent = new Intent(MainActivity.this, AmendOrCreateTaskActivity.class);
        startActivity(intent);
    }

    public void onClickAllTasks(View view){
        mainViewModel.setSettings();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MainListItems item = items.get(position);
        Intent intent = new Intent(MainActivity.this, AmendOrCreateTaskActivity.class);
        intent.putExtra(Constants.ID, item.getID());
        startActivity(intent);
    }
}