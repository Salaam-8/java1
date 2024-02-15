package airline.ticket.booking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Booking {
    private Customer customer;
    private ArrayList<Flight> flight;
    private Date bookingDate;
    public Booking() {
    }
    public Booking(Customer customer, ArrayList<Flight> flight,  Date bookingDate) {
        this.customer = customer;
        this.flight = flight;

        this.bookingDate = bookingDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Flight> getFlight() {
        return flight;
    }

    public void setFlight(ArrayList<Flight> flight) {
        this.flight = flight;
    }


    public Date getBookingDate() {
        return bookingDate;
    }
    public String toString() {
        String str="";
        //flight ArrayList to String
        for (Flight f : flight) {
            str += f.toString();
        }
        return customer.toString() + str + "\n" +
                "Booking Date: " + bookingDate + "\n";
    }
    public void saveBookingToFile() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(customer.getId() + ".txt");
        pw.println(this.toString());
        pw.close();
    }
    public static void  SearchBooking() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your ID: ");
        String id = sc.nextLine();
        File file = new File(id + ".txt");
        if(file.exists()){
            Scanner sc1 = new Scanner(file);
            System.out.println("Your Booking Details: ");
            while(sc1.hasNextLine()){
                System.out.println(sc1.nextLine());
            }
        }
        else{
            System.out.println("No Booking Found");
        }
    }
    //delete booking
    public static void deleteBooking() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your ID: ");
        String id = sc.nextLine();
        File file = new File(id + ".txt");
        if(file.exists()){
            file.delete();
            System.out.println("Booking Deleted");
        }
        else{
            System.out.println("No Booking Found");
        }
    }
}
