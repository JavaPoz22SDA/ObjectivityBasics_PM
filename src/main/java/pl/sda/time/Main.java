package pl.sda.time;

/**
 * @author Paweł Matyaszczyk
 */
public class Main {
    public static void main(String[] args) {
        Time t1 = new Time(10, 56);
        Time t2 = new Time(0, 123);
        System.out.println("t1 = " + t1);
        System.out.println("t2 = " + t2);
        System.out.println("t1 + t2 = " + t1.addTime(t2));
        System.out.println("t1 - t2 = " + t1.subtractTime(t2));
        Time [] tab = { t1, t2, t2 };
        System.out.println("Time.sumuj dla t1 + t2 + t2 = " +
               Time.sum(tab, 3));
        System.out.println("t1 * 2 = " + t1.multiplyTime(2));
        Time t3 = new Time("3 h 17 min");
        System.out.println("Konstruktor z łańcuchem: " + t3);

    }
}
