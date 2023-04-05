# VaRCalculator

VaRCalculator is a Java application that calculates the Value at Risk (VaR) of a portfolio of trades using historical simulation. 

## Dependencies

VaRCalculator requires the following dependencies:

- Java 8 or higher
- Apache Maven 3.6.0 or higher
- JUnit Jupiter 5.7.0

## How to run

To run VaRCalculator, follow these steps:

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Run `javac VaRCalculator.java` to compile the source code.
4. Run `java VaRCalculator` to execute the program.
5. Follow/alter the examplar code in the main method to input the desired data.

## Input data

VaRCalculator requires the following input data:

- The historical PnL data for each trade in the portfolio, as an array of doubles.
- The confidence level for the VaR calculation, as a double between 0 and 1.

## Output

VaRCalculator will output the VaR of the portfolio at the specified confidence level, as a double.
