import java.sql.Date;

public class Tache {
    private int id;
    private String description;
    private Date dateDebut; // Changed to java.sql.Date
    private Date dateFin;   // Changed to java.sql.Date
    private int idProjet;   // Foreign key for Project

    public Tache() {}

    public Tache(int id, String description, Date dateDebut, Date dateFin, int idProjet) {
        this.id = id;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idProjet = idProjet;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }

    public int getIdProjet() { return idProjet; }
    public void setIdProjet(int idProjet) { this.idProjet = idProjet; }
}
