import java.time.LocalDate;

public class Stock {
    private String tickerCode;
    private String productName;
    private Market exchangedMarket;
    private long sharesIssued;
    private LocalDate listedDate;
    StockValidator stockValidator = new StockValidator();

    public Stock(String tickerCode, String productName, Market exchangedMarket, long sharesIssued, LocalDate listedDate) {
        setTickerCode(tickerCode);
        setProductName(productName);
        this.exchangedMarket = exchangedMarket;
        setSharesIssued(sharesIssued);
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

    public void setTickerCode(String tickerCode) {
        if(stockValidator.validateTickerCode(tickerCode)) {
            this.tickerCode = tickerCode;
        }else {
            throw new IllegalArgumentException("エラー内容：銘柄コード" + tickerCode);
        }
    }

    public void setProductName(String productName) {
        if (stockValidator.validateProductName(productName)) {
            this.productName = productName;
        }else {
            throw new IllegalArgumentException("エラー内容：銘柄名" + productName);
        }
    }

    public void setSharesIssued(long sharesIssued) {
        if (stockValidator.validateSharesIssued(sharesIssued)) {
            this.sharesIssued = sharesIssued;
        }else{
        throw new IllegalArgumentException("エラー内容：発行済み株式総数"+sharesIssued);
        }
    }

    public void setListedDate(LocalDate listedDate) {
        this.listedDate = listedDate;
    }
}
