import java.util.Scanner;

public class users {
    public int userID;
    public users() {

        System.out.println("Hello and welcome, please choose your type of users, press 1 for user, press 2 for normal user and 3 for super users");
        Scanner scan = new Scanner(System.in);
        userID = scan.nextInt();
        switch (userID) {
            case 1 -> System.out.println("You have now entered users");
            case 2 -> System.out.println("You have now entered normal users");
            case 3 -> System.out.println("You have now entered super users");
            default -> {
                System.out.println("Shutdown beginning...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("3...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        }
    }
}



