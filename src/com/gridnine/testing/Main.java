package com.gridnine.testing;

import com.gridnine.testing.entities.Flight;
import com.gridnine.testing.entities.FlightBuilder;
import com.gridnine.testing.service.Filter;
import com.gridnine.testing.service.FilterImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println();
        List<Flight> flights = FlightBuilder.createFlights();
        Filter filter = new FilterImpl();

        List<Flight> flights1 = filter.departureBeforeLocalDateTimeNow(flights);
        List<Flight> flights2 = filter.arrivalBeforeDeparture(flights);
        List<Flight> flights3 = filter.totalTimeSpentOnEarthIsMoreTwoHours(flights);

        printFlights(flights);
        printFlights(flights1);
        printFlights(flights2);
        printFlights(flights3);


    }

    private static void printFlights(List<Flight> flights) {
        flights.forEach(System.out::println);
        System.out.println("============================");
    }
}
