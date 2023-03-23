package com.example.monpotager.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.monpotager.models.Action;
import com.example.monpotager.models.Parcelle;

import java.util.List;

/**
 * ViewModel for database
 */
public class ActionViewModel extends AndroidViewModel {

    /**
     * Action Repository
     */
    private ActionRepository repository;
    /**
     * all Actions from database
     */
    private final LiveData<List<Action>> allActions;
    /**
     * all Parcelles from database
     */
    private final LiveData<List<Parcelle>> allParcelles;

    /**
     * Constructor
     * @param application
     */
    public ActionViewModel (Application application) {
        super(application);
        repository = new ActionRepository(application);
        allActions = repository.getAllActions();
        allParcelles = repository.getAllParcelles();
    }


    public LiveData<List<Action>> getAllActions() { return allActions; }
    public LiveData<List<Parcelle>> getAllParcelles() { return allParcelles; }

    /**
     * Function to insert Action in the database
     * @param action
     */
    public void insertA(Action action) { repository.insertA(action); }

    /**
     * Function to insert Parcelle in the databse
     * @param parcelle
     */
    public void insertP(Parcelle parcelle) { repository.insertP(parcelle); }
}
