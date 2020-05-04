package pl.sda.order;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Paweł Matyaszczyk
 */
public class Order implements Serializable{
    private List<Item> items = new ArrayList<>();
    private int itemsAdded;
    private int maxSizeInOrder;

    public Order() {
    }

    public Order(int maxSizeInOrder) {
        this.maxSizeInOrder = maxSizeInOrder;
    }

    public void addItem(Item p) {
        int index=-1;

        for (Item searchItem:items) {
            if (searchItem.getProductName() == p.getProductName()){
                index = items.indexOf(searchItem);
            }
        }
        if (index >=0) {
            items.get(index).setQuantity(items.get(index).getQuantity() + p.getQuantity());
        }
        else
        {
            this.items.add(p);
            ++this.itemsAdded;
        }
    }

    public double sumValue(){
        double sum=0;
        for (Item item: items) {
            sum =+ item.calculateValue();
        }
        return sum;
    }

    public void deleteItem(int index){
        items.remove(index);
        --this.itemsAdded;
    }

    public void editItem(int index){
        Item item = items.get(index);
        String oldName = item.getProductName();
        double oldPrice = item.getPrice();
        int oldQuantity = item.getQuantity();

        Scanner scanner = new Scanner(System.in);
        try {
            String trySetName, trySetPrice, trySetQuantity;
            System.out.println("Podaj nazwę towaru: ");
            trySetName = scanner.nextLine().trim();
            System.out.println("Podaj cenę: ");
            trySetPrice = scanner.nextLine().trim().replace(",",".");
            System.out.println("Podaj ilość towaru: ");
            trySetQuantity = scanner.nextLine().trim().replace(",",".");

            if (trySetName == null || trySetName == "" ){
                System.out.println("Błąd w nazwie. Brak zmian.");
            }
            else{
                item.setProductName(trySetName);
            }

            if (trySetPrice == null || trySetPrice == "") {
                System.out.println("Błąd w nazwie. Brak zmian.");
            }
            else {
                item.setPrice(Double.valueOf(trySetPrice));
            }

            if (trySetQuantity == null || trySetQuantity == ""){
                System.out.println("Błąd w nazwie. Brak zmian.");
            }
            else {
                item.setQuantity(Integer.parseInt(trySetQuantity));
            }
        } catch ( Exception ex ){
            System.out.println("Podałeś błędne dane. System wygenerował błąd: " + ex.getMessage() +"\nDane zostały przywrócone do poprzednich wartości.");
            item.setProductName(oldName);
            item.setQuantity(oldQuantity);
            item.setPrice(oldPrice);
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getItemsAdded() {
        return itemsAdded;
    }

    public void setItemsAdded(int itemsAdded) {
        this.itemsAdded = itemsAdded;
    }

    public int getMaxSizeInOrder() {
        return maxSizeInOrder;
    }

    public void setMaxSizeInOrder(int maxSizeInOrder) {
        this.maxSizeInOrder = maxSizeInOrder;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        double total = 0,totalWithDiscount = 0, discount = 0;
        sb.append("\n\nZamowienie:\n");
        for (Item item: items) {
            sb.append(item.toString()+"\n");
            total += item.calculateValue();
            totalWithDiscount += item.calculateValueWithDiscount();
        }
        sb.append(String.format("\nRazem: %.2f zł",total));
        sb.append(String.format("\nRabat: %.2f zł",total-totalWithDiscount));
        sb.append(String.format("\nRazem z rabatem: %.2f zł",totalWithDiscount));

        return sb.toString();
    }

    @Override
    public void saveOrder(Order z, String fileName) {
    try{
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(fileName),z);
    }
    catch (Exception ex){
        ex.printStackTrace();
    }
    }

    @Override
    public Order readOrder(String fileName) {
        Order order = new Order();
        try {
        ObjectMapper mapper = new ObjectMapper();
        order = mapper.readValue(new File(fileName),Order.class);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return order;
    }
}
