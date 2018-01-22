package com.example.nguye.mvparchitecture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nguye.mvparchitecture.R;
import com.example.nguye.mvparchitecture.data.User;
import com.example.nguye.mvparchitecture.searchuser.IClick;

import java.util.List;

/**
 * Created by nguye on 16/01/2018.
 */

public class UserAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<User> mUsers;
    private IClick mIClick;

    public UserAdapter(Context context, List<User> users) {
        mContext = context;
        mUsers = users;
        mIClick = (IClick) context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_user, parent,
                false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserViewHolder userViewHolder = (UserViewHolder) holder;
        userViewHolder.fillData(mUsers.get(position));
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.valueOf(v.getTag().toString());
                mIClick.recyclerItemClick(mUsers.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void updateData(List<User> users) {
        if (users == null) return;
        mUsers.addAll(users);
        notifyDataSetChanged();
    }

    private class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewLogin;
        private TextView mTextViewId;

        public UserViewHolder(View itemView) {
            super(itemView);
            mTextViewId = itemView.findViewById(R.id.text_view_id);
            mTextViewLogin = itemView.findViewById(R.id.text_view_login);
        }

        public void fillData(User user) {
            mTextViewId.setText(String.valueOf(user.getId()));
            mTextViewLogin.setText(user.getLogin());
        }
    }
}
