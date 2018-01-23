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
import android.widget.Toast;

import com.example.nguye.mvparchitecture.Constant;
import com.example.nguye.mvparchitecture.R;
import com.example.nguye.mvparchitecture.adapter.UserAdapter;
import com.example.nguye.mvparchitecture.data.User;
import com.example.nguye.mvparchitecture.userdetail.UserDetailActivity;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchUserContract.View, IClick {

    public static final String TAG = "SearchActivity";
    private EditText mEdtLogin;
    private EditText mEdtlimit;
    private Button mBtnSearch;
    private RecyclerView mRecyclerView;
    private ProgressDialog mDialog;
    private UserAdapter mUserAdapter;
    private List<User> mUsers;
    private SearchUserContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        events();
    }

    private void events() {
        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginName = mEdtLogin.getText().toString().trim();
                String limit = mEdtlimit.getText().toString().trim();
                if (loginName.isEmpty() || limit.isEmpty()) return;
                mPresenter.getUserList(loginName, Integer.parseInt(limit));
            }
        });
    }

    private void initView() {
        mEdtLogin = findViewById(R.id.edt_login);
        mEdtlimit = findViewById(R.id.edt_limit_number);
        mBtnSearch = findViewById(R.id.button_search);
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
        mUsers = userList;
        mUserAdapter = new UserAdapter(this, mUsers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mUserAdapter);
    }

    @Override
    public void onNoData() {
        if (mUsers != null) {
            mUsers.clear();
            mUserAdapter.notifyDataSetChanged();
        }
        Toast.makeText(this, "Không tìm thấy user này!", Toast.LENGTH_SHORT).show();
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
