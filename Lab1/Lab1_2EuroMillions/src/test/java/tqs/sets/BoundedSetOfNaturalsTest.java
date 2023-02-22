/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    //@Disabled("TODO revise test logic")
    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element found in set.");
        assertEquals(1, setA.size());
    }

    //@Disabled("TODO revise to test the construction from invalid arrays")
    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }
    @Test
    void testAddElementToFullSet() {
        assertThrows(IllegalArgumentException.class, () -> setB.add(11));
    }

    @Test
    void testAddElementThatAlreadyExists() {
        assertThrows(IllegalArgumentException.class, () -> setB.add(60));
    }
    @Test
    void testAddNegativeElement() {
        assertThrows(IllegalArgumentException.class, () -> setA.add(-10));
    }
    @Test
    void testSetIntersection() {
        assertTrue(setB.intersects(setC));
        assertFalse(setA.intersects(setC));
    }
    @Test
    void testEquals() {
        setB = setA;
        assertEquals(setA, setB);
    }
    @Test
    void testHashCode() {
        assertNotEquals(setB.hashCode(), setC.hashCode());
    }
    @Test
    void testEqualsWhenNull() {
        assertFalse(setA.equals(null));
    }
    @Test
    void testEqualsWhenDifferentClass() {
        int number = 0;
        assertFalse(setA.equals(number));
    }
}
