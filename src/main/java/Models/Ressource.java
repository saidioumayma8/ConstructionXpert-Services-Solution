package Models;

public class Ressource {
    private int id;
    private String nom;
    private String type;
    private int quantite;
    private String fournisseur;
    private int idTache; // Clé étrangère vers Tache

    public Ressource() {}

    public Ressource(int id, String nom, String type, int quantite, String fournisseur, int idTache) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.quantite = quantite;
        this.fournisseur = fournisseur;
        this.idTache = idTache;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public String getFournisseur() { return fournisseur; }
    public void setFournisseur(String fournisseur) { this.fournisseur = fournisseur; }

    public int getIdTache() { return idTache; }
    public void setIdTache(int idTache) { this.idTache = idTache; }
}

