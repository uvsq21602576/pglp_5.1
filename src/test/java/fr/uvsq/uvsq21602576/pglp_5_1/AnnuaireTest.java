package fr.uvsq.uvsq21602576.pglp_5_1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test pour Annuaire.
 * @author Flora
 */
public class AnnuaireTest {
    /**
     * Un personnel.
     */
    Personnel p;
    /**
     * Un deuxième personnel.
     */
    Personnel p2;
    /**
     * Un groupe.
     */
    Groupe g;
    /**
     * L'annuaire.
     */
    Annuaire a;
    
    /**
     * Initialise les variables 
     */
    @Before
    public void init() {
        p = new Personnel.Builder("1", "1",
                LocalDate.of(2000, 01, 05),
                new Telephone("06...", "portable"))
                .build();
        p2 = new Personnel.Builder("1", "2",
                LocalDate.of(2000, 01, 05),
                new Telephone("06...", "portable"))
                .build();

        g = new Groupe("G");
        g.add(p);
        g.add(p2);

        a = new Annuaire(g);
    }

    /**
     * Teste la méthode hierarchie.
     */
    @Test
    public void hierarchieTest() {
        String expected = "Groupe G (2)\n" + "\t|-   "
                + p.toString() + "\n"
                + "\t|-   " + p2.toString() + "\n";
        assertEquals(expected, a.hierachie());
    }

    /**
     * Teste la méthode groupe.
     */
    @Test
    public void groupeTest() {
        String expected = "---\n" + g.toString() + "\n"
                + "---\n" + p.toString() + "\n"
                + p2.toString() + "\n";
        assertEquals(expected, a.groupe());
    }
    
    /**
     * Teste a sérialisation.
     * En sérialisant, puis déserialisant,
     * et comparant avec l'objet initial.
     * @throws IOException En cas d'erreur d'ecriture ou lecture dans les stream
     * @throws ClassNotFoundException Si la classe de l'objet lu n'existe pas
     */
    @Test
    public void serialisationTest() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream outBuff = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outBuff);
        out.writeObject(a);
        out.close();
        
        byte[] buff = outBuff.toByteArray();
        outBuff.close();
        
        ByteArrayInputStream inBuff = new ByteArrayInputStream(buff);
        ObjectInputStream in = new ObjectInputStream(inBuff);
        Object observed = in.readObject();
        in.close();
        inBuff.close();
        
        assertTrue(observed instanceof Annuaire);
        assertEquals(a, observed);
    }
}
