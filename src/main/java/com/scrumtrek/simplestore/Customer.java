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
			double thisAmount = getThisAmount(currentRental);

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

		// Add bonus for a two-day new-release rental
		if ((each.getMovie().getPriceCode() == PriceCodes.NewRelease) && (each.getDaysRented() > 1))
        {
            frequentRenterPoints ++;
        }
		return frequentRenterPoints;
	}

	double getThisAmount(Rental each) {
		double thisAmount = 0;

		// Determine amounts for each line
		switch(each.getMovie().getPriceCode()) {
            case Regular:
                thisAmount += 2;
                if (each.getDaysRented() > 2)
                {
                    thisAmount += (each.getDaysRented() - 2) * 1.5;
                }
                break;

            case NewRelease:
                thisAmount += each.getDaysRented() * 3;
                break;

            case Childrens:
                thisAmount += 1.5;
                if (each.getDaysRented() > 3)
                {
                    thisAmount = (each.getDaysRented() - 3) * 1.5;
                }
                break;
        }
		return thisAmount;
	}
}

