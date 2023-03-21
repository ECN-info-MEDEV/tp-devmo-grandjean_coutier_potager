package com.example.monpotager.models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.monpotager.models.Parcelle;

@Entity(tableName = "action_table",
        foreignKeys = @ForeignKey(entity = Parcelle.class,

        parentColumns = "id",

        childColumns = "parcelleId"))
public class Action {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long parcelleId;

    private String type;

    private String date;

    private String description;

    public Action() { }

    public Action(long parcelleId, String type, String date, String description) {

        this.parcelleId = parcelleId;

        this.type = type;

        this.date = date;

        this.description = description;

    }
// --- GETTER ---

    public long getId() { return id; }

    public String getType() { return type; }

    public String getDate() { return date; }

    public String getDescription() { return description; }

    public long getParcelleId() { return parcelleId; }

    // --- SETTER ---

    public void setId(long id) { this.id = id; }

    public void setType(String nom) { this.type = nom; }

    public void setDate(String date) { this.date = date; }

    public void setDescription(String text) { this.description = text; }

    public void setParcelleId(long parcelleId) { this.parcelleId = parcelleId; }

}
