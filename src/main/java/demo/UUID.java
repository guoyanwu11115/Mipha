package demo;


public class UUID {
    public static void main(String[] args) {
        String uuid = java.util.UUID.randomUUID().toString();
        System.out.println(uuid.replaceAll("-",""));

    }
}
