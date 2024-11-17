public enum Market {
    Prime("P","プライム"),
    Standard("S","スタンダード"),
    Growth("G","グロース"),
    TokyoPRO("PRO","Tokyo PRO");
    private final String shortName;
    private final String japannedName;

    Market(String shortName, String japannedName) {
        this.shortName = shortName;
        this.japannedName = japannedName;
    }

    public String getShortName() {
        return shortName;
    }
    public String getJapannedName() {
        return japannedName;
    }
    public static Market fromShortName(String shortName) {
        for (Market market : Market.values()) {
            if (market.getShortName().equals(shortName)) {
                return market;
            }
        }
        throw new IllegalArgumentException("No enum constant with shortName: " + shortName);
    }

}
