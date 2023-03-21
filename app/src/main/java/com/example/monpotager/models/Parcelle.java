package com.example.monpotager.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "parcelle_table")
public class Parcelle {

    @PrimaryKey
    private long id;

    public Parcelle(long id) {

        this.id = id;

    }

    // --- GETTER ---

    public long getId() { return id; }


    // --- SETTER ---

    public void setId(long id) { this.id = id; }


}
