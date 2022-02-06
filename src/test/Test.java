package test;

import com.gridnine.testing.entities.Flight;
import com.gridnine.testing.entities.Segment;
import com.gridnine.testing.service.Filter;
import com.gridnine.testing.service.FilterImpl;
import org.junit.Assert;
import org.junit.Before;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {
    private static final LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

    private static final Segment segment1 = new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2));
    private static final Segment segment2 = new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4));
    private static final Segment segment3 = new Segment(threeDaysFromNow.minusDays(3), threeDaysFromNow);
    private static final Segment segment4 = new Segment(threeDaysFromNow, threeDaysFromNow.minusHours(6));
    private static final Segment segment5 = new Segment(threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7));

    //A normal flight
    private static final Flight flight1 = new Flight(Arrays.asList(segment1, segment2));
    //A flight departing in the past
    private static final Flight flight2 = new Flight(Collections.singletonList(segment3));
    //A flight that arrives earlier than it left
    private static final Flight flight3 = new Flight(Collections.singletonList(segment4));
    //A flight with more than two hours ground time
    private static final Flight flight4 = new Flight(Arrays.asList(segment1, segment2, segment5));

    private static final List<Flight> flights = Arrays.asList(flight1, flight2, flight3, flight4);

    private Filter filter;

    @Before
    public void setUp() {
        filter = new FilterImpl();
    }

    @org.junit.Test
    public void filterDepartureBeforeLocalDateTimeNow() {
        List<Flight> expected = Arrays.asList(flight1, flight3, flight4);
        List<Flight> actual = filter.departureBeforeLocalDateTimeNow(flights);

        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void filterFlightsDepartsBeforeArrival() {
        List<Flight> expected = Arrays.asList(flight1, flight2, flight4);
        List<Flight> actual = filter.arrivalBeforeDeparture(flights);

        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void filterTotalTimeSpentOnEarthIsMoreTwoHours() {
        List<Flight> expected = Arrays.asList(flight1, flight2, flight3);
        List<Flight> actual = filter.totalTimeSpentOnEarthIsMoreTwoHours(flights);

        Assert.assertEquals(expected, actual);
    }
}
