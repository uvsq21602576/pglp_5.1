package fr.uvsq.uvsq21602576.pglp_5_1.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_5_1.Personnel;
import fr.uvsq.uvsq21602576.pglp_5_1.Telephone;

/**
 * Classe de test pour personnelDAO.
 * @author Flora
 */
public class PersonnelDAOFileTest {
    /** FabriqueDAO ayant donné daoP. */
    private static FabriqueDAO fabrique;
    /** nomDossier de la fabrique avant modification pour les tests. */
    private static String nomDossierFabriqueAvant;
    /** nomDossier de la fabrique pour les tests. */
    private static String nomDossierFabriqueTest;
    /** DAO pour Personnel. */
    private static DAO<Personnel> daoP;

    /**
     * Initialise les variables de classes pour les tests.
     * Le dossier utilisé pour les tests est forcément un dossier n'existant pas
     * avant.
     * Il est créé uniquement pour les tests.
     */
    @BeforeClass
    public static void initDAO() {
        fabrique = FabriqueDAO.getFabriqueDAO(FabriqueDAO.TypeDAO.FILE);
        nomDossierFabriqueAvant = ((FabriqueDAOFile) fabrique).getDossierDB();
        int i = 0;
        do {
            ((FabriqueDAOFile) fabrique)
                    .setDossierDB("databaseFileForTestUniquely" + i + "\\");
            nomDossierFabriqueTest = ((FabriqueDAOFile) fabrique)
                    .getDossierDB();
            i++;
        } while (new File(nomDossierFabriqueTest).exists());

        daoP = fabrique.getPersonnelDAO();
    }

    /**
     * Pour terminer correctement tous les tests.
     * La fabrique reprend son dossier d'origine.
     * Le dossier utilisé pour tous les tests est entièrement supprimé.
     */
    @AfterClass
    public static void termineDAO() {
        ((FabriqueDAOFile) fabrique).setDossierDB(nomDossierFabriqueAvant);
        deleteAllDossier(new File(nomDossierFabriqueTest));
    }

    /**
     * Supprime le dossier et son contenu.
     * Fonctionne en récursivité.
     * @param dossier Chemin du dossier à supprimer
     * @return Si la délétion à réussie.
     */
    private static boolean deleteAllDossier(final File dossier) {
        if (dossier.isDirectory()) {
            File[] sousDossiers = dossier.listFiles();
            if (sousDossiers != null) {
                for (File f : sousDossiers) {
                    deleteAllDossier(f);
                }
            }
        }
        return dossier.delete();
    }

    /**
     * Test de la création.
     * @throws FileNotFoundException Si l'emplacement du fichier est impossible
     *         à atteindre
     * @throws IOException En cas d'erreur lors de la lecture.
     * @throws ClassNotFoundException Si la classe du fichier de sérialisation
     *         est inconnue de l'application.
     */
    @Test
    public void createTest()
            throws FileNotFoundException, ClassNotFoundException, IOException {
        Personnel p = new Personnel.Builder(1, "Nom", "Prenom",
                LocalDate.of(2000, 01, 05),
                new Telephone(1, "06...", "portable")).build();
        daoP.create(p);

        String nomFichier = nomDossierFabriqueTest + "Personnel\\" + p.getId()
                + ".ser";
        File f = new File(nomFichier);
        assertTrue(f.exists());
        Personnel observed = deserialize(nomFichier);
        assertEquals(p, observed);
        assertEquals(p.getId(), observed.getId());
    }

    /**
     * Test de la recherche.
     */
    @Test
    public void findTest() {
        Personnel p = new Personnel.Builder(2, "Nom", "Prenom",
                LocalDate.of(2000, 01, 05),
                new Telephone(1, "06...", "portable")).build();
        daoP.create(p);

        Personnel observed = daoP.find(p.getId() + "");
        assertEquals(p.getId(), observed.getId());
        assertEquals(p, observed);
    }

    /**
     * Test de la modification.
     * @throws FileNotFoundException Si l'emplacement du fichier est impossible
     *         à atteindre
     * @throws IOException En cas d'erreur lors de la lecture.
     * @throws ClassNotFoundException Si la classe du fichier de sérialisation
     *         est inconnue de l'application.
     */
    @Test
    public void updateTest()
            throws FileNotFoundException, ClassNotFoundException, IOException {
        Personnel p = new Personnel.Builder(3, "Nom", "Prenom",
                LocalDate.of(2000, 01, 05),
                new Telephone(1, "06...", "portable")).build();
        daoP.create(p);

        Personnel updateP = new Personnel.Builder(3, "Nom", "Prerazfegrhtrnom",
                LocalDate.of(2000, 01, 05),
                new Telephone(1, "06...", "portable")).build();
        daoP.update(updateP);

        String nomFichier = nomDossierFabriqueTest + "Personnel\\"
                + updateP.getId() + ".ser";
        File f = new File(nomFichier);
        assertTrue(f.exists());
        Personnel observed = deserialize(nomFichier);
        assertEquals(updateP.getId(), observed.getId());
        assertEquals(updateP, observed);
    }

    /**
     * Teste de la délétion.
     */
    @Test
    public void deleteTest() {
        Personnel p = new Personnel.Builder(4, "Nom", "Prenom",
                LocalDate.of(2000, 01, 05),
                new Telephone(1, "06...", "portable")).build();
        daoP.create(p);

        daoP.delete(p);
        String nomFichier = nomDossierFabriqueTest + "Personnel\\" + p.getId()
                + ".ser";
        File f = new File(nomFichier);
        assertFalse(f.exists());
    }

    /**
     * Methode pour désérialiser un Personnel.
     * Lis le fichier nomFichier contenant la sérialisation précédemment
     * effectuée.
     * @param nomFichier Chemin du dossier contenant le Personnel
     * @return Personnel stocké à nomFichier, ou null en cas d'erreur.
     * @throws FileNotFoundException Si l'emplacement du fichier est impossible
     *         à atteindre
     * @throws IOException En cas d'erreur lors de la lecture.
     * @throws ClassNotFoundException Si la classe du fichier de sérialisation
     *         est inconnue de l'application.
     */
    private Personnel deserialize(final String nomFichier)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(new File(nomFichier))))) {
            Object o = in.readObject();
            if (o instanceof Personnel) {
                return (Personnel) o;
            }
        }
        return null;
    }
}
