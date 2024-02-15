package airline.ticket.booking;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Flight {
    private int flightNumber;
    private String departure;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private String flightDate;
    private String flightTime;
    private double price;

    public Flight() {
    }

    public Flight(int flightNumber, String departure, String destination, String departureTime, String arrivalTime,
                  String flightDate, String flightTime, double price) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightDate = flightDate;
        this.flightTime = flightTime;
        this.price = price;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static boolean checkFlightNumber(int flightNumber) throws FileNotFoundException {
        File file = new File("flight.txt");
        if (!file.exists()) {
            return false;
        }
        Scanner in = new Scanner(file);
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] arr = line.split(",");
            if (Integer.parseInt(arr[0]) == flightNumber) {
                return true;
            }
        }
        return false;
    }

    public static void addFlightToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("flight.txt", true));
        Scanner in = new Scanner(System.in);
        //flight number is random number between 100000 and 999999
        int flightNumber = (int) (Math.random() * 900000 + 100000);
        while (checkFlightNumber(flightNumber)) {
            flightNumber = (int) (Math.random() * 900000 + 100000);
        }
        System.out.println("Enter departure: ");
        String departure = in.nextLine();
        System.out.println("Enter destination: ");
        String destination = in.nextLine();
        System.out.println("Enter departure time: ");
        String departureTime = in.nextLine();
        // check if departure time is valid format (hh:mm)
        while (!departureTime.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
            System.out.println("Invalid format. Enter departure time again format (hh:mm): ");
            departureTime = in.nextLine();
        }
        System.out.println("Enter arrival time format (hh:mm): ");
        String arrivalTime = in.nextLine();
        // check if arrival time is valid format (hh:mm)
        while (!arrivalTime.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
            System.out.println("Invalid format. Enter arrival time again: ");
            arrivalTime = in.nextLine();
        }
        System.out.println("Enter flight date: ");
        String flightDate = in.nextLine();
        // check if flight date is valid format (dd/mm/yyyy)
        while (!flightDate.matches("([0-2]?[0-9]|3[0-1])/([0]?[0-9]|1[0-2])/[0-9]{4}")) {
            System.out.println("Invalid format. Enter flight date again format (dd/mm/yyyy): ");
            flightDate = in.nextLine();
        }
        //flight time is difference between departure time and arrival time
        int flightTime = Integer.parseInt(arrivalTime.substring(0, 2)) - Integer.parseInt(departureTime.substring(0, 2));
        String flightTimeStr = String.valueOf(flightTime);
        System.out.println("Enter price: ");
        double price = in.nextDouble();
        //check if price is more than 100
        while (price < 100) {
            System.out.println("Price must be more than 100. Enter price again: ");
            price = in.nextDouble();
        }
        writer.write(flightNumber + "," + departure + "," + destination + "," + departureTime + "," + arrivalTime + "," + flightDate + "," + flightTimeStr + "," + price);
        writer.newLine();
        writer.close();
    }

    public static Flight getFlightFomFile() throws FileNotFoundException {
        ArrayList<Flight> list = new ArrayList<>();
        File file = new File("flight.txt");
        if (!file.exists()) {
            System.out.println("Flight file does not exist");
            return null;
        }
        Scanner in = new Scanner(System.in);
        Scanner in2 = new Scanner(file);
        while (in2.hasNext()) {
            String line = in2.nextLine();
            String[] arr = line.split(",");
            Flight flight = new Flight(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], Double.parseDouble(arr[7]));
            list.add(flight);
        }
        System.out.println("Available flights: ");
        System.out.println("No.\t Flight number\t Departure\t Destination\t Departure time\t Arrival time\t Flight date\t Flight time\t Price");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "\t " + list.get(i).getFlightNumber() + "\t\t " + list.get(i).getDeparture() + "\t\t " + list.get(i).getDestination() + "\t\t " + list.get(i).getDepartureTime() + "\t\t " + list.get(i).getArrivalTime() + "\t\t " + list.get(i).getFlightDate() + "\t\t " + list.get(i).getFlightTime() + "\t\t " + list.get(i).getPrice());
        }
        System.out.println("Enter flight number: ");
        int flightNumber = in.nextInt();
        //check if flight number is valid array index
        while (flightNumber < 1 || flightNumber > list.size()) {
            System.out.println("Invalid flight number. Enter flight number again: ");
            flightNumber = in.nextInt();
        }
        return list.get(flightNumber - 1);

    }
    //toString method
    @Override
    public String toString() {
        return "flight number: " + flightNumber + ", departure: " + departure + ", destination: " + destination + ", departure time: " + departureTime + ", arrival time: " + arrivalTime + ", flight date: " + flightDate + ", flight time: " + flightTime + ", price: " + price;
    }
}
