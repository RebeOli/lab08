package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import it.unibo.deathnote.impl.DeathNoteImpl;
import it.unibo.deathnote.api.DeathNote;

class TestDeathNote {
    private static final int RULE=5;
    private static final String CAUSE1="Shot";
    private static final String CAUSE2="karting accident";
    private static final String DETAILS1="ran for too long";
    private static final String DETAILS2="out of breath";
    private static final String GENERAL_CAUSE="Heart Attack";
    private static final String NAME="Luca";  
    private static final String NAME2="Marco";  
    private static final int SPLEEP1=100;
    private static final int SPLEEP2=6100;


    private DeathNote deathNote;

    @BeforeEach
    void setUp() {
        deathNote= new DeathNoteImpl();
    }
    @Test
    public void testRule() {
        int[] array=new int[]{0, -RULE};
        for (int x : array){
            try{
                deathNote.getRule(x);
            }catch(Exception e){
                assertEquals(new IllegalArgumentException(), e);
            }
        }  
    }
    @Test
    public void testRuleNotNull(){
        for(String rule : DeathNote.RULES){
            if(rule == null || rule == " "){
                throw new NullPointerException("Rule is empty or null");
            }
        }
    }
    
    @Test
    public void testName(){
        assertEquals(deathNote.isNameWritten(NAME), false);
        deathNote.writeName(NAME);
        assertEquals(deathNote.isNameWritten(NAME), true);
        assertEquals(deathNote.isNameWritten(NAME2), false);
        assertEquals(deathNote.isNameWritten(" "), false);
    }
    @Test
    public void testCauseOfDeath(){
        assertThrows(IllegalStateException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                deathNote.writeDeathCause(CAUSE1);
            }
            
        });
        deathNote.writeName(NAME);
        assertEquals(deathNote.getDeathCause(NAME), GENERAL_CAUSE);
        deathNote.writeName(NAME2);
        assertEquals(deathNote.writeDeathCause(CAUSE2), true);
        assertEquals(deathNote.getDeathCause(NAME2), CAUSE2);
        try{
            Thread.sleep(SPLEEP1);
        }catch(InterruptedException e){
            throw new IllegalArgumentException();
        }
        deathNote.writeDeathCause(CAUSE1);
        assertEquals(deathNote.getDeathCause(NAME2), CAUSE2);
    }
    @Test
    public void testDetails(){
        assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                deathNote.getDeathDetails(NAME);
            }
        });
        deathNote.writeName(NAME);
        assertEquals(deathNote.getDeathDetails(NAME), " ");
        assertEquals(deathNote.writeDetails(DETAILS1), true);
        assertEquals(deathNote.getDeathDetails(NAME), DETAILS1);
        deathNote.writeName(NAME2);
        try{
            Thread.sleep(SPLEEP2);
        }catch(InterruptedException e){
            throw new IllegalArgumentException();
        }
        deathNote.writeDetails(DETAILS2);
        assertEquals(deathNote.getDeathCause(NAME2), DETAILS2);
    }
}