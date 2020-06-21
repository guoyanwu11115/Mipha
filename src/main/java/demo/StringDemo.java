package demo;

public class StringDemo {
    public static void main(String[] args) {
        String ZHZT="00000000";
        String newZHZT="00000000";
        System.out.println(ZHZT.charAt(0));
        int a = 8;
        for (int i = 0; i < a; i++) {
            if (-1 == i) {
                if (newZHZT.charAt(i) == '1') {
                    throw new RuntimeException("账户状态不合法");
                }
                continue;
            }
            // 如果新获取的账户状态每一位和原来的不一致
            if (newZHZT.charAt(i) != ZHZT.charAt(i)) {
                if (ZHZT.charAt(i) == '0') {
                    throw new RuntimeException("账户状态不合法");
                }
            }
        }


    }
}
