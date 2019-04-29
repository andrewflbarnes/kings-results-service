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

    private static final String SHOULD_NOT_EQUAL = "%nUnexpected: %s%nActual    : %s%n";
    private static final String SHOULD_EQUAL = "%nExpected  : %s%nActual    : %s%n";

    public static void assertEqualsStrong(Object... objects) {
        int count = objects.length;

        // Include checking that object[i] equals object[i]
        for (int i = 0; i < count; i ++) {
            for (int j = i; j < count; j++) {
                Object a = objects[i];
                Object b = objects[j];

                assertEqualsMsg(a, b);
                assertEqualsMsg(b, a);
                assertEqualsMsg(a.hashCode(), b.hashCode());
            }
        }
    }

    private static void assertEqualsMsg(Object a, Object b) {
        assertEquals(String.format(SHOULD_EQUAL, a, b), a , b);
    }

    public static void assertNotEqualsStrong(Object... objects) {
        int count = objects.length;

        // Exclude checking that object[i] equals object[i]
        for (int i = 0; i < count - 1; i ++) {
            for (int j = i + 1; j < count; j++) {
                Object a = objects[i];
                Object b = objects[j];

                assertNotEqualsMsg(a, b);
                assertNotEqualsMsg(b, a);
                assertNotEqualsMsg(a.hashCode(), b.hashCode());
            }
        }
    }

    private static void assertNotEqualsMsg(Object a, Object b) {
        assertNotEquals(String.format(SHOULD_NOT_EQUAL, a, b), a , b);
    }
}
