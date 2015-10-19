package com.scrumtrek.simplestore;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static com.scrumtrek.simplestore.PriceCodes.Childrens;
import static com.scrumtrek.simplestore.PriceCodes.NewRelease;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by fgagarin on 10/19/15.
 */
public class CustomerTest {

    @Test
    public void testAddFrequentRenterPointsNotNewRelease() throws Exception {
        Customer customer = new Customer("Ivanov");
        Movie movie = new Movie("Scream", Childrens);
        Rental rental = new Rental(movie, 1);
        int result = customer.addFrequentRenterPoints(0, rental);
        assertThat(result, equalTo(1));
    }

    @Test
    public void testAddFrequentRenterPointsNewRelease() throws Exception {
        Customer customer = new Customer("Ivanov");
        Movie movie = new Movie("Scream", NewRelease);
        Rental rentalOneDay = new Rental(movie, 1);
        int result = customer.addFrequentRenterPoints(0, rentalOneDay);
        assertThat(result, equalTo(1));

        int startFrequentRentalPoints = 5;
        Rental rentalMoreThanOneDay = new Rental(movie, 2);
        result = customer.addFrequentRenterPoints(startFrequentRentalPoints, rentalMoreThanOneDay);
        assertThat(result, equalTo(startFrequentRentalPoints + 2));
    }
}