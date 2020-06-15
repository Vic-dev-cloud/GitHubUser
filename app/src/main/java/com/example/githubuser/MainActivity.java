package com.example.githubuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerviewUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getGitHubUserList();
    }

    private void initView() {
        mRecyclerviewUserList = findViewById(R.id.recyclerviewUserList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerviewUserList.setLayoutManager(linearLayoutManager);
    }

    private void getGitHubUserList() {
        HttpRequest request = new HttpRequest(this, new HttpRequest.ResultCallback() {
            @Override
            public void onSuccess(JSONArray res) {
                UserListAdapter userListAdapter = new UserListAdapter(MainActivity.this, res);
                mRecyclerviewUserList.setAdapter(userListAdapter);
            }

            @Override
            public void onError() {
            }
        });
        request.execute(Utilities.URL_GITHUB_USER_LIST);
    }
}