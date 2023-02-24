package org.example;

import java.util.List;

public class StocksPortfolio implements IStockmarketService {
    private List<Stock> stocks;
    private final IStockmarketService stockmarket;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double getTotalValue() {
        double total = 0;
        for (Stock stock : stocks) {
            total += stockmarket.lookUpPrice(stock.getLabel());
        }
        return total;
    }

    @Override
    public double lookUpPrice(String stockName) {
        return 0;
    }
}

