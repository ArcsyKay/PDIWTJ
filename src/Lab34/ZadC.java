package Lab34;
import java.io.*;


/**
 * Created by Kisuo on 28/10/2016.
 *
 * 1. Wykonać aplikację odporną na błędy oraz:
 – utworzyć własny wyjątek przechowujący opis oraz numer kodu błędu
 – zdefiniować konstruktor oraz metody wyrzucające powyższy wyjątek
 – zastosować blok obsługi wyjątków try/catch/finally

 * 2. Napisać klasę:
 * Wakacje zawierającą opis wakacji (kraj, miejscowość, hotel, termin, wyżywienie itp).
 * Klasa ma posiadać konstruktor oraz metody wyświetlające cenę (przy zadanym pokoju i terminie).
 * W programie głównym stworzyć kilka obiektów klasy Wakacje (np.Grecja,Włochy,Hiszpania)
 * i na podstawie przykładowych danych wyświetlić wszystkie informacje.
 *
 * 3. Napisać program wczytujący z klawiatury wartosci dla obiektów z zadania 2 oraz zapisujący je w pliku tekstowym.
 * Obsłużyć wszelkie możliwe wyjątki programu.
 */
public class ZadC {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private static Integer choice = 0;
    private static String city;
    private static Float price = 0f;
    private static String hotel;

    public static void main(String[] args) throws IOException, MyException {

        chooseHoliday();

        switch (choice) {
            case 1:
                System.out.println("Germany");
                enterHolidayCity();
                Holiday Germany = new Holiday(city, price, hotel);
                System.out.println(String.format("Food cost is: \u20ac%.2f ", Germany.foodCost()));
                System.out.println(String.format("Room cost: \u20ac%.2f per day", Germany.price));
                System.out.println(String.format("Cost of living is: \u20ac%.2f per month", Germany.costOfLiving()));
                writeToTextFile(Germany);
                return;
            case 2:
                System.out.println("USA");
                enterHolidayCity();
                Holiday USA = new Holiday(city, price, hotel);
                System.out.println(String.format("Food cost: $%.2f per day", USA.foodCost()));
                System.out.println(String.format("Room cost: $%.2f per day", USA.price));
                System.out.println(String.format("Cost of living is: $%.2f per month", USA.costOfLiving()));
                writeToTextFile(USA);
                return;
            case 3:
                System.out.println("England");
                enterHolidayCity();
                Holiday England = new Holiday(city, price, hotel);
                System.out.println(String.format("Food cost: \u00a3%.2f per day", England.foodCost()));
                System.out.println(String.format("Room cost: \u00a3%.2f per day", England.price));
                System.out.println(String.format("Cost of living is: \u00a3%2f per month", England.costOfLiving()));
                writeToTextFile(England);
                return;
            default:
                System.out.println("Wrong choice! Try Again!");
                main(null);
        }
    }

    private static void chooseHoliday() throws IOException {
        System.out.println("Choose a Holiday: 1.Germany 2.USA 3.England");
        try {
            choice = Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException nfe) {
            System.out.println(String.format("Wrong Number format %s", nfe.getMessage()));
            chooseHoliday();
        }
    }

    private static void enterHolidayCity() throws IOException {
        System.out.print("Enter city: ");
        try {
            city = bufferedReader.readLine();
            enterHolidayPrice();
            if(city == "") throw new Exception();
        } catch (Exception e) {
            System.out.println(String.format("Can't be empty %s", e.getMessage()));
            enterHolidayCity();
        }
    }

    private static void enterHolidayPrice() throws IOException {
        System.out.print("Enter price: ");
        try {
            price = Float.parseFloat(bufferedReader.readLine());
            enterHolidayHotel();
        } catch (NumberFormatException nfe) {
            System.out.println(String.format("Wrong Number format %s", nfe.getMessage()));
            enterHolidayPrice();
        }
    }

    private static void enterHolidayHotel() throws IOException {
        System.out.print("Enter hotel: ");
        try {
            hotel = bufferedReader.readLine();
            if(hotel == "") throw new Exception();
        } catch (Exception e) {
            System.out.println(String.format("Can't be empty %s", e.getMessage()));
            enterHolidayHotel();
        }
    }

    private static void writeToTextFile(Holiday Holiday) throws IOException {
        BufferedWriter writer = null;
        try {
            File file = new File("Holiday");
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(String.format("city: %s ", Holiday.city));
            writer.write(String.format("price: %s ", Holiday.price));
            writer.write(String.format("hotel: %s ", Holiday.hotel));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
