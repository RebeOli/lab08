package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

/**
 * Implementation of `DeathNote`.
 */

public final class DeathNoteImpl implements DeathNote {
    private static final String GENERAL_CAUSE = "Heart Attack";
    private static final long MILLISECONDS1 = 40;
    private static final long MILLISECONDS2 = 6040;
    private long timeName;
    private long now; //NOPMD
    private long timeCause;
    private String lastName;
    private final Map<String, Victim> map = new HashMap<>();

    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNumber < 1 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("the given rule number is smaller than 1 or larger than the number of rules");
        }
        return RULES.get(ruleNumber);
    }

    @Override
    public void writeName(final String name) {
        if (name == null) {
            throw new NullPointerException("the given name is null"); //NOPMD
        }
        map.put(name, new Victim());
        this.lastName = name; 
        timeName = System.currentTimeMillis();
    }

    @Override
    public boolean writeDeathCause(final String cause) {
        if (cause == null || map.isEmpty()) {
            throw new IllegalStateException("the given cause is null or there is no name written");
        }
        now = System.currentTimeMillis();
        if ((now - timeName) <= MILLISECONDS1) {
            map.get(lastName).setCause(cause); //setta la causa al primo nome
            timeCause = now;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean writeDetails(final String details) {
        if (details == null || map.isEmpty()) {
            throw new IllegalStateException("the given cause is null or there is no name written");
        }
        now = System.currentTimeMillis();
        if ((now - timeCause) <= MILLISECONDS2) {
            map.get(lastName).setDetails(details); //setta la causa al primo nome
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDeathCause(final String name) {
        if (!this.isNameWritten(name)) {
            throw new IllegalArgumentException("the provider name is not written in this DeathNote");
        }
        final String cause = map.get(name).getCause();
        if (cause == null) {
           return GENERAL_CAUSE;
        } else {
            return cause;
        }
    }

    @Override
    public String getDeathDetails(final String name) {
        if (!this.isNameWritten(name)) {
            throw new IllegalArgumentException("the provider name is not written in this DeathNote");
        }
        final String details = map.get(name).getDetails();
        if (details == null) {
           return " ";
        } else {
            return details;
        }
    }

    @Override
    public boolean isNameWritten(final String name) {
        return map.containsKey(name);
    }
}
