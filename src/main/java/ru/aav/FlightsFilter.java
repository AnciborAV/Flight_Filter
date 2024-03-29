package ru.aav;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightsFilter {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        var now = LocalDateTime.now();
        System.out.println(now);

        // 1
        var noFlightsBeforeNow =
                flights.stream()
                        .filter(f1 -> f1.getSegments().stream()
                                              .noneMatch(seg -> seg.getDepartureDate()
                                                                        .isBefore(now)))
                        .toList();
        System.out.println(noFlightsBeforeNow);
        System.out.println("----------------------------");

        // 2
        var flightsWithArrivalBeforeDeparture =
                flights.stream()
                        .filter(f1 -> f1.getSegments().stream()
                                              .noneMatch(seg -> seg.getDepartureDate()
                                                                        . isAfter(seg.getArrivalDate())))
                        .toList();
        System.out.println(flightsWithArrivalBeforeDeparture);
        System.out.println("----------------------------");

        // 3
        var notTransferTimeMoreThanTwoHours = new ArrayList<Flight>();
        for (Flight f : flights) {
            var transferSum = diff(f);
            System.out.println(f + " gap " + transferSum);
            if (transferSum < 2L) {
                notTransferTimeMoreThanTwoHours.add(f);
            }
        }
        System.out.println(notTransferTimeMoreThanTwoHours);
        System.out.println("----------------------------");
    }
    private static Long diff(Flight f) {
        var result = 0L;
        if (f.getSegments().size() == 1) {
            return 0L;
        }
        for (int i = 0; i < f.getSegments().size() - 1; i++) {
            var arrivalDate = f.getSegments().get(i).getArrivalDate();
            var departureDate = f.getSegments().get(i + 1).getDepartureDate();
            var gap = Duration.between(arrivalDate, departureDate).toHours();
            result += gap;
        }
        return result;
    }
}
