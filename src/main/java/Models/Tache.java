package Models;

import java.sql.Date;

public class Tache {
    public boolean getId;
    private int id;
    private String description;
    private String dateDebut;
    private String dateFin;
    private int projectId;

    // Constructor
    public Tache(String description, String dateDebut, String dateFin, int projectId) {
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.projectId = projectId;
    }

    public Tache() {

    }

    public Tache(int id, String description, Date dateDebut, Date dateFin, int projectId) {
        this.id = id;
        this.description = description;
        this.dateDebut = dateDebut.toString();
        this.dateFin = dateFin.toString();
        this.projectId = projectId;

    }

    public Tache(int id, String description, String dateDebut, String dateFin, int idProjet) {
        this.id = id;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.projectId = idProjet;

    }

    // Getters
    public String getDescription() {
        return this.description;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return this.dateFin;
    }

    public int getProjectId() {
        return this.projectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
