package com.example.monpotager.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.monpotager.models.Action;
import com.example.monpotager.models.Parcelle;

import java.util.List;

class ActionRepository {

    private ActionDao actionDao;
    private ParcelleDao parcelleDao;
    private LiveData<List<Action>> allActions;
    private LiveData<List<Parcelle>> allParcelles;

    ActionRepository(Application application) {
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

    void insertA(Action action) {
        ActionDatabase.databaseWriteExecutor.execute(() -> {
            actionDao.insert(action);
        });
    }

    void insertP(Parcelle parcelle) {
        ActionDatabase.databaseWriteExecutor.execute(() -> {
            parcelleDao.insert(parcelle);
        });
    }
}
