package demo;

public class Unicode {

    public static void main(String[] args) {
        String str = "认购登记簿信息表";
        System.out.println(toUNICODE("认购登记簿信息表"));
        System.out.println("认购登记簿信息表".length());
        System.out.println("认购登记簿信息表".charAt(0)<=256);
    }

    public static String toUNICODE(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) <= 256) {
                sb.append("\\u00");
            } else {
                sb.append("\\u");
            }
            sb.append(Integer.toHexString(s.charAt(i)));
        }
        return sb.toString();
    }
}
