package trading_app.Stock;

public enum Market {
    Prime("P","プライム","PRIME"),
    Standard("S","スタンダード","STANDARD"),
    Growth("G","グロース","GROWTH"),
    TokyoPRO("PRO","Tokyo PRO","TOKYO PRO");
    private final String shortName;
    private final String japannedName;
    private final String fullName;

    Market(String shortName, String japannedName,String fullName) {
        this.shortName = shortName;
        this.japannedName = japannedName;
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }
    public String getJapannedName() {
        return japannedName;
    }
    public String getFullName() {
        return fullName;
    }

    public static Market fromOtherName(String userInput) {
        for (Market market : Market.values()) {
            if (market.getShortName().equals(userInput)||market.getFullName().equals(userInput)) {
                return market;
            }
        }
        throw new IllegalArgumentException(userInput+"に対応する上場市場はありません。");
    }



}
