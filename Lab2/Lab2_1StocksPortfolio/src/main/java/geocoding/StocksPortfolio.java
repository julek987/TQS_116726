package geocoding;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    private List<Stock> stocks = new ArrayList<Stock>();
    private final IStockmarketService stockmarket;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
    }


    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double getTotalValue() {
        double total = 0.0;
        for (Stock stock : stocks) {
            total += stock.getQuantity() * stockmarket.lookUpPrice(stock.getLabel());
        }
        return total;
    }
}

