package fr.uvsq.uvsq21602576.pglp_5_1;

public abstract class DAO<T> {
    
    /**
     * Pour la création.
     * @param obj   objet à créer
     * @return  object créé
     */
    public abstract T create(T obj);
    
    /**
     * Pour la recherche.
     * @param id    Identifiant de l'objet à trouvée
     * @return  object trouvé
     */
    public abstract T find(String id);
    
    /**
     * Pour la modification.
     * @param obj   objet à modifié
     * @return  object modifié
     */
    public abstract T update(T obj);
    
    /**
     * Pour la suppression.
     * @param obj   objet à supprimer
     */
    public abstract void delete(T obj);
    
}
