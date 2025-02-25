package task2codeaplha;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.*;

public class tasktwo {
    static class Stock {
        String sign;
        double price;

        public Stock(String sign, double price) {
            this.sign = sign;
            this.price = price;
        }
    }

    static class Portfolio {
        Map<String, Integer> holdings = new HashMap<>();
        double balance = 10000000.00;

        public void buyStock(Stock stock, int quantity) {
            double totalCost = stock.price * quantity;
            if (balance >= totalCost) {
                holdings.put(stock.sign, holdings.getOrDefault(stock.sign, 0) + quantity);
                balance -= totalCost;
                System.out.println("Bought " + quantity + " shares of " + stock.sign + " at " + stock.price + " each.");
            } else {
                System.out.println("Insufficient balance to buy " + quantity + " shares.");
            }
        }

        public void sellStock(Stock stock, int quantity) {
            int owned = holdings.getOrDefault(stock.sign, 0);
            if (owned >= quantity) {
                holdings.put(stock.sign, owned - quantity);
                balance += stock.price * quantity;
                System.out.println("Sold " + quantity + " shares of " + stock.sign + " at " + stock.price + " each.");
            } else {
                System.out.println("can't own enough shares of " + stock.sign + " to sell.");
            }
        }

        public void showPortfolio(Map<String, Stock> marketData) {
            double totalValue = balance;
            System.out.println("\nYour Portfolio:");
            for (String symbol : holdings.keySet()) {
                int quantity = holdings.get(symbol);
                double stockValue;
                stockValue = marketData.get(symbol).price * quantity;
                totalValue += stockValue;
                System.out.println(symbol + ": " + quantity + " shares, Value: " + stockValue);
            }
            System.out.println("Cash Balance: " + balance);
            System.out.println("Total Portfolio Value: " + totalValue);
        }
    }

    public static void main(String[] args) {

        Stock apple = new Stock("AAPL", 150.00);
        Stock google = new Stock("GOOG", 2800.00);
        Stock reliance = new Stock("RELIANCE", 900.00);

        Map<String, Stock> marketData = new HashMap<>();
        marketData.put("AAPL", apple); // Corrected: used "AAPL" instead of "APPLE"
        marketData.put("GOOG", google); // Corrected: used "GOOG" instead of "GOOGLE"
        marketData.put("RELIANCE", reliance);

        Portfolio myPortfolio = new Portfolio();
        myPortfolio.buyStock(apple, 990);
        myPortfolio.buyStock(reliance, 120);
        myPortfolio.sellStock(apple, 2000);


        myPortfolio.showPortfolio(Collections.<String, Stock>unmodifiableMap(marketData));
    }
}



