package com.ezbuy.netlog;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.ezbuy.netlog.data.ChuckContentProvider;
import com.ezbuy.netlog.data.HttpTransaction;
import com.ezbuy.netlog.data.LocalCupboard;
import com.ezbuy.netlog.support.NotificationHelper;
import com.ezbuy.netlog.support.Period;
import com.ezbuy.netlog.support.RetentionManager;

public class ChuckUtil {

    private Period DEFAULT_RETENTION;
    private NotificationHelper notificationHelper;
    private RetentionManager retentionManager;
    private boolean showNotification;
    private Context context;

    public ChuckUtil(Context context) {
        DEFAULT_RETENTION = Period.ONE_WEEK;
        notificationHelper = new NotificationHelper(context);
        retentionManager = new RetentionManager(context, DEFAULT_RETENTION);
        showNotification = true;
        this.context = context;
    }

    public Uri create(HttpTransaction transaction) {
        ContentValues values = LocalCupboard.getInstance().withEntity(HttpTransaction.class).toContentValues(transaction);
        Uri uri = context.getContentResolver().insert(ChuckContentProvider.TRANSACTION_URI, values);
        transaction.setId(Long.valueOf(uri.getLastPathSegment()));
        if (showNotification) {
            notificationHelper.show(transaction);
        }
        retentionManager.doMaintenance();
        return uri;
    }

    public int update(HttpTransaction transaction, Uri uri) {
        ContentValues values = LocalCupboard.getInstance().withEntity(HttpTransaction.class).toContentValues(transaction);
        int updated = context.getContentResolver().update(uri, values, null, null);
        if (showNotification && updated > 0) {
            notificationHelper.show(transaction);
        }
        return updated;
    }
}
