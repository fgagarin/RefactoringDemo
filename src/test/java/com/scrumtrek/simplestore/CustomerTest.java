package com.scrumtrek.simplestore;

import org.junit.Test;

import static com.scrumtrek.simplestore.PriceCodes.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

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


    @Test
    public void testCalculateNewReleaseAmount() throws Exception {
        Customer customer = new Customer("Ivanov");
        Movie movie = new Movie("Scream", NewRelease);
        Rental rentalOneDay = new Rental(movie, 1);

        double result = customer.calculateNewReleaseAmount(rentalOneDay);
        assertThat(result, equalTo(3.0));
    }

    @Test
    public void testCalculateChildrenAmount() throws Exception {
        Customer customer = new Customer("Ivanov");
        Movie movie = new Movie("Scream", Childrens);
        Rental rentalOneDay = new Rental(movie, 1);

        double result = customer.calculateChildrenAmount(rentalOneDay);
        assertThat(result, equalTo(1.5));

        Rental rentalMoreThanThreeDays = new Rental(movie, 5);
        result = customer.calculateChildrenAmount(rentalMoreThanThreeDays);
        assertThat(result, equalTo(3.0));
    }

    @Test
    public void testCalculateRegularAmount() throws Exception {
        Customer customer = new Customer("Ivanov");
        Movie movie = new Movie("Scream", Regular);
        Rental rentalOneDay = new Rental(movie, 1);

        double result = customer.calculateRegularAmount(rentalOneDay);
        assertThat(result, equalTo(2.0));

        Rental rentalMoreThanTwoDays = new Rental(movie, 3);
        result = customer.calculateRegularAmount(rentalMoreThanTwoDays);
        assertThat(result, equalTo(3.5));
    }
}