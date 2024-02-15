
package airline.ticket.booking;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class AirlineTicketBooking {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Airline Ticket Booking System");
        Scanner sc = new Scanner(System.in);
        int ch = 0;
        do {
            System.out.println("1. Book Ticket");
            System.out.println("2. Search Booking");
            System.out.println("3. Add Flight");
            System.out.println("4. Delete Flight");
            System.out.println("5. Exit");
            System.out.println("----------------------------------------");
            System.out.println("Enter your choice: ");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    bookTicket();
                    break;
                case 2:

                    Booking.SearchBooking();
                    break;
                case 3:
                    Flight.addFlightToFile();
                    break;
                case 4:
                    Booking.deleteBooking();
                    break;
                case 5:
                    System.out.println("Thank you for using Airline Ticket Booking System");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        } while (ch != 5);
    }

    private static void bookTicket() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        //check if name is valid only letters and spaces
        String name = sc.nextLine();
        while (!name.matches("[a-zA-Z ]+")) {
            System.out.println("Invalid name. Enter again: ");
            name = sc.nextLine();
        }
        System.out.println("Enter your ID: ");
        String id = sc.nextLine();
        //check if id is valid only numbers and 9 digits
        while (!id.matches("[0-9]{9}")) {
            System.out.println("Invalid ID. Enter again: ");
            id = sc.nextLine();
        }
        //check if id is already exists
        File f = new File(id + ".txt");
        while (f.exists()) {
            System.out.println("ID already exists. Do you want to continue? (y/n)");
            String ans = sc.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                break;
            } else {
                System.out.println("Enter your ID: ");
                id = sc.nextLine();
                //check if id is valid only numbers and 9 digits
                while (!id.matches("[0-9]{9}")) {
                    System.out.println("Invalid ID. Enter again: ");
                    id = sc.nextLine();
                }
                f = new File(id + ".txt");
            }

        }
        System.out.println("Enter your phone: ");
        String phone = sc.nextLine();
        //check if phone is valid only numbers and 10 digits and starts with 05
        while (!phone.matches("05[0-9]{8}")) {
            System.out.println("Invalid phone. Enter again: ");
            phone = sc.nextLine();
        }
        //ask user to enter gender and check if it is valid only m or f
        System.out.println("Enter your gender:");
        String gender = sc.nextLine();
        while (!gender.equalsIgnoreCase("m") && !gender.equalsIgnoreCase("f")){
            System.out.println("Invalid gender . just m or f");
        }
        System.out.println("Enter your age: ");
        int age = sc.nextInt();
        //check if age is valid only numbers and between 0 and 120
        while (age < 0 || age > 120) {
            System.out.println("Invalid age. Enter again: ");
            age = sc.nextInt();
        }
        //create customer object
        Customer c = new Customer(id, name, phone, gender, age);
        //choose flight from list of flights
        ArrayList<Flight> flights = new ArrayList<>();
        System.out.println("Choose flight from the list: ");
        do {
            Flight f1 = Flight.getFlightFomFile();
            if (f1 == null) {
                System.out.println("No flights available please add a flight first");
                return;
            }
            flights.add(f1);
            System.out.println("Do you want to add another ticket? (y/n)");
            String ans = sc.next();
            if (ans.equalsIgnoreCase("n")) {
                break;
            }
        } while (true);
        //create booking object
        Date date = new Date();
        Booking b = new Booking(c, flights, date);
        //save booking to file
        b.saveBookingToFile();
        //print booking details
        System.out.println("Booking details: ");
        System.out.println(b.toString());
        }
    }

