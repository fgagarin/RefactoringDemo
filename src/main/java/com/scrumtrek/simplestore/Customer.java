package com.scrumtrek.simplestore;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


	public void addRental(Rental arg){
		rentals.add(arg);
	}

	public String calculateStatement()
	{
		double totalAmount = 0;
		int frequentRenterPoints = 0;
				
		String result = "Rental record for " + name + "\n";
		
		for(Rental currentRental: rentals) {
			double thisAmount = calculateAmmount(currentRental);

			// Add frequent renter points
			frequentRenterPoints = addFrequentRenterPoints(frequentRenterPoints, currentRental);

			// Show figures for this rental
			result += "\t" + currentRental.getMovie().getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}

		// Add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points.";
		return result;
	}

	int addFrequentRenterPoints(int frequentRenterPoints, Rental each) {
		frequentRenterPoints++;
		if ((each.getMovie().getPriceCode() == PriceCodes.NewRelease) && (each.getDaysRented() > 1)) {
            frequentRenterPoints ++;
        }
		return frequentRenterPoints;
	}

	double calculateAmmount(Rental each) {
		switch(each.getMovie().getPriceCode()) {
            case Regular:
				return calculateRegularAmount(each);

            case NewRelease:
                return calculateNewReleaseAmount(each);

            case Childrens:
				return calculateChildrenAmount(each);

			default:
				return 0;
        }
	}

	double calculateNewReleaseAmount(Rental rental) {
		return rental.getDaysRented() * 3;
	}

	double calculateChildrenAmount(Rental rental) {
		double thisAmount = 1.5;
		if (rental.getDaysRented() > 3) {
            thisAmount = (rental.getDaysRented() - 3) * 1.5;
        }
		return thisAmount;
	}

	double calculateRegularAmount(Rental rental) {
		double thisAmount = 2;
		if (rental.getDaysRented() > 2) {
            thisAmount += (rental.getDaysRented() - 2) * 1.5;
        }
		return thisAmount;
	}
}

