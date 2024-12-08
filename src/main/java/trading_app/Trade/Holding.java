package trading_app.Trade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class Holding {
        private String productName;
        private  long quantity = 0;
        private BigDecimal averageUnitPrice = BigDecimal.ZERO;
        private BigDecimal realisedProfitAndLoss= BigDecimal.ZERO;
        private LocalDateTime latestTradeDateTime = LocalDateTime.MIN;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getQuantity() {
        return quantity;
    }


    public BigDecimal getAverageUnitPrice() {
        return averageUnitPrice;
    }


    public BigDecimal getRealisedProfitAndLoss() {
        return realisedProfitAndLoss;
    }


    public LocalDateTime getLatestTradeDateTime() {
        return latestTradeDateTime;
    }

    public void updateHolding(TradeData trade) {
            if (trade.getSide() == Side.Buy) {
                BigDecimal totalCost = averageUnitPrice.multiply(BigDecimal.valueOf(quantity)).add(trade.getUnitPrice().multiply(BigDecimal.valueOf(trade.getQuantity())));
                quantity += trade.getQuantity();
                averageUnitPrice = quantity>0
                        ?totalCost.divide(BigDecimal.valueOf(quantity), RoundingMode.HALF_UP)
                        :BigDecimal.ZERO;
            } else if (trade.getSide() == Side.Sell) {
                BigDecimal profit = BigDecimal.valueOf(trade.getQuantity()).multiply(trade.getUnitPrice().subtract(getAverageUnitPrice()));
                realisedProfitAndLoss = realisedProfitAndLoss.add(profit);
                quantity -= trade.getQuantity();
                if (quantity == 0) {
                    resetAveragePrice();
                }
            }
            latestTradeDateTime = trade.getTradedDateTime();
        }
    private void resetAveragePrice() {
        this.averageUnitPrice = BigDecimal.ZERO;
    }
    public BigDecimal calculateValuation(BigDecimal marketPrice) {
        return BigDecimal.valueOf(quantity).multiply(marketPrice);
    }

    public BigDecimal calculateUnrealizedPnL(BigDecimal marketPrice) {
        BigDecimal valuation = calculateValuation(marketPrice);
        BigDecimal acquisitionCost = BigDecimal.valueOf(quantity).multiply(averageUnitPrice);
        return valuation.subtract(acquisitionCost);
    }

}


