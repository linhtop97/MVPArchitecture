package com.example.nguye.mvparchitecture.searchuser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nguye.mvparchitecture.Constant;
import com.example.nguye.mvparchitecture.R;
import com.example.nguye.mvparchitecture.adapter.UserAdapter;
import com.example.nguye.mvparchitecture.model.User;
import com.example.nguye.mvparchitecture.model.UserHelper;
import com.example.nguye.mvparchitecture.userdetail.UserDetailActivity;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements ListUserView {

    public static final String TAG = "SearchUserActivity";
    private EditText edtLogin;
    private EditText edtLimit;
    private Button btnSearch;
    private RecyclerView mRecyclerView;
    private ProgressDialog mDialog;
    private List<User> mUsers;
    //
    private OnLoadUserResultPresenterImpl mOnLoadUserResultPresenter;
    private UserHelper mUserHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        events();
    }

    private void events() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginName = edtLogin.getText().toString().trim();
                String limit = edtLimit.getText().toString().trim();
                if (loginName.isEmpty() || limit.isEmpty()) return;
                mDialog.show();
                mUserHelper = new UserHelper();
                mOnLoadUserResultPresenter =
                        new OnLoadUserResultPresenterImpl(SearchActivity.this, mUserHelper);
                mOnLoadUserResultPresenter.getData(loginName, limit);
            }
        });
    }

    private void initView() {
        edtLogin = findViewById(R.id.edt_login);
        edtLimit = findViewById(R.id.edt_limit_number);
        btnSearch = findViewById(R.id.button_search);
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Searching...");
    }

    @Override
    public void displayUserList(List<User> users) {
        mDialog.dismiss();
        mRecyclerView = findViewById(R.id.rv_user_list);
        mUsers = users;
        UserAdapter adapter = new UserAdapter(this, mUsers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void recyclerItemClick(int position) {
        Intent intent = new Intent(SearchActivity.this, UserDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.EXTRA_USERS, mUsers.get(position));
        intent.putExtra(Constant.EXTRA_USERS, bundle);
        startActivity(intent);
    }
}
