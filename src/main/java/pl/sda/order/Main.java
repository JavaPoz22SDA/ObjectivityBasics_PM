package pl.sda.order;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Paweł Matyaszczyk
 */
public class Main {
    public static void saveOrder(Order z, String fileName){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(out, z);
            byte[] data = out.toByteArray();
            FileOutputStream file = new FileOutputStream(fileName);
            file.write(data);
            file.close();

        } catch (
                IOException ex) {
            System.out.println("Błąd zapisu " + ex.getMessage());
        }

    }

    public static Order readOrder(String fileName){
        Order order = new Order();
        return order;
    }

    public static void main(String[] args) {
        Serializable serializable;
        serializable = new Order();
        Order readorder = new Order();

        //test odczytu z pliku json
        readorder = serializable.readOrder("Order.json");
        System.out.println(readorder.toString());

        Item p1 = new Item("Chleb", 1, 3.5);
        Item p2 = new Item("Cukier", 3, 4);
        Item p3 = new Item("Cukier", 10, 4);
        Item p4 = new Item("Cukier", 10, 4);

        Order z = new Order(20);
        z.addItem(p1);
        z.addItem(p2);
        z.addItem(p3);
        z.addItem(p4);

        //test zapisu do pliku
        serializable.saveOrder(z,"Order.json");
        System.out.println(z.toString());



    }
}
