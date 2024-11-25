package trading_app.Trade;

public enum Side {
    Sell("S"),
    Buy("B");
    private final String shortName;
    Side(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
