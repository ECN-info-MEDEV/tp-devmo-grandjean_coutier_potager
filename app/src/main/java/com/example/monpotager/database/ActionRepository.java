package com.example.monpotager.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.monpotager.models.Action;
import com.example.monpotager.models.Parcelle;

import java.util.List;

/**
 * Repository for database
 */
class ActionRepository {
    /**
     * the ActionDao
     */
    private ActionDao actionDao;
    /**
     * the ParcelleDao
     */
    private ParcelleDao parcelleDao;
    /**
     * all Actions from database
     */
    private LiveData<List<Action>> allActions;
    /**
     * all Parcelles from database
     */
    private LiveData<List<Parcelle>> allParcelles;

    /**
     * Constructor
     * @param application
     */
    public ActionRepository(Application application) {
        ActionDatabase db = ActionDatabase.getDatabase(application);
        actionDao = db.actionDao();
        parcelleDao = db.parcelleDao();
        allActions = actionDao.getAllActions();
        allParcelles = parcelleDao.getAllParcelles();
    }

    LiveData<List<Action>> getAllActions() {
        return allActions;
    }

    LiveData<List<Parcelle>> getAllParcelles() {
        return allParcelles;
    }

    /**
     * Function to insert Action in database
     * @param action
     */
    void insertA(Action action) {
        ActionDatabase.databaseWriteExecutor.execute(() -> {
            actionDao.insert(action);
        });
    }

    /**
     * Function to insert Parcelle into database
     * @param parcelle
     */
    void insertP(Parcelle parcelle) {
        ActionDatabase.databaseWriteExecutor.execute(() -> {
            parcelleDao.insert(parcelle);
        });
    }
}
