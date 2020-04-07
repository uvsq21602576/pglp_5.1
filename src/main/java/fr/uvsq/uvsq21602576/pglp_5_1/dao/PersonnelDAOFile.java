package fr.uvsq.uvsq21602576.pglp_5_1.dao;

import fr.uvsq.uvsq21602576.pglp_5_1.Personnel;

public class PersonnelDAOFile extends DAO<Personnel> {
    private String dossier;

    public PersonnelDAOFile(String dossierDB) {
        dossier = dossierDB + "Personnel\\";
    }

    @Override
    public Personnel create(Personnel obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Personnel find(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Personnel update(Personnel obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Personnel obj) {
        // TODO Auto-generated method stub

    }

}
