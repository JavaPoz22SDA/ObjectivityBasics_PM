package pl.sda.list;

/**
 * @author Paweł Matyaszczyk
 */
public class Main {
    public static void main(String[] args) {
        final int N = 10;
        List l = new List(N);
        for (int i = 0; i < N/2; ++i) {
            l.addElement( (1 << i) );
        }
        l.addElement(2);
        l.addElement(8);
        l.writeData();
        l.deleteFirst(2);
        l.writeData();
        for (int i = 0; i < N/2; ++i) {
            l.addElement( (1 << i) );
        }
        l.writeData();
        System.out.println("Po usunięciu powtórzeń:");
        l.removeDuplicates();
        l.writeData();
        System.out.println("Po odwróceniu listy:");
        l.reverseNumbers();
        l.writeData();

    }
}
