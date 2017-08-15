package com.example.sun.demo.demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by sun on 17/8/15.
 */

public class E_RecycleViewActivity extends BaseActivity {
    @Bind(R.id.recycle_view)
    RecyclerView mRecycleView;

    @Override
    public void setContent() {
        setContentView(R.layout.e_recycle_view_activity);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {
        mRecycleView.setAdapter(new RecycleViewAdapter());
    }

    private static class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewHolder> {
        private DeviceInfo[] deviceInfos = DeviceInfo.values();

        @Override
        public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecycleViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(RecycleViewHolder holder, int position) {

            final DeviceInfo deviceInfo = deviceInfos[position];
            holder.name.setText(deviceInfo.name);
            holder.icon.setImageResource(deviceInfo.res);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(,deviceInfo.name,Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return deviceInfos.length;
        }
    }

    private static class RecycleViewHolder extends RecyclerView.ViewHolder {
        private final ImageView icon;
        private final TextView name;

//        public RecycleViewHolder(View itemView) {
//            super(itemView);
//            itemView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.e_recycle_view_item, itemView, false);
//            icon = (ImageView) itemView.findViewById(R.id.tv_recycle_view_item);
//            name = (TextView) itemView.findViewById(R.id.tv_name);
//        }

        public RecycleViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.e_recycle_view_item, parent, false));
            icon = (ImageView) itemView.findViewById(R.id.tv_recycle_view_item);
            name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
