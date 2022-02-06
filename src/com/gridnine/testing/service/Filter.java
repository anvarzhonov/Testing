package com.gridnine.testing.service;

import com.gridnine.testing.entities.Flight;

import java.util.List;


public interface Filter {
    List<Flight> departureBeforeLocalDateTimeNow(List<Flight> flights);

    List<Flight> arrivalBeforeDeparture(List<Flight> flights);

    List<Flight> totalTimeSpentOnEarthIsMoreTwoHours(List<Flight> flights);
}
