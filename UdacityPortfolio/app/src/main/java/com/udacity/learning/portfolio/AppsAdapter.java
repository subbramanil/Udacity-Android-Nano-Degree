package com.udacity.learning.portfolio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by Subbu on 5/20/16.
 */
public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.AppViewHolder> {

    private static final String TAG = AppsAdapter.class.getSimpleName();
    private Context context = null;
    private LayoutInflater inflater;
    private AppItemClickListener itemClickListener;
    private List<String> appsNameList;

    //region Constructor
    public AppsAdapter(Context c, AppItemClickListener listener, List<String> arrayOfApps) {
        this.context = c;
        this.itemClickListener = listener;
        inflater = LayoutInflater.from(context);
        this.appsNameList = arrayOfApps;
    }
    //endregion

    //region Lifecycle methods

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.button_list_item, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        onBindViewHolder(holder, appsNameList.get(position));
    }

    public void onBindViewHolder(AppViewHolder holder, final String appName) {
        holder.appBtn.setText(appName);
    }

    @Override
    public int getItemCount() {
        return appsNameList.size();
    }

    //endregion

    //Classes & Interfaces declaration

    public interface AppItemClickListener {
        void onAppBtnClick(View view, int position);
    }

    public class AppViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button appBtn;

        public AppViewHolder(View itemView) {
            super(itemView);
            appBtn = (Button) itemView.findViewById(R.id.appBtn);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onAppBtnClick(view, getLayoutPosition());
            }
        }
    }

    //endregion
}
