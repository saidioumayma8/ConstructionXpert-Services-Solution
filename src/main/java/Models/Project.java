package Models;

import java.sql.Date;

public class Project {
    private int id;
    private String nom;
    private String description;
    private Date dateDebut; // Use java.sql.Date
    private Date dateFin;
    private Double budget;

    // Constructor with Date parameters (convert util.Date to sql.Date)
    public Project(int id, String nom, String description, java.util.Date dateDebut, java.util.Date dateFin, Double budget) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebut = new Date(dateDebut.getTime()); // Convert util.Date to sql.Date
        this.dateFin = new Date(dateFin.getTime());
        this.budget = budget;
    }

    // Constructor with long timestamps
    public Project(int id, String nom, String description, long dateDebut, long dateFin, double budget) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebut = new Date(dateDebut);
        this.dateFin = new Date(dateFin);
        this.budget = budget;
    }

    // Constructor with Strings (if needed, convert String to java.sql.Date)
    public Project(String nom, String description, String dateDebut, String dateFin, double budget) {
        this.nom = nom;
        this.description = description;
        this.dateDebut = Date.valueOf(dateDebut); // Requires "yyyy-MM-dd" format
        this.dateFin = Date.valueOf(dateFin);
        this.budget = budget;
    }

    public Project() {
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
