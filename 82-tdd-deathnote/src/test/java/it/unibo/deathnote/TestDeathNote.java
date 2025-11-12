package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import it.unibo.deathnote.impl.DeathNoteImpl;
import it.unibo.deathnote.api.DeathNote;

class TestDeathNote {
    private static final int RULE=5; 
    private DeathNote deadNote;

    @BeforeEach
    void setUp() {
        deadNote= new DeathNoteImpl();
    }
    @Test
    public void testRule() {
        int[] array=new int[]{0, -RULE};
        for (int x : array){
            try{
                deadNote.getRule(x);
            }catch(Exception e){
                assertEquals(new IllegalArgumentException(), e);
            }
        }  
    }


    
}