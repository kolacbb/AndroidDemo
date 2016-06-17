package io.github.kolacbb.androiddemo.util;

/**
 * Created by Kola on 2016/6/17.
 */
public class ActivityItemDetails {
    public final String title;
    public final String description;
    public final Class<? extends android.app.Activity> activityClass;

    public ActivityItemDetails(String title, String description,
                       Class<? extends android.app.Activity> activityClass) {
        super();
        this.title = title;
        this.description = description;
        this.activityClass = activityClass;
    }
}
