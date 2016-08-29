package model;

import model.types.EUserRole;

public class Client {

    public String id;
    public String email;
    public String motDePasse;
    public String nom;
    public String prenom;
    public String adressePostale;
    public String telephone;
    public EUserRole role;
    public boolean isSupprime;

    public Client(String id, String email, String motDePasse, String nom, String prenom, String adressePostale, String telephone, EUserRole role, boolean isSupprime) {
        this.id = id;
        this.email = email;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.adressePostale = adressePostale;
        this.telephone = telephone;
        this.role = role;
        this.isSupprime = isSupprime;
    }
}
