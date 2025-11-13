package it.unibo.deathnote.impl;

/**
 * Represents a victim written in the DeathNote.
 */

public final class Victim {
    private String cause;
    private String details; 
    private int time;

    /**
     * Creates a new Victim with empty fields.
     */

    public Victim() {
        this.cause = null;
        this.details = null;
        this.time = 0;
    }

    /**
     * Returns the cause of death.
     * 
     * @return the cause of death
     */

    public String getCause() {
        return cause;
    }

    /**
     * Returns the details of the death.
     * 
     * @return the death details
     */

    public String getDetails() {
        return details;
    }

    /**
     * Returns the recorded time.
     * 
     * @return the time in milliseconds
     */

    public int getTime() {
        return time;
    }

    /**
     * Sets the cause of death.
     * 
     * @param cause the cause of death
     */

    public void setCause(final String cause) {
        this.cause = cause;
    }

    /**
     * Sets the details of the death.
     * 
     * @param details the death details
     */

    public void setDetails(final String details) {
        this.details = details;
    }

    /**
     * Sets the time of the event.
     * 
     * @param time the time in milliseconds
     */

    public void setTime(final int time) {
        this.time = time;
    }
 }
