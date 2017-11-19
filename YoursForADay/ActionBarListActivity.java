package com.example.ashleyridout.yoursforaday;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

//class to access listView and listAdapter
public abstract class ActionBarListActivity extends AppCompatActivity {

    /**
     * @intent action when item is clicked
     * @postconditions override method in activity will perform a function
     */
    private final class ListOnItemClickListener implements OnItemClickListener {

        public void onItemClick(AdapterView<?> lv, View v, int position, long id) {
            onListItemClick((ListView) lv, v, position, id);
        }
    }

    private ListView mListView;

    /**
     * @intent get the listView
     * @return listView to show data
     * @postconditions if there is no listView, this one is initiated
     */
    protected ListView getListView() {

        if (mListView == null) {
            initListView();
        }
        return mListView;
    }

    /**
     * @intent use listView id to find listView and set onClickListener
     * @postconditions listView is assigned to listViewID or error message is displayed
     */
    private void initListView() {
        mListView = (ListView) findViewById(getListViewId());
        if (mListView == null) {
            throw new RuntimeException(
                    "ListView cannot be null. Please set a valid ListViewId");
        }

        mListView.setOnItemClickListener(new ListOnItemClickListener());
    }

    /**
     * @intent get listViewId that will hold list data
     * @postconditions listViewId is obtainable
     * @return int of listViewId
     */
    protected abstract int getListViewId();

    /**
     * @intent set listAdapter to this listView
     * @postconditions listAdapter is assigned to this listView
     */
    protected void setListAdapter(ListAdapter adapter) {
        getListView().setAdapter(adapter);
    }

    /**
     * @intent empty method to override in activity when a list item is clicked
     * @postconditions listAdapter is assigned to this listView
     */
    protected void onListItemClick(ListView lv, View v, int position, long id) {

    }

    /**
     * @intent get the listAdapter
     * @return the listAdapter
     * @postconditions listAdapter is able to be obtained
     */
    protected ListAdapter getListAdapter() {
        ListAdapter adapter = getListView().getAdapter();
        if (adapter instanceof HeaderViewListAdapter) {
            return ((HeaderViewListAdapter) adapter).getWrappedAdapter();
        } else {
            return adapter;
        }
    }
}
