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
import com.example.nguye.mvparchitecture.data.User;
import com.example.nguye.mvparchitecture.userdetail.UserDetailActivity;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchUserContract.View, IClick {

    public static final String TAG = "SearchUserActivity";
    private EditText edtLogin;
    private EditText edtLimit;
    private Button btnSearch;
    private RecyclerView mRecyclerView;
    private ProgressDialog mDialog;
    private SearchUserContract.Presenter mPresenter;

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
                mPresenter.getUserList(loginName, Integer.parseInt(limit));
            }
        });
    }

    private void initView() {
        edtLogin = findViewById(R.id.edt_login);
        edtLimit = findViewById(R.id.edt_limit_number);
        btnSearch = findViewById(R.id.button_search);
        mPresenter = new SearchUserPresenter(this);
        mDialog = new ProgressDialog(this);
        mDialog.setMessage(getString(R.string.searching));
    }

    @Override
    public void recyclerItemClick(User user) {
        Intent intent = new Intent(SearchActivity.this, UserDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.EXTRA_USERS, user);
        intent.putExtra(Constant.EXTRA_USERS, bundle);
        startActivity(intent);
    }

    @Override
    public void onLoadUserSuccess(List<User> userList) {
        mDialog.dismiss();
        mRecyclerView = findViewById(R.id.rv_user_list);
        UserAdapter adapter = new UserAdapter(this, userList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onNoData() {

    }

    @Override
    public void showDiaLog() {
        mDialog.show();
    }

    @Override
    public void dismissDialog() {
        mDialog.dismiss();
    }

    @Override
    public void setPresenter(SearchUserContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
