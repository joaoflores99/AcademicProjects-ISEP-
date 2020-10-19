/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests a keyword,
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class KeywordTest {
    
    private Keyword keyword;
    
    public KeywordTest() {
        this.keyword = new Keyword("super");
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of toKeywordsList method, of class Keyword.
     */
    @Test
    public void testToKeywordsList() throws Exception {
        System.out.println("toKeywordsList");
        String keywordsCSV = "abc,def";
        List<Keyword> expResult = new ArrayList<>();
        expResult.add(new Keyword("abc"));
        expResult.add(new Keyword("def"));
        List<Keyword> result = Keyword.toKeywordsList(keywordsCSV);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Keyword.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "super";
        String result = this.keyword.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Keyword.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "hot";
        this.keyword.setDescription(description);
        assertEquals(this.keyword.getDescription(), description);
    }

    /**
     * Test of validate method, of class Keyword.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        assertTrue(this.keyword.validate());
    }

    /**
     * Test of equals method, of class Keyword.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = new Keyword("super");
        assertTrue(this.keyword.equals(otherObject));
    }

    /**
     * Test of getDisplayInfo method, of class Keyword.
     */
    @Test
    public void testGetDisplayInfo() {
        System.out.println("getDisplayInfo");
        String expResult = "super";
        String result = this.keyword.getDisplayInfo();
        assertEquals(expResult, result);
    }
    
}
