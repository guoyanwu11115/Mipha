package demo;

public class IndexOfDemo {
    public static void main(String[] args) {
        String string = "id.jyrq";
        System.out.println(string.indexOf("idd."));

        for (int i = 0; i <10 ; i++) {
            System.out.println("index:"+i);
            int index = new java.util.Random().nextInt(5);
            System.out.println("index:"+index);
        }
    }
}
