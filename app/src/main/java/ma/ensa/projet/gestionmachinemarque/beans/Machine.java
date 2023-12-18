package ma.ensa.projet.gestionmachinemarque.beans;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import javax.crypto.Mac;

public class Machine
{
    private int id;
    private String reference;
    private double prix;
    private JSONObject marque;
    private String date_achat;

    public Machine(){

    }

    public Machine(String reference, double prix, String marque, String date_achat) {
        this.reference = reference;
        this.prix = prix;
        // Convertir la chaîne de caractères en un objet JSON
        this.marque = convertStringToJSONObject(marque);
        this.date_achat = date_achat;
    }

    // Méthode pour convertir la chaîne de caractères en un objet JSON
    private JSONObject convertStringToJSONObject(String jsonString) {
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }




    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public String getDate_achat() {
        return date_achat;
    }

    public void setDate_achat(String date_achat) {
        this.date_achat = date_achat;
    }




    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", prix=" + prix +
                ", marque=" + marque +
                ", date_achat=" + date_achat +
                '}';
    }

    public Machine(int id, String reference, double prix, JSONObject marque, String date_achat) {
        this.id=id;
        this.reference=reference;
        this.prix=prix;
        this.marque= marque;
        this.date_achat=date_achat;
    }
}
