package com.longshihan.moodtail.util.db;


import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * @创建者 longshihan
 * Realm数据库
 * @创建时间 2016/9/18 14:26
 */
public class RealmHelper {
    private Realm mRealm;

    public RealmHelper(Context context) {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder(context)
                .name("myOtherRealm.realm")
                .build());

    }

}
