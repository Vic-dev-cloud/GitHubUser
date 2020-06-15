package com.example.githubuser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private Context mContext;
    private JSONArray mUserList;

    public UserListAdapter(Context context, JSONArray userList) {
        mContext = context;
        mUserList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_details_item, parent, false);
        UserListAdapter.ViewHolder holder = new UserListAdapter.ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        UserInformation response = new UserInformation();
        try {
            response = GSONUtil.getInstance().fromJson(mUserList.getString(position), UserInformation.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Picasso.get().load(response.getAvatarUrl()).into(holder.mImageViewAvatar);
        holder.mTextViewLogin.setText(position + 1 + ". " + response.getLogin());
        if (response.getSiteAdmin()) {
            holder.mTextViewSiteAdmin.setVisibility(View.VISIBLE);
        }

        holder.mTextViewID.setText("ID : " + response.getID());
        holder.mTextViewNodeID.setText("Node ID : " + response.getNodeID());
        holder.mTextViewGravatarID.setText("Gravatar ID :" + response.getGravatarID());
        holder.mTextViewUrl.setText("Url : " + response.getUrl());
        holder.mTextViewHtmlUrl.setText("HtmlUrl : " + response.getHtmlUrl());
        holder.mTextViewFollowersUrl.setText("Followers Url : " + response.getFollowersUrl());
        holder.mTextViewFollowingUrl.setText("Following Url : " + response.getFollowingUrl());
        holder.mTextViewGistsUrl.setText("Gists Url : " + response.getGistsUrl());
        holder.mTextViewStarredUrl.setText("Starred Url : " + response.getStarredUrl());
        holder.mTextViewSubscriptionsUrl.setText("Subscriptions Url : " + response.getSubscriptionsUrl());
        holder.mTextViewOrganizationsUrl.setText("Organizations Url : " + response.getOrganizationsUrl());
        holder.mTextViewReposUrl.setText("Repos Url : " + response.getReposUrl());
        holder.mTextViewEventsUrl.setText("Events Url : " + response.getEventsUrl());
        holder.mTextViewReceivedEventsUrl.setText("ReceivedEvents Url : " + response.getReceivedEventsUrl());
        holder.mTextViewType.setText("Type : " + response.getType());
    }

    @Override
    public int getItemCount() {
        return mUserList.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageViewAvatar;
        TextView mTextViewLogin, mTextViewSiteAdmin;
        LinearLayout mLinearLayoutUserDetail;

        TextView mTextViewID, mTextViewNodeID, mTextViewGravatarID, mTextViewUrl, mTextViewHtmlUrl, mTextViewFollowersUrl, mTextViewFollowingUrl,
                mTextViewGistsUrl, mTextViewStarredUrl, mTextViewSubscriptionsUrl, mTextViewOrganizationsUrl, mTextViewReposUrl, mTextViewEventsUrl,
                mTextViewReceivedEventsUrl, mTextViewType;

        ViewHolder(View itemView) {
            super(itemView);
            mImageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);
            mTextViewLogin = itemView.findViewById(R.id.textViewLogin);
            mTextViewSiteAdmin = itemView.findViewById(R.id.textViewSiteAdmin);
            mLinearLayoutUserDetail = itemView.findViewById(R.id.linearLayoutUserDetail);

            mTextViewID = itemView.findViewById(R.id.textViewID);
            mTextViewNodeID = itemView.findViewById(R.id.textViewNodeID);
            mTextViewGravatarID = itemView.findViewById(R.id.textViewGravatarID);
            mTextViewUrl = itemView.findViewById(R.id.textViewUrl);
            mTextViewHtmlUrl = itemView.findViewById(R.id.textViewHtmlUrl);
            mTextViewFollowersUrl = itemView.findViewById(R.id.textViewFollowersUrl);
            mTextViewFollowingUrl = itemView.findViewById(R.id.textViewFollowingUrl);
            mTextViewGistsUrl = itemView.findViewById(R.id.textViewGistsUrl);
            mTextViewStarredUrl = itemView.findViewById(R.id.textViewStarredUrl);
            mTextViewSubscriptionsUrl = itemView.findViewById(R.id.textViewSubscriptionsUrl);
            mTextViewOrganizationsUrl = itemView.findViewById(R.id.textViewOrganizationsUrl);
            mTextViewReposUrl = itemView.findViewById(R.id.textViewReposUrl);
            mTextViewEventsUrl = itemView.findViewById(R.id.textViewEventsUrl);
            mTextViewReceivedEventsUrl = itemView.findViewById(R.id.textViewReceivedEventsUrl);
            mTextViewType = itemView.findViewById(R.id.textViewType);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mLinearLayoutUserDetail.getVisibility() == View.GONE) {
                        mLinearLayoutUserDetail.setVisibility(View.VISIBLE);
                    } else {
                        mLinearLayoutUserDetail.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
}

