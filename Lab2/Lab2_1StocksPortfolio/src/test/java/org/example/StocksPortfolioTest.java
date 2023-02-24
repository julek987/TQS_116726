package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StocksPortfolioTest {

    @Mock
    private IStockmarketService mockedStockmarket = mock(IStockmarketService.class);
    @Mock
    private Stock mockedStock = mock(Stock.class);

    @InjectMocks
    private StocksPortfolio stocksPortfolio = new StocksPortfolio(mockedStockmarket);

    @Test
    void getTotalValue() {

        when(mockedStockmarket.lookUpPrice("Lena sp. z o.o.")).thenReturn(21.37);
        when(mockedStockmarket.lookUpPrice("Juleczek S.A.")).thenReturn(0.24);
        assertEquals(stocksPortfolio.getTotalValue(), 0);
        verify(mockedStockmarket.lookUpPrice("Lena sp. z o.o."));
        verify(mockedStockmarket.lookUpPrice("Juleczek sp. z o.o."));
    }
}