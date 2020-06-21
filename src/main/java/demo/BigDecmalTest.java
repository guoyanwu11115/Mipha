package demo;

import java.math.BigDecimal;

public class BigDecmalTest {
    public static void main(String[] args) {
        System.out.println(moneyPaarseTofen("100"));
        System.out.println(moneyPaarseTofen("100.0"));
        System.out.println(moneyPaarseTofen("100.1"));
        System.out.println(moneyPaarseTofen("100.13"));
        System.out.println(moneyPaarseTofen("10014.13"));
    }

    public static String moneyPaarseTofen(String moneyStr){
        BigDecimal moneyStr_bg = new BigDecimal(moneyStr);
        //100.12->10012.00
        String moneyStr_bg_str = moneyStr_bg.multiply(new BigDecimal(100)).toString();
        int i;
        if ((i=moneyStr_bg_str.indexOf("."))!=-1){
            //10012.00->10012
            moneyStr_bg_str = moneyStr_bg_str.substring(0,i);
        }
        return moneyStr_bg_str;
    }
}
