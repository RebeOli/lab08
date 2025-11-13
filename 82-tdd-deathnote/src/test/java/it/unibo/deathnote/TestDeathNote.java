package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {
    private static final int RULE = 5;
    private static final String CAUSE1 = "Shot";
    private static final String CAUSE2 = "karting accident";
    private static final String DETAILS1 = "ran for too long";
    private static final String DETAILS2 = "out of breath";
    private static final String GENERAL_CAUSE = "Heart Attack";
    private static final String NAME = "Luca";
    private static final String NAME2 = "Marco";
    private static final int SPLEEP1 = 100;
    private static final int SPLEEP2 = 6100;
    private DeathNote deathNote;

    @BeforeEach
    void setUp() {
        deathNote = new DeathNoteImpl();
    }

    @Test
    void testRule() {
        final int[] array = {0, -RULE};
        for (final int x : array) {
            assertThrows(IllegalArgumentException.class, new Executable() {
                @Override
                public void execute() throws Throwable {
                    deathNote.getRule(x);
                }
            }); 
        }
    }

    @Test
    void testRuleNotNull() {
        for (final String rule : DeathNote.RULES) {
            if (rule == null || " ".equals(rule)) {
                assertNotNull(rule, "Rule is null");
                assertTrue(rule.isEmpty(), "Rule is empty");
            }
        }
    }

    @Test
    void testName() {
        assertFalse(deathNote.isNameWritten(NAME));
        deathNote.writeName(NAME);
        assertTrue(deathNote.isNameWritten(NAME));
        assertFalse(deathNote.isNameWritten(NAME2));
        assertFalse(deathNote.isNameWritten(" "));
    }

    @Test
    void testCauseOfDeath() {
        assertThrows(IllegalStateException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                deathNote.writeDeathCause(CAUSE1);
            }
        });
        deathNote.writeName(NAME);
        assertEquals(GENERAL_CAUSE, deathNote.getDeathCause(NAME));
        deathNote.writeName(NAME2);
        assertTrue(deathNote.writeDeathCause(CAUSE2));
        assertEquals(CAUSE2, deathNote.getDeathCause(NAME2));
        try {
            Thread.sleep(SPLEEP1);
        } catch (final InterruptedException e) {
            throw new IllegalArgumentException("Interrupted during sleep", e);
        }
        deathNote.writeDeathCause(CAUSE1);
        assertEquals(CAUSE2, deathNote.getDeathCause(NAME2));
    }

    @Test
    void testDetails() {
        assertThrows(IllegalStateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                deathNote.writeDetails(DETAILS1);
            }
        });
        deathNote.writeName(NAME);
        assertEquals(" ", deathNote.getDeathDetails(NAME));
        deathNote.writeDeathCause(GENERAL_CAUSE);
        assertTrue(deathNote.writeDetails(DETAILS1));
        assertEquals(DETAILS1, deathNote.getDeathDetails(NAME));
        deathNote.writeName(NAME2);
        deathNote.writeDeathCause(GENERAL_CAUSE);
        try {
            Thread.sleep(SPLEEP2);
        } catch (final InterruptedException e) {
            throw new IllegalArgumentException("Interrupted during sleep", e);
        }
        assertFalse(deathNote.writeDetails(DETAILS2));
        assertEquals(" ", deathNote.getDeathDetails(NAME2));
    }
}
