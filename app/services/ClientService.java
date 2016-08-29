package services;

import com.google.common.base.Strings;
import exceptions.InvalidArgumentException;
import exceptions.MetierException;
import model.Client;
import model.types.EUserRole;
import org.mindrot.jbcrypt.BCrypt;
import services.db.DBService;
import validators.EmailValidator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientService {

    private static int BCRYPT_WORKLOAD = 12;

    /**
     * Singleton
     */
    private static ClientService instance;

    /**
     * Constructeur privé = personne ne peut faire de new ClientService()
     */
    private ClientService() {
    }

    /**
     * Seule méthode pour récupérer une instance (toujours la même) de ClientService
     *
     * @return toujours la même instance de ClientService
     */
    public static ClientService get() {
        if (instance == null) {
            instance = new ClientService();
        }
        return instance;
    }

    public Client creer(String email, String motDePasse) throws InvalidArgumentException, MetierException {

        List<String> validationMessages = new ArrayList<>();
        if (Strings.isNullOrEmpty(email)) {
            validationMessages.add("Le email ne peut être null ou vide");
        } else {
            if (!EmailValidator.validate(email)) {
                validationMessages.add("Le format de l'email est invalide");
            }
        }
        if (Strings.isNullOrEmpty(motDePasse)) {
            validationMessages.add("Le motDePasse ne peut être null ou vide");
        }
        if (validationMessages.size() > 0) {
            throw new InvalidArgumentException((String[]) validationMessages.toArray(new String[0]));
        }

        try {
            Statement requete = DBService.get().getConnection().createStatement();
            ResultSet result = requete.executeQuery("SELECT * FROM Client WHERE email='" + email + "'");
            if (result.next()) {
                throw new MetierException("Cet email est déjà utilisé");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Client client = new Client(UUID.randomUUID().toString(), email, encodePassword(motDePasse), null, null, null, null, EUserRole.CLIENT, false);
//        client.id = UUID.randomUUID().toString();
//        client.email = email;
//        client.motDePasse = encodePassword(motDePasse);
//        client.role = EUserRole.CLIENT;
//        client.isSupprime = false;
        return client;
    }

    public void modifier(Client client, String nom, String prenom, String email, String adressePostale, String telephone) throws InvalidArgumentException {

        List<String> validationMessages = new ArrayList<>();
        if (client == null) {
            validationMessages.add("Le client ne peut être null");
        }
        if (validationMessages.size() > 0) {
            throw new InvalidArgumentException((String[]) validationMessages.toArray(new String[0]));
        }

        if (!Strings.isNullOrEmpty(nom)) {
            client.nom = nom;
        }
        if (!Strings.isNullOrEmpty(prenom)) {
            client.prenom = prenom;
        }
        if (!Strings.isNullOrEmpty(email)) {
            client.email = email;
        }
        if (!Strings.isNullOrEmpty(adressePostale)) {
            client.adressePostale = adressePostale;
        }
        if (!Strings.isNullOrEmpty(telephone)) {
            client.telephone = telephone;
        }

    }

    public Client fusionner(Client client1, Client client2) throws InvalidArgumentException {

        List<String> validationMessages = new ArrayList<>();
        if (client1 == null) {
            validationMessages.add("Le client1 ne peut être null");
        }
        if (client2 == null) {
            validationMessages.add("Le client2 ne peut être null");
        }
        if (validationMessages.size() > 0) {
            throw new InvalidArgumentException((String[]) validationMessages.toArray(new String[0]));
        }

        if (Strings.isNullOrEmpty(client1.nom) && !Strings.isNullOrEmpty(client2.nom)) {
            client1.nom = client2.nom;
        }
        if (Strings.isNullOrEmpty(client1.prenom) && !Strings.isNullOrEmpty(client2.prenom)) {
            client1.prenom = client2.prenom;
        }
        if (Strings.isNullOrEmpty(client1.adressePostale) && !Strings.isNullOrEmpty(client2.adressePostale)) {
            client1.adressePostale = client2.adressePostale;
        }
        if (Strings.isNullOrEmpty(client1.telephone) && !Strings.isNullOrEmpty(client2.telephone)) {
            client1.telephone = client2.telephone;
        }
        return client1;
    }

    private String encodePassword(String password) {
        String salt = BCrypt.gensalt(BCRYPT_WORKLOAD);
        return BCrypt.hashpw(password, salt);
    }

    public boolean authenticate(String email, String motDePasse) throws InvalidArgumentException {

        List<String> validationMessages = new ArrayList<>();
        if (Strings.isNullOrEmpty(email)) {
            validationMessages.add("Le email ne peut être null ou vide");
        }
        if (Strings.isNullOrEmpty(email)) {
            validationMessages.add("Le motDePasse ne peut être null ou vide");
        }
        if (validationMessages.size() > 0) {
            throw new InvalidArgumentException((String[]) validationMessages.toArray(new String[0]));
        }

        try {
            Statement requete = DBService.get().getConnection().createStatement();
            ResultSet result = requete.executeQuery("SELECT motDePasse FROM Client WHERE email='" + email + "'");
            if (result.next()) {
                if(BCrypt.checkpw(motDePasse, result.getString("motDePasse"))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Client> lister() {
        List<Client> clients = new ArrayList<>();
        try {
            Statement requete = DBService.get().getConnection().createStatement();
            ResultSet result = requete.executeQuery("SELECT * FROM Client WHERE isSupprime = '0' ORDER BY nom");
            while (result.next()) {
                Client client = new Client(
                        result.getString("id"),
                        result.getString("email"),
                        result.getString("motDePasse"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("adressePostale"),
                        result.getString("telephone"),
                        EUserRole.valueOf(result.getString("role")),
                        result.getBoolean("isSupprime"));
//                client.id = result.getString("id");
//                client.email = result.getString("email");
//                client.nom = result.getString("nom");
//                client.prenom = result.getString("prenom");
//                client.adressePostale = result.getString("adressePostale");
//                client.telephone = result.getString("telephone");
//                client.role = EUserRole.valueOf(result.getString("role"));
//                client.isSupprime = result.getBoolean("isSupprime");
//                client.motDePasse = result.getString("motDePasse");
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public void enregistrer(Client client) {

        try {
            PreparedStatement preparedStatement;
            if (DBService.get().getConnection().createStatement().executeQuery("SELECT id FROM Client WHERE id = " + "'" + client.id + "'").next() == false){
                preparedStatement = DBService.get().getConnection().prepareStatement("INSERT INTO Client (`id`, `nom`, `email`, `motDePasse`, `prenom`, `adressePostale`, `telephone`, `role`, `isSupprime`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, client.id);
                preparedStatement.setString(2, client.nom);
                preparedStatement.setString(3, client.email);
                preparedStatement.setString(4, client.motDePasse);
                preparedStatement.setString(5, client.prenom);
                preparedStatement.setString(6, client.adressePostale);
                preparedStatement.setString(7, client.telephone);
                preparedStatement.setString(8, client.role.name());
                preparedStatement.setBoolean(9, client.isSupprime);
            }
            else{
                preparedStatement = DBService.get().getConnection().prepareStatement("UPDATE Client SET `nom` = ?, `email` = ?, `motDePasse` = ?, `prenom` = ?, `adressePostale` = ?, `telephone` = ?, `role` = ?, `isSupprime` = ? WHERE id =" + "'" + client.id + "'");
                preparedStatement.setString(1, client.nom);
                preparedStatement.setString(2, client.email);
                preparedStatement.setString(3, client.motDePasse);
                preparedStatement.setString(4, client.prenom);
                preparedStatement.setString(5, client.adressePostale);
                preparedStatement.setString(6, client.telephone);
                preparedStatement.setString(7, client.role.name());
                preparedStatement.setBoolean(8, client.isSupprime);
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimer(Client client) throws InvalidArgumentException {
        if (client == null) {
            throw new InvalidArgumentException(new String[]{"Le client ne peut être null"});
        }
        client.isSupprime = true;
        try {
            PreparedStatement preparedStatement = DBService.get().getConnection().prepareStatement("UPDATE Client SET `isSupprime` = ? WHERE `id` =  ?");
            preparedStatement.setBoolean(1, client.isSupprime);
            preparedStatement.setString(2, client.id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client getClient(String idClient) throws InvalidArgumentException {

        List<String> validationMessages = new ArrayList<>();
        if (Strings.isNullOrEmpty(idClient)) {
            validationMessages.add("L'idClient ne peut être null ou vide");
        }
        if (validationMessages.size() > 0) {
            throw new InvalidArgumentException((String[]) validationMessages.toArray(new String[0]));
        }

        try {
            Statement requete = DBService.get().getConnection().createStatement();
            ResultSet result = requete.executeQuery("SELECT * FROM Client WHERE id='" + idClient + "'");
            if (result.next()) {
                Client client = new Client(
                        result.getString("id"),
                        result.getString("email"),
                        result.getString("motDePasse"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("adressePostale"),
                        result.getString("telephone"),
                        EUserRole.valueOf(result.getString("role")),
                        result.getBoolean("isSupprime"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Client getClientByEmail(String email) throws InvalidArgumentException {

        List<String> validationMessages = new ArrayList<>();
        if (Strings.isNullOrEmpty(email)) {
            validationMessages.add("L'email ne peut être null ou vide");
        }
        if (validationMessages.size() > 0) {
            throw new InvalidArgumentException((String[]) validationMessages.toArray(new String[0]));
        }

        try {
            Statement requete = DBService.get().getConnection().createStatement();
            ResultSet result = requete.executeQuery("SELECT * FROM Client WHERE email='" + email + "'");
            if (result.next()) {
                Client client = new Client(
                        result.getString("id"),
                        result.getString("email"),
                        result.getString("motDePasse"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("adressePostale"),
                        result.getString("telephone"),
                        EUserRole.valueOf(result.getString("role")),
                        result.getBoolean("isSupprime"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Client> listerArchiveClient() {
        List<Client> clientsArchive = new ArrayList<>();
        try {
            Statement requete = DBService.get().getConnection().createStatement();
            ResultSet result = requete.executeQuery("SELECT * FROM Client WHERE isSupprime = '1' ORDER BY nom");
            while (result.next()) {
                Client clientArchive = new Client(
                        result.getString("id"),
                        result.getString("email"),
                        result.getString("motDePasse"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("adressePostale"),
                        result.getString("telephone"),
                        EUserRole.valueOf(result.getString("role")),
                        result.getBoolean("isSupprime"));
                clientsArchive.add(clientArchive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientsArchive;
    }

    public void clear() {
        try {
            Statement requete = DBService.get().getConnection().createStatement();
            requete.executeUpdate("DELETE FROM Client");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
