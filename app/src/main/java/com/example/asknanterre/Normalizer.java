package com.example.asknanterre;

public class Normalizer {

    public Normalizer() {
    }

    public String normalizeTitre(String titre) {
        if(!Character.isUpperCase(titre.indexOf(0))) {
            titre = titre.substring(0, 1).toUpperCase() + titre.substring(1);
        }
        /*if(titre.charAt(titre.length()-1)!='?') {
            titre = titre + "?";
        }*/
        if(titre.length()>15) {
            titre = titre.substring(0, Math.min(titre.length(), 15));
            titre = titre.substring(0, titre.length() - 4);
            titre = titre + " ...";
        }
        return titre;
    }

    public String normalizeNom(String nom) {
        if(!Character.isUpperCase(nom.indexOf(0))) {
            nom = nom.substring(0, 1).toUpperCase() + nom.substring(1);
        }
        /*if(nom.charAt(nom.length()-1)!='?') {
            nom = nom + "?";
        }*/
        return nom;
    }
}
