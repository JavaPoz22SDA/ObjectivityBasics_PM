package pl.sda.list;

import java.util.Arrays;

/**
 * @author Paweł Matyaszczyk
 */
public class List {
    private int [] numbers;
    private int maxElements;
    private int size;

    public List(int maxElements) {
        this.numbers = new int[maxElements];
        this.maxElements = maxElements;
        this.size = 0;
    }


    public void addElement(int value){
        try {
            if (this.size < this.maxElements){
                this.numbers[size] = value;
                ++this.size;
            }else
            {
                System.out.println("Nie można dodać więcej elementów, lista pełna!");
            }

        }
        catch(Exception ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public int findValue(int searchedValue){
        int value = -1;
        for (int i=0; i<this.maxElements;i++){
            if (this.numbers[i] == searchedValue) {
                return i;
            }
        }
        return value;
    }

    public void writeData() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(
                "Lista:\n"+
                "\tPojemność: "+maxElements +"\n"+
                "\tRozmiar: "+size+"\n"+
                "\tElementy:");
        for (int i =0;i<size;i++) {
            stringBuilder.append(" "+numbers[i]);
        }
        stringBuilder.append("\n");
        System.out.println(stringBuilder);
    }

    public void deleteFirst(int i) {
        int value = findValue(i);
        if (value > -1) {
            for (int a = value; a<size; a++){
              numbers[a] = numbers[a+1];
            }
            --size;
        }
    }

    public void removeDuplicates() {
        numbers = Arrays.stream(numbers).distinct().toArray();
        size = numbers.length;
    }

    public void reverseNumbers(){
        for (int i =0; i <numbers.length/2;i++){
            int temp = numbers[i];
            numbers[i] = numbers[numbers.length-1-i];
            numbers[numbers.length -1 -i] = temp;
        }
    }
}
