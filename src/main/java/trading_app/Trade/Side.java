package trading_app.Trade;

public enum Side {
    Sell("S","SELL"),
    Buy("B","BUY");
    private final String shortName;
    private final String fullName;

    Side(String shortName, String fullName) {
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }
    public String getFullName() {
        return fullName;
    }

    public static Side fromOtherName(String userInput) {
        for (Side side : Side.values()) {
            if (side.getShortName().equals(userInput)||side.getFullName().equals(userInput)){
                return side;
            }
        }
        throw new IllegalArgumentException(userInput+"に対応する取引はありません。");
    }


}
