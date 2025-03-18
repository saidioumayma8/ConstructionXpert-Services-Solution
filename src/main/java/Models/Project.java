package Models;

import java.sql.Date;

public class Project {
    private int id;
    private String nom;
    private String description;
    private String dateDebut;
    private String dateFin;
    private double budget;

    public Project() {}

    public Project(int id, String nom, String description, String dateDebut, String dateFin, double budget) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.budget = budget;
    }

    public Project(String nom, String description, String dateDebut, String dateFin, double budget) {
    }


    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(String dateDebut) { this.dateDebut = dateDebut; }

    public int getDateFin() { return dateFin; }
    public void setDateFin(String dateFin) { this.dateFin = dateFin; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }
}

