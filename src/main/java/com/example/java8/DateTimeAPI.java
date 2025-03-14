package com.example.java8;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * Demonstrates the new Date/Time API introduced in Java 8 (java.time package).
 */
public class DateTimeAPI {

    public static void main(String[] args) {
        // LocalDate - represents a date (year, month, day)
        System.out.println("--- LocalDate Examples ---");
        LocalDate today = LocalDate.now();
        System.out.println("Today: " + today);
        
        LocalDate specificDate = LocalDate.of(2023, Month.DECEMBER, 25);
        System.out.println("Christmas 2023: " + specificDate);
        
        LocalDate dateFromString = LocalDate.parse("2023-06-15");
        System.out.println("Parsed date: " + dateFromString);
        
        LocalDate tomorrow = today.plusDays(1);
        System.out.println("Tomorrow: " + tomorrow);
        
        LocalDate previousMonthSameDay = today.minus(1, ChronoUnit.MONTHS);
        System.out.println("One month ago: " + previousMonthSameDay);
        
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        System.out.println("Day of week: " + dayOfWeek);
        
        int dayOfMonth = today.getDayOfMonth();
        System.out.println("Day of month: " + dayOfMonth);
        
        boolean isLeapYear = today.isLeapYear();
        System.out.println("Is leap year? " + isLeapYear);
        
        // LocalTime - represents a time (hour, minute, second, nanosecond)
        System.out.println("\n--- LocalTime Examples ---");
        LocalTime now = LocalTime.now();
        System.out.println("Current time: " + now);
        
        LocalTime specificTime = LocalTime.of(13, 30, 0);
        System.out.println("Specific time (1:30 PM): " + specificTime);
        
        LocalTime parsedTime = LocalTime.parse("15:45:30");
        System.out.println("Parsed time: " + parsedTime);
        
        LocalTime twoHoursLater = now.plusHours(2);
        System.out.println("Two hours later: " + twoHoursLater);
        
        // LocalDateTime - represents both date and time
        System.out.println("\n--- LocalDateTime Examples ---");
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Current date and time: " + currentDateTime);
        
        LocalDateTime christmasEve = LocalDateTime.of(2023, Month.DECEMBER, 24, 19, 0);
        System.out.println("Christmas Eve 2023 at 7 PM: " + christmasEve);
        
        LocalDateTime dateTimeFromDateAndTime = LocalDateTime.of(today, now);
        System.out.println("Combined date and time: " + dateTimeFromDateAndTime);
        
        // ZonedDateTime - date and time with timezone
        System.out.println("\n--- ZonedDateTime Examples ---");
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("Current date and time with zone: " + zonedDateTime);
        
        ZoneId newYorkZone = ZoneId.of("America/New_York");
        ZonedDateTime newYorkTime = ZonedDateTime.now(newYorkZone);
        System.out.println("Current time in New York: " + newYorkTime);
        
        ZonedDateTime tokyoTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        System.out.println("Current time in Tokyo: " + tokyoTime);
        
        // Period - represents a date-based amount of time
        System.out.println("\n--- Period Examples ---");
        Period tenDays = Period.ofDays(10);
        System.out.println("Ten days period: " + tenDays);
        
        Period twoMonthsAndFiveDays = Period.of(0, 2, 5);
        System.out.println("Period of 2 months and 5 days: " + twoMonthsAndFiveDays);
        
        LocalDate newDate = today.plus(twoMonthsAndFiveDays);
        System.out.println("Today plus 2 months and 5 days: " + newDate);
        
        Period periodBetween = Period.between(today, specificDate);
        System.out.println("Period between today and Christmas: " + periodBetween);
        
        // Duration - represents a time-based amount of time
        System.out.println("\n--- Duration Examples ---");
        Duration twoHours = Duration.ofHours(2);
        System.out.println("Two hours duration: " + twoHours);
        
        Duration tenMinutes = Duration.ofMinutes(10);
        System.out.println("Ten minutes duration: " + tenMinutes);
        
        LocalTime timePlusDuration = now.plus(twoHours);
        System.out.println("Current time plus two hours: " + timePlusDuration);
        
        // Instant - represents a point in time (timestamp)
        System.out.println("\n--- Instant Examples ---");
        Instant instant = Instant.now();
        System.out.println("Current instant: " + instant);
        
        Instant instantFromEpoch = Instant.ofEpochSecond(1500000000);
        System.out.println("Instant from epoch second: " + instantFromEpoch);
        
        // Formatting and Parsing
        System.out.println("\n--- Formatting Examples ---");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        System.out.println("Formatted date-time: " + formattedDateTime);
        
        DateTimeFormatter italianFormatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)
                .withLocale(Locale.ITALIAN);
        String italianDate = today.format(italianFormatter);
        System.out.println("Date in Italian format: " + italianDate);
        
        // Temporal adjusters
        System.out.println("\n--- Temporal Adjuster Examples ---");
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("First day of current month: " + firstDayOfMonth);
        
        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("Last day of current year: " + lastDayOfYear);
        
        LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println("Date of next Monday: " + nextMonday);
        
        // Comparison operations
        System.out.println("\n--- Comparison Examples ---");
        boolean isBefore = today.isBefore(tomorrow);
        System.out.println("Is today before tomorrow? " + isBefore);
        
        boolean isAfter = tomorrow.isAfter(today);
        System.out.println("Is tomorrow after today? " + isAfter);
        
        // Clock
        System.out.println("\n--- Clock Examples ---");
        Clock clock = Clock.systemDefaultZone();
        System.out.println("Default zone clock: " + clock);
        
        Clock utcClock = Clock.systemUTC();
        System.out.println("UTC clock: " + utcClock);
        
        // OffsetDateTime
        System.out.println("\n--- OffsetDateTime Examples ---");
        ZoneOffset offset = ZoneOffset.of("+02:00");
        OffsetDateTime offsetDateTime = OffsetDateTime.of(currentDateTime, offset);
        System.out.println("Date-time with offset: " + offsetDateTime);
    }
}
