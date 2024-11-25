package trading_app.Stock;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class StockRegistrant {
    StockValidator stockValidator  =new StockValidator();
    public String registerTickerCode(){
        Scanner scanner = new Scanner(System.in);
        StockManager stockManager = new StockManager();
        String userInput;
        while(true){
            System.out.printf("新規に登録する4桁の銘柄コードを入力してください。%n" +
                    "1,3文字目:0-9の数字%n" +
                    "2,4文字目：0-9の数字またはB,E,I,O,V,Q,Zを除いた19種類の英大文字%n>");
            userInput = scanner.nextLine();
            if(stockValidator.validateTickerCode(userInput)){
                if(!stockManager.isAlreadyRegistered(userInput)){
                    return userInput;
                }
                System.out.println("銘柄コード"+userInput+"は登録済みです。");
            }else {
                System.out.println(userInput + "は銘柄コードの要件を満たしていません。");
            }
        }
    }
    public String registerProductName(){
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true){
            System.out.printf("登録する銘柄名を英語で入力してください。%n>");
            userInput = scanner.nextLine().replace(",","");
            if(stockValidator.validateProductName(userInput)){
                return userInput;
            }else{
                System.out.println(userInput+"は銘柄名の要件を満たしていません。");
            }
        }
    }
    public Market registerExchangedMarket(){
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true) {
            try{
            System.out.printf("銘柄の上場市場を入力してください。%n" +
                    "プライム:PまたはPrime" +
                    "スタンダード：SまたはStandard%n" +
                    "グロース：GまたはGrowth%n" +
                    "Tokyo PRO:PROまたはTokyo PRO%n>");
            userInput = scanner.nextLine().toUpperCase();
            return Market.fromOtherName(userInput);
        }catch (IllegalArgumentException e){
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
    public long registerSharesIssued(){
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while(true){
            System.out.printf("発行済み株式総数を1~999,999,999,999までの整数で入力してください。%n>");
            userInput = scanner.nextLine().replace(",","");
            try{
                long parsedUserInput = Long.parseLong(userInput);
                if(stockValidator.validateSharesIssued(parsedUserInput)){
                    return parsedUserInput;
                }else{
                    System.out.println("発行済み株式数は1～999,999,999,999までの整数である必要があります。");
                }
            }catch (NumberFormatException e ){
                System.out.println(userInput+"を整数に変換できません。");
            }
        }
    }
    public LocalDate registerListedDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true) {
            System.out.printf("企業の上場日をyyyy/MM/dd形式で入力してください。%n>");
            userInput = scanner.nextLine();
            try {
                LocalDate parsedUserInput = LocalDate.parse(userInput, dateTimeFormatter);
                if (parsedUserInput.isBefore(LocalDate.now())) {
                    return parsedUserInput;
                } else {
                    System.out.println("未来の日付が入力されています。");
                }
            } catch (DateTimeParseException e) {
                System.out.println(userInput + "を日付に変換できませんでした。");
            }
        }
    }

        public Stock registerStockData () {
            Stock stockToCsv = new Stock(registerTickerCode(), registerProductName(), registerExchangedMarket(), registerSharesIssued(), registerListedDate());
            System.out.println("以下の内容を登録します。");
            System.out.printf("""
                            銘柄コード: %s
                            　　銘柄名: %s
                            　　上場日: %tY/%<tm/%<td
                            　上場市場: %s
                    　　発行済み株式総数: %,d株
                    """, stockToCsv.getTickerCode(), stockToCsv.getProductName(), stockToCsv.getListedDate(), stockToCsv.getExchangedMarket().getJapannedName(), stockToCsv.getSharesIssued());
            return stockToCsv;
        }
}
