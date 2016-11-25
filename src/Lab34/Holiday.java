package Lab34;

/**
 * Created by Kisuo on 25/11/2016.
 */
import java.util.Random;

public class Holiday {
    String city;
    Float price;
    String hotel;

    public Holiday(String city, Float price, String hotel) {
        this.city = city;
        this.price = price;
        this.hotel = hotel;
    }

    public Float costOfLiving() {
        Random rand = new Random();
        try {
            return foodCost() * 30 + price * 30;
        } catch (MyException e) {
            System.out.print(e.getMessage());
            System.out.println(e.getErrorCode());
        }
        return 0f;
    }

    public Float foodCost() throws MyException {
        float minX = 20.0f;
        float maxX = 60.0f;
        Random rand = new Random();
        float finalX = rand.nextFloat() * (maxX - minX) + minX;
        if (finalX < 20.0 || finalX > 60.00) {
            throw new MyException("Random Float Error, CODE: ", 666);
        }
        return finalX;
    }
}
