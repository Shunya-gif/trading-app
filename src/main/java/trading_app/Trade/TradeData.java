package trading_app.Trade;

import trading_app.Stock.StockValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TradeData {
    private String tickerCode;
    private String productName;
    private Side side;
    private long quantity;
    private BigDecimal unitPrice;
    private LocalDateTime tradedDateTime;
    private final LocalDateTime inputDateTime;
    TradeDataValidator tradeDataValidator = new TradeDataValidator();
    StockValidator stockValidator = new StockValidator();
    public TradeData(String tickerCode, String productName, Side side, long quantity, BigDecimal unitPrice, LocalDateTime tradedDateTime,LocalDateTime inputDateTime) {
        setTickerCode(tickerCode);
        setProductName(productName);
        this.side = side;
        setQuantity(quantity);
        this.unitPrice = unitPrice;
        this.tradedDateTime = tradedDateTime;
        this.inputDateTime = inputDateTime;
    }

    public String getTickerCode() {
        return tickerCode;
    }

    public void setTickerCode(String tickerCode) {
        if(tradeDataValidator.validateTickerCode(tickerCode)){
            this.tickerCode = tickerCode;
        }else{
            throw new IllegalArgumentException("エラー内容：銘柄コード"+tickerCode);
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if(stockValidator.validateProductName(productName)) {
            this.productName = productName;
        }else{
            throw new IllegalArgumentException("エラー内容：銘柄名"+productName);
        }
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        if(tradeDataValidator.validateQuantity(quantity)){
            this.quantity = quantity;
        }else{
            throw new IllegalArgumentException("エラー内容：取引数量"+quantity);
        }
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDateTime getTradedDateTime() {
        return tradedDateTime;
    }

    public void setTradedDateTime(LocalDateTime tradedDateTime) {
        this.tradedDateTime = tradedDateTime;
    }

    public LocalDateTime getInputDateTime() {
        return inputDateTime;
    }
}
