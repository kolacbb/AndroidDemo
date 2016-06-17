package io.github.kolacbb.androiddemo.AndroidHeros;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import io.github.kolacbb.androiddemo.AndroidHeros.CustomView.CustomViewExpandActivity;
import io.github.kolacbb.androiddemo.AndroidHeros.CustomView.CustomViewMergerActivity;
import io.github.kolacbb.androiddemo.AndroidHeros.CustomView.CustomViewNewActivity;
import io.github.kolacbb.androiddemo.R;
import io.github.kolacbb.androiddemo.util.ActivityItemDetails;
import io.github.kolacbb.androiddemo.util.CustomArrayAdapter;

public class CustomViewListActivity extends ListActivity {

    private static final ActivityItemDetails[] demos = {
            new ActivityItemDetails("CustomView-expand", "description", CustomViewExpandActivity.class),
            new ActivityItemDetails("CustomView-Merger", "merger", CustomViewMergerActivity.class),
            new ActivityItemDetails("CustomView-new", "custom view", CustomViewNewActivity.class)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_list);
        ListAdapter adapter = new CustomArrayAdapter(this.getApplicationContext(), demos);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        ActivityItemDetails details = (ActivityItemDetails) getListAdapter().getItem(position);
        startActivity(new Intent(this.getApplicationContext(), details.activityClass));
    }
}
