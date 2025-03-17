import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> username = new ArrayList<>();
        ArrayList<Integer> password = new ArrayList<>();
        ArrayList<Integer> roomNumber = new ArrayList<>();
        ArrayList<Integer> Ids = new ArrayList<>();
        ArrayList<String> Category = new ArrayList<>();
        int price = 0;

        User user = new User();

        while (true) {
            System.out.println("Welcome To The Hotel Reservation System");
            System.out.print("What Do You Want To Do? \n1-Reservation\n2-Empty Room\n3-Booking Details\n4-Exit\nPlease Enter Your Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    user.reservation(username, password, roomNumber, Ids, scanner, Category, price);
                    break;
                case 2:
                    user.emptyRoom(Ids, scanner);
                    break;
                case 3:
                    user.bookingDetails(username, password, roomNumber, scanner, Category);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}

class User {
    private int price = 0;

    public void reservation(ArrayList<String> Username, ArrayList<Integer> password, ArrayList<Integer> roomNumber, ArrayList<Integer> id, Scanner scanner, ArrayList<String> Category, int price) {
        System.out.print("Please Enter Your ID: ");
        int Id = scanner.nextInt();
        scanner.nextLine();
        id.add(Id);

        System.out.print("Please Enter Your Username: ");
        String name = scanner.nextLine();
        Username.add(name);

        System.out.print("Please Enter Your Password: ");
        int New_password = scanner.nextInt();
        scanner.nextLine();
        password.add(New_password);

        int roomnumber2 = 0;
        this.category(scanner, Category, price, roomnumber2, roomNumber);
    }

    public void emptyRoom(ArrayList<Integer> id, Scanner scanner) {
        System.out.print("Please Enter Your ID: ");
        int Id = scanner.nextInt();
        scanner.nextLine();

        if (id.contains(Id)) {
            System.out.println("Unfortunately, the room is already booked.\nPlease choose 3 to View Booking Details.");
        } else {
            System.out.println("There is an empty room available.");
        }
    }

    public void bookingDetails(ArrayList<String> Username, ArrayList<Integer> password, ArrayList<Integer> roomNumber, Scanner scanner, ArrayList<String> Category) {
        System.out.print("Please Enter Your User Name: ");
        String Username2 = scanner.nextLine();
        System.out.print("Please Enter Your Password: ");
        int password2 = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < Username.size(); i++) {
            if (Username.get(i).equals(Username2) && password.get(i).equals(password2)) {
                System.out.println("Booking Details: ");
                System.out.println("Username: " + Username.get(i));
                System.out.println("Room Number: " + roomNumber.get(i));
                System.out.println("Room Category: " + Category.get(i));
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Invalid username or password.");
        }
    }

    public void category(Scanner scanner, ArrayList<String> Category, int price, int roomnumber2, ArrayList<Integer> roomNumber) {
        while (true) {
            System.out.print("There are three types of rooms: \n1-Standard Room\n2-Premium Room\n3-Suite Room\nPlease Enter Your Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    Category.add("Standard Room");
                    price = 100;
                    break;
                case 2:
                    Category.add("Premium Room");
                    price = 500;
                    break;
                case 3:
                    Category.add("Suite Room");
                    price = 1000;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    continue;
            }
            this.take_Decision(scanner, price, Category, roomnumber2, roomNumber);
            break;
        }
    }

    public void take_Decision(Scanner scanner, int price, ArrayList<String> Category, int roomnumber2, ArrayList<Integer> roomNumber) {
        String decision;
        System.out.println("Your Total Price Is: " + price + " $");
        do {
            System.out.println("Do You Want to Confirm Your Reservation? (Y/N)");
            decision = scanner.next();
        } while (!decision.equalsIgnoreCase("Y") && !decision.equalsIgnoreCase("N"));

        if (decision.equalsIgnoreCase("Y")) {
            System.out.println("Do You Want To Pay By Cash Or Card? \n1-Cash\n2-Card");
            int choice2 = scanner.nextInt();
            scanner.nextLine();

            switch (choice2) {
                case 1:
                    System.out.println("Your Payment Is Cash");
                    break;
                case 2:
                    System.out.println("Your Payment Is Card");
                    break;
                default:
                    System.out.println("Invalid choice, assuming cash payment.");
            }

            System.out.println("Please Pay " + price + " $");
            System.out.println("You have Paid " + price + " $ Successfully");

            roomnumber2 = (int) (Math.random() * 1000);
            roomNumber.add(roomnumber2);
            System.out.println("Your Room Number Is: " + roomnumber2);
            System.out.println("Your Reservation Is Confirmed"); // تم تأكيد الحجز هنا
        } else {
            System.out.print("Do You Want to Choose Another Category of Room? (Y/N): ");
            String choice3 = scanner.next();

            if (choice3.equalsIgnoreCase("Y")) {
                roomnumber2 = (int) (Math.random() * 1000);
                this.category(scanner, Category, price, roomnumber2, roomNumber);
                return; // للخروج بعد اختيار نوع جديد
            } else {
                System.out.println("Your Reservation Is Canceled");
            }
        }
    }
}
