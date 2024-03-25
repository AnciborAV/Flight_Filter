package ru.aav;

import java.time.LocalDateTime;
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
    }
}
