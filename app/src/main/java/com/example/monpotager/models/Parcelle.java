package com.example.monpotager.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity Class for a Parcelle
 */
@Entity(tableName = "parcelle_table")
public class Parcelle {

    /**
     * Id of the Parcelle
     */
    @PrimaryKey
    private long id;

    /**
     * Constructor
     * @param id
     */
    public Parcelle(long id) {

        this.id = id;

    }

    // --- GETTER ---

    public long getId() { return id; }


    // --- SETTER ---

    public void setId(long id) { this.id = id; }


}
