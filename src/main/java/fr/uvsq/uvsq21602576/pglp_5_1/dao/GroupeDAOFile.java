package fr.uvsq.uvsq21602576.pglp_5_1.dao;

import fr.uvsq.uvsq21602576.pglp_5_1.Groupe;

public class GroupeDAOFile extends DAO<Groupe> {
    private String dossier;

    public GroupeDAOFile(String dossierDB) {
        dossier = dossierDB + "Groupe\\";
    }

    @Override
    public Groupe create(Groupe obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Groupe find(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Groupe update(Groupe obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Groupe obj) {
        // TODO Auto-generated method stub

    }

}
