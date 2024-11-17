import java.time.LocalDate;

public class Stock {
    private String tickerCode;
    private String productName;
    private Market exchangedMarket;
    private long sharesIssued;
    private LocalDate listedDate;

    public Stock(String tickerCode, String productName, Market exchangedMarket, long sharesIssued, LocalDate listedDate) {
        this.tickerCode = tickerCode;
        this.productName = productName;
        this.exchangedMarket = exchangedMarket;
        this.sharesIssued = sharesIssued;
        this.listedDate = listedDate;
    }

    public String getTickerCode() {
        return tickerCode;
    }

    public String getProductName() {
        return productName;
    }

    public Market getExchangedMarket() {
        return exchangedMarket;
    }

    public long getSharesIssued() {
        return sharesIssued;
    }

    public LocalDate getListedDate() {
        return listedDate;
    }
}
