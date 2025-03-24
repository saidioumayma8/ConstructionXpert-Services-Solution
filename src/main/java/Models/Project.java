package Models;

import java.util.Date;

public class Project {
    private int id;
    private String nom;
    private String description;
    private String dateDebut; // Use java.sql.Date
    private String dateFin;
    private Double budget;

    // Constructor with Date parameters (convert util.Date to sql.Date)
    public Project(int id, String nom, String description,String dateDebut,String dateFin, Double budget) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut; // Convert util.Date to sql.Date
        this.dateFin = dateFin;
        this.budget = budget;
    }

    // Constructor with long timestamps
    public Project(int id, String nom, String description,String dateDebut, String dateFin, double budget) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.budget = budget;
    }

    // Constructor with Strings (if needed, convert String to java.sql.Date)
    public Project(String nom, String description, String dateDebut, String dateFin, double budget) {
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.budget = budget;
    }

    public Project() {
    }

    public Project(int id, String nom, String description, long dateDebut, long dateFin, double budget) {
    }

    public Project(int id, String nom, String description, Date dateDebut, Date dateFin, double budget) {
    }

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

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }
}
