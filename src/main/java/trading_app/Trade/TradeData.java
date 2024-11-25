package trading_app.Trade;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TradeData {
    private String tickerCode;
    private String productName;
    private Side side;
    private long quantity;
    private BigDecimal unitPrice;
    private LocalDateTime tradedDateTime;
    private LocalDateTime inputDateTime;

    public TradeData(String tickerCode, String productName, Side side, long quantity, BigDecimal unitPrice, LocalDateTime tradedDateTime) {
        this.tickerCode = tickerCode;
        this.productName = productName;
        this.side = side;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.tradedDateTime = tradedDateTime;
        this.inputDateTime = LocalDateTime.now();
    }
}
