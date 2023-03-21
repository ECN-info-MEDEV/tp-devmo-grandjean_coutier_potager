package com.example.monpotager.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.monpotager.models.Action;
import com.example.monpotager.models.Parcelle;

import java.util.List;

public class ActionViewModel extends AndroidViewModel {

    private ActionRepository repository;

    private final LiveData<List<Action>> allActions;
    private final LiveData<List<Parcelle>> allParcelles;

    public ActionViewModel (Application application) {
        super(application);
        repository = new ActionRepository(application);
        allActions = repository.getAllActions();
        allParcelles = repository.getAllParcelles();
    }

    public LiveData<List<Action>> getAllActions() { return allActions; }
    public LiveData<List<Parcelle>> getAllParcelles() { return allParcelles; }

    public void insertA(Action action) { repository.insertA(action); }
    public void insertP(Parcelle parcelle) { repository.insertP(parcelle); }
}
