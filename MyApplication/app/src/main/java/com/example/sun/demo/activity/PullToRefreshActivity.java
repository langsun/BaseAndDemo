package com.example.sun.demo.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by sun on 16/6/30.
 */
public class PullToRefreshActivity extends BaseActivity {
    @Bind(R.id.listView)
    PullToRefreshListView mListView;
    private List<String> list;
    private listAdapter adapter;

    @Override
    public void setContent() {
        setContentView(R.layout.activity_next);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {
        mCenterTitle.setText("下拉刷新");
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("a=" + i);
        }
        adapter = new listAdapter(this);
        mListView.setAdapter(adapter);
//        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
//                    @Override
//                    public void onRefresh(
//                            PullToRefreshBase<ListView> refreshView) {
//                        // Do work to refresh the list here.
//                        new GetDataTask().execute();
//                    }
//                });
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                // Do work to refresh the list here.
                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new GetDataTask().execute();
            }
        });
    }

    class GetDataTask extends AsyncTask<Void, Void, String[]> {
        @Override
        protected void onPostExecute(String[] result) {
            mListView.onRefreshComplete();//结束刷新
            super.onPostExecute(result);
        }

        @Override
        protected String[] doInBackground(Void... params) {
            try {
                Thread.sleep(2000);//控制刷新两秒
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
    }

    private class listAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        public listAdapter(Context context) {
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View contentView, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if (contentView == null) {
                viewHolder = new ViewHolder();
                contentView = inflater.inflate(R.layout.list_item, null);
                viewHolder.item = (TextView) contentView.findViewById(R.id.item);
                contentView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) contentView.getTag();
            }
            viewHolder.item.setText(list.get(position));
            return contentView;
        }
    }

    public class ViewHolder {
        public TextView item;

    }


}
