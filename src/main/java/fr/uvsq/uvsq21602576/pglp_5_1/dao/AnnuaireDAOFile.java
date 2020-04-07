package fr.uvsq.uvsq21602576.pglp_5_1.dao;

import fr.uvsq.uvsq21602576.pglp_5_1.Annuaire;

public class AnnuaireDAOFile extends DAO<Annuaire> {
    private String dossier;

    public AnnuaireDAOFile(String dossierDB) {
        dossier = dossierDB + "Annuaire\\";
    }

    @Override
    public Annuaire create(Annuaire obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Annuaire find(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Annuaire update(Annuaire obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Annuaire obj) {
        // TODO Auto-generated method stub

    }

}
