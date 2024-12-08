package trading_app.Trade;

import trading_app.Stock.StockManager;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
public class TradeDataRegistrant {
    TradeData tradeData = new TradeData();
    TradeDataValidator tradeDataValidator = new TradeDataValidator();
    public void registerTickerCode(){
        Scanner scanner = new Scanner(System.in);
        StockManager stockManager = new StockManager();
        String userInput;
        while(true){
            System.out.printf("取引データを登録する4桁の銘柄コードを入力してください。%n" +
                    "1,3文字目:0-9の数字%n" +
                    "2,4文字目：0-9の数字またはB,E,I,O,V,Q,Zを除いた19種類の英大文字%n>");
            userInput = scanner.nextLine();
            if(tradeDataValidator.validateTickerCode(userInput)){
                if(stockManager.isAlreadyRegistered(userInput)){
                    tradeData.setTickerCode(userInput);
                    return;
                }
                System.out.println("銘柄コード"+userInput+"は銘柄マスタに登録されていません");
            }else {
                System.out.println(userInput + "は銘柄コードの要件を満たしていません。");
            }
        }
    }
    public void registerTradeSide(){
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true) {
            System.out.printf("取引の形態を入力してください。%n" +
                    "売り：SellまたはS%n" +
                    "買い：BuyまたはB%n>");
            userInput = scanner.nextLine().toUpperCase();
            try{
                tradeData.setSide(Side.fromOtherName(userInput));
                if(tradeDataValidator.canSellStock(tradeData)) {
                    return;
                }else{
                    System.out.println("保有していない銘柄を売却することは出来ません。");
                }
            }catch (IllegalArgumentException e){
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
    public void registerQuantity() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true) {
            System.out.printf("取引の数量（正の整数）を100の倍数で入力してください%n>");
            userInput = scanner.nextLine().replace(",", "");
            try {
                long parsedInput = Long.parseLong(userInput);
                if (tradeDataValidator.validateQuantity(parsedInput)) {
                    if (tradeDataValidator.isHundredfold(parsedInput)) {
                        if(tradeDataValidator.quantityWillBePlus(parsedInput,tradeData)) {
                            tradeData.setQuantity(parsedInput);
                            return;
                        }else{
                            System.out.println("保有数量が負の数となる取引は入力できません");
                        }
                    } else {
                        System.out.println("入力値が100の倍数ではありません。");
                    }
                } else {
                    System.out.println("負の数が入力されています。");
                }
            } catch (NumberFormatException e) {
                System.out.println(userInput + "を整数に変換できません。");
            }
        }
    }
    public void registerUnitPrice(){
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true){
            System.out.printf("取引単価を小数第二位まで入力してください%n" +
                    "なお、少数第三位以降は切り捨てられます。%n>");
            userInput = scanner.nextLine();
            try{
                if(userInput.endsWith(".")){
                    System.out.println("入力が小数点で終わっています。");
                }else if(tradeDataValidator.validateUnitPrice(userInput)){
                    tradeData.setUnitPrice(new BigDecimal(userInput));
                    return;
                }else{
                    System.out.println("入力値が0または負の数になっています。");
                }
            }catch (NumberFormatException e){
                System.out.println(userInput+"を数値に変換できませんでした");
            }
        }
    }
    public void registerTradedDate(){
        Scanner scanner = new Scanner(System.in);
        String userInput;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d k:m");
        while(true){
            System.out.printf("過去の取引日時をyyyy/MM/dd hh:mm形式で入力してください%n" +
                    "(時刻は24時間表記)%n" +
                    "日本取引所は平日の9:00から15:30まで営業しています。%n>");
            userInput  =scanner.nextLine();
            try{
                LocalDateTime parsedInput = LocalDateTime.parse(userInput,formatter);
                tradeDataValidator.isPastDate(parsedInput);
                tradeDataValidator.tradeIsOver(parsedInput,tradeData);
                tradeDataValidator.isWeekDay(parsedInput);
                tradeDataValidator.marketIsOpen(parsedInput);
                tradeData.setTradedDateTime(parsedInput);
                return;
            }catch (DateTimeParseException e){
                System.out.println(userInput+"を日付に変換できません");
            }catch (IllegalArgumentException e){
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
    public TradeData registerTradeData(){
        StockManager stockManager = new StockManager();
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        registerTickerCode();
        registerTradeSide();
        registerQuantity();
        registerUnitPrice();
        registerTradedDate();
        tradeData.setProductName(stockManager.searchStockFromCode(tradeData.getTickerCode()).getProductName());
        tradeData.setInputDateTime(LocalDateTime.now());
        System.out.println("以下の取引を登録します。");
        System.out.printf("""
                　　　　　　銘柄コード：%s
                　　　　　　　　銘柄名：%s
                　　　　　　　取引形態：%s
                　　　　　　　取引数量：%,d個
                　　　　　　　取引単価：%s円
                　　　　　　　取引日時：%tY/%<tm/%<td %<tH:%<tM
                """,tradeData.getTickerCode(),tradeData.getProductName(),tradeData.getSide().name(),tradeData.getQuantity(),decimalFormat.format(tradeData.getUnitPrice()),tradeData.getTradedDateTime());
        return tradeData;
    }
}
