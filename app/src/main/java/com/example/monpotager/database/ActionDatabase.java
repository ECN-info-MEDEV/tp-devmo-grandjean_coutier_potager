package com.example.monpotager.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.monpotager.models.Action;
import com.example.monpotager.models.Parcelle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Database to manage Actions and Parcelles
 */
@Database(entities = {Parcelle.class, Action.class}, version = 1, exportSchema = false)
public abstract class ActionDatabase extends RoomDatabase {

    /**
     * Create the ActionDao
     * @return the Action Dao
     */
    public abstract ActionDao actionDao();

    /**
     * Create the ParcelleDao
     * @return the Parcelle Dao
     */
    public abstract ParcelleDao parcelleDao();


    //Create the singleton
    private static volatile ActionDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    /**
     * Function to get Database in a context
     * @param context
     * @return the Database built
     */
    public static ActionDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (ActionDatabase.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),

                                    ActionDatabase.class, "action_database")
                            .addCallback(prepopulateDatabase())
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    /**
     * Function to prepopulate the Databse
     * @return a callBack
     */
    private static Callback prepopulateDatabase() {

        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {

                super.onCreate(db);

                Executors.newSingleThreadExecutor().execute(() -> {
                    //Create Daos and delete all Actions and Parcelles
                    ParcelleDao parcelleDao = INSTANCE.parcelleDao();
                    ActionDao actionDao = INSTANCE.actionDao();
                    parcelleDao.deleteAll();
                    actionDao.deleteAll();

                    //Create Parcelles
                    Parcelle parcelle = new Parcelle(1);
                    parcelleDao.insert(parcelle);
                    parcelle = new Parcelle(2);
                    parcelleDao.insert(parcelle);
                    parcelle = new Parcelle(3);
                    parcelleDao.insert(parcelle);
                    parcelle = new Parcelle(4);
                    parcelleDao.insert(parcelle);
                    parcelle = new Parcelle(5);
                    parcelleDao.insert(parcelle);

                    //Create Actions
                    Action action = new Action(1, "Arroser", "20/03/2023", "");
                    actionDao.insert(action);
                    action = new Action(2, "Désherber", "21/03/2023", "Il reste quelques orties.");
                    actionDao.insert(action);
                });

            }

        };

    }

}
