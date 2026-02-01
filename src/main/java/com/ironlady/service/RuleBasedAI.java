package com.ironlady.service;

import java.sql.Date;
import java.time.LocalDate;

public class RuleBasedAI {

    /**
     * Suggests the status of an enrollment based on the start date.
     * if start date is in the future -> "Pending"
     * if start date is today or past -> "Active"
     */
    public static String suggestStatus(Date startDate) {
        if (startDate == null)
            return "Pending";

        LocalDate start = startDate.toLocalDate();
        LocalDate now = LocalDate.now();

        if (start.isAfter(now)) {
            return "Pending";
        } else {
            return "Active";
        }
    }

    /**
     * Suggests priority based on course name.
     * "Premium" courses get "High" priority.
     * Others get "Medium".
     */
    public static String suggestPriority(String courseName) {
        if (courseName == null || courseName.isEmpty())
            return "Low";

        String lowerName = courseName.toLowerCase();
        if (lowerName.contains("premium") || lowerName.contains("advanced") || lowerName.contains("masterclass")) {
            return "High";
        }
        return "Medium";
    }

    /**
     * Flags potential issues with the enrollment.
     * Checks for incomplete data or scheduling conflicts (mock logic).
     */
    public static String analyzeEnrollment(String courseName, Date startDate) {
        StringBuilder flags = new StringBuilder();

        if (startDate == null) {
            flags.append("Missing Start Date; ");
        } else if (startDate.toLocalDate().isBefore(LocalDate.now().minusDays(30))) {
            flags.append("Start Date is too far in the past; ");
        }

        if (courseName == null || courseName.length() < 3) {
            flags.append("Invalid Course Name; ");
        }

        if (flags.length() == 0) {
            return "OK";
        }
        return flags.toString();
    }
}
