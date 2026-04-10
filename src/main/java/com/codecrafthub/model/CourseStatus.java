package com.codecrafthub.model;

/**
 * Defines allowed course status values.
 * These values MUST match the required JSON values exactly.
 */

public enum CourseStatus {

    NOT_STARTED("Not Started"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");

    private final String value;

    CourseStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Validate status string
     */
    public static boolean isValid(String status) {
        for (CourseStatus s : values()) {
            if (s.value.equals(status)) {
                return true;
            }
        }
        return false;
    }
}