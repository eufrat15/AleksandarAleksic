package com.example.androiddevelopment.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.androiddevelopment.myapplication.db.model.TuristickaAtrakcija;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by androiddevelopment on 29.11.17..
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "ormlite.db";

    private static final int DATABASE_VERSION = 1;

    private Dao<TuristickaAtrakcija, Integer> mTuristickaAtrakcijaDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, TuristickaAtrakcija.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, TuristickaAtrakcija.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Dao<TuristickaAtrakcija, Integer> getmTuristickaAtrakcijaDao() throws SQLException {
        if (mTuristickaAtrakcijaDao == null) {
            mTuristickaAtrakcijaDao = getDao(TuristickaAtrakcija.class);
        }

        return mTuristickaAtrakcijaDao;
    }

    @Override
    public void close() {
        mTuristickaAtrakcijaDao = null;

        super.close();
    }
}
