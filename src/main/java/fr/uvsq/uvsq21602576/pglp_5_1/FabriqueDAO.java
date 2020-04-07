package fr.uvsq.uvsq21602576.pglp_5_1;

public class FabriqueDAO {
    
    /**
     * Retourne le DAO pour le Telephone.
     * @return  DAO pour Telephone
     */
    public static DAO<Telephone> getTelephoneDAO() {
        return new TelephoneDAO();
    }
    
    /**
     * Retourne le DAO pour le Personnel.
     * @return  DAO pour Personnel
     */
    public static DAO<Personnel> getPersonnelDAO() {
        return new PersonnelDAO();
    }
    
    /**
     * Retourne le DAO pour le Groupe.
     * @return  DAO pour Groupe
     */
    public static DAO<Groupe> getGroupeDAO() {
        return new GroupeDAO();
    }
    
    /**
     * Retourne le DAO pour le Annuaire.
     * @return  DAO pour Annuaire
     */
    public static DAO<Annuaire> getAnnuaireDAO() {
        return new AnnuaireDAO();
    }
}
