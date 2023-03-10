package geocoding;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StocksPortfolioTest {

    @Mock
    IStockmarketService mockedStockmarket = Mockito.mock(IStockmarketService.class);

    @InjectMocks
    StocksPortfolio stocksPortfolio;


    @Test
    void getTotalValue() {
        when(mockedStockmarket.lookUpPrice("Lena sp. z o.o.")).thenReturn(21.3);
        when(mockedStockmarket.lookUpPrice("Juleczek S.A.")).thenReturn(0.25);

        stocksPortfolio.addStock(new Stock("Lena sp. z o.o.", 10));
        stocksPortfolio.addStock(new Stock("Juleczek S.A.", 4));

        assertEquals(stocksPortfolio.getTotalValue(), 214);

        verify(mockedStockmarket).lookUpPrice("Lena sp. z o.o.");
    }
}