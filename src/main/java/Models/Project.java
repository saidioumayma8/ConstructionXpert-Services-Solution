package Models;

import java.util.Date;

public class Project {
    private int id;
    private String nom;
    private String description;
    private java.sql.Date dateDebut;
    private java.sql.Date dateFin;
    private Double budget;

    // Constructor
    public Project(int id, String nom, String description, Date dateDebut, Date dateFin, Double budget) {
        this.nom = nom;
        this.description = description;
        this.dateDebut = (java.sql.Date) dateDebut;
        this.dateFin = (java.sql.Date) dateFin;
        this.budget = budget;
    }

    public Project(int id, String nom, String description, long dateDebut, long dateFin, double budget) {
    }

    public Project(String nom, String description, Date dateDebut, Date dateFin, double budget) {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = (java.sql.Date) dateDebut;
    }

    public java.sql.Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = (java.sql.Date) dateFin;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }
}
