package org.kingsski.kaas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * "WHY?" I here you cry.
 * Because assertEquals/assertNotEquals only tests equality in one direction
 * and aren't really designed with testing the equals method in mind.
 *
 * So, convenience methods for both directions and the hash code.
 */
public class TestUtil {
    public static void assertEqualsStrong(Object a, Object b) {
        assertEquals(a, b);
        assertEquals(b, a);
        assertEquals(a.hashCode(), b.hashCode());
    }

    public static void AssertNotEqualsStrong(Object a, Object b) {
        assertNotEquals(a, b);
        assertNotEquals(b, a);
        assertNotEquals(a.hashCode(), b.hashCode());
    }
}
