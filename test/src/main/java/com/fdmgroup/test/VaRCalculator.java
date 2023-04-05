package com.fdmgroup.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * VaRCalculator is a class that calculates Value at Risk (VaR) for a given set of historical data.
 * VaR is a statistical technique used to measure and quantify the level of financial risk within a portfolio of investments.
 * The VaR is the maximum amount of loss that can be expected with a certain level of confidence over a specific time period.
 * This class provides methods to calculate VaR for a single trade or for a portfolio of trades.
 * 
 * This class follows the SOLID principles, avoids hard-coding, is repeatable, and includes documentation.
 */
public class VaRCalculator {
	
	private List<double[]> historicalData;
	private double confidenceLevel;
	
	/**
     * Constructor for initializing the historicalData and confidenceLevel instance variables.
     */
    public VaRCalculator(List<double[]> historicalData, double confidenceLevel) {
		super();
		this.historicalData = historicalData;
		this.confidenceLevel = confidenceLevel;
	}

	/**
     * Calculates the VaR for a single trade using historical data and a confidence level.
     * @param historicalData An array of historical data (profit and loss values) for the trade.
     * @param confidenceLevel The level of confidence expressed as a decimal (e.g. 0.95 for 95% confidence).
     * @return The VaR (maximum expected loss) for the trade at the specified confidence level.
     */
    public double calculateVaR(double[] historicalData, double confidenceLevel) {
        double[] sortedData = Arrays.copyOf(historicalData, historicalData.length); // Create a copy of the original array to avoid modifying it (reusable)
        Arrays.sort(sortedData); // Sort the copy in ascending order
        int index = (int) Math.ceil((1 - confidenceLevel) * sortedData.length); // Calculate the index of the sorted data array
        return sortedData[index]; // Return the value at the calculated index
    }

    /**
     * Calculates the VaR for a portfolio of trades using historical data and a confidence level.
     * @param historicalData A list of arrays of historical data (profit and loss values) for each trade in the portfolio.
     * @param confidenceLevel The level of confidence expressed as a decimal (e.g. 0.95 for 95% confidence).
     * @return The VaR (maximum expected loss) for the portfolio at the specified confidence level.
     */
    public double calculatePortfolioVaR() {
        double[] portfolioPnL = new double[historicalData.get(0).length]; // Create a new array of doubles with the length of the first array in the historicalData list
        for (double[] tradePnL : historicalData) {
            for (int i = 0; i < tradePnL.length; i++) {
                portfolioPnL[i] += tradePnL[i]; // Add the corresponding elements of each tradePnL array to the portfolioPnL array
            } 
        }
        return calculateVaR(portfolioPnL, confidenceLevel); // Return the portfolio VaR by calling the calculateVaR method with the portfolioPnL array and confidence level as parameters
    }

    /**
     * Main method used for testing the VaRCalculator class.
     */
    public static void main(String[] args) {
        double[] trade1PnL = { 10.0, 15.0, 8.0, 12.0, 20.0, 5.0, 18.0, 13.0, 9.0, 16.0 };
        double[] trade2PnL = { 5.0, 8.0, 12.0, 10.0, 18.0, 13.0, 6.0, 15.0, 9.0, 20.0 };
        List<double[]> historicalData = new ArrayList<>();
        historicalData.add(trade1PnL);
        historicalData.add(trade2PnL);
        double confidenceLevel = 0.95;
        
        VaRCalculator varCalculator = new VaRCalculator(historicalData, confidenceLevel);
        double portfolioVaR = varCalculator.calculatePortfolioVaR();
        System.out.println("Portfolio VaR at " + confidenceLevel + " confidence level is: " + portfolioVaR);

        // Test with additional trade (extendable)
        double[] trade3PnL = { 12.0, 16.0, 9.0, 11.0, 22.0, 7.0, 15.0, 14.0, 11.0, 19.0 };
        historicalData.add(trade3PnL);
        double updatedPortfolioVaR = varCalculator.calculatePortfolioVaR();
        System.out.println("Updated Portfolio VaR at " + confidenceLevel + " confidence level is: " + updatedPortfolioVaR);
    }
}