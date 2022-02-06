package com.gridnine.testing.service;

import com.gridnine.testing.entities.Flight;
import com.gridnine.testing.entities.Segment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FilterImpl implements Filter {
    @Override
    public List<Flight> departureBeforeLocalDateTimeNow(List<Flight> flights) {
        List<Flight> result = new ArrayList<>();

        flights
            .forEach(flight -> flight.getSegments()
                .stream()
                .filter(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now())).limit(1)
                .forEach(s -> result.add(flight)));

        return result;
    }

    @Override
    public List<Flight> arrivalBeforeDeparture(List<Flight> flights) {
        List<Flight> result = new ArrayList<>();
        flights
            .forEach(flight -> flight.getSegments()
                .stream()
                .filter(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())).limit(1)
                .forEach(el -> result.add(flight)));

        return result;
    }

    @Override
    public List<Flight> totalTimeSpentOnEarthIsMoreTwoHours(List<Flight> flights) {
        List<Flight> result = new ArrayList<>();

        for (Flight flight : flights) {
            List<Segment> segments = flight.getSegments();

            if (segments.size() <= 1) result.add(flight);
            else {
                int countHours = 0;

                for (int i = 0; i < segments.size() - 1; i++)
                    countHours += ChronoUnit.HOURS.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate());

                if (countHours <= 2)
                    result.add(flight);
            }
        }
        return result;
    }
}
