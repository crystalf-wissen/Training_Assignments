import java.time.*;
import java.util.*;
import static java.time.Month.*;

public class DateAndTime{
    public static void main(String args[]){
        LocalDate ld = LocalDate.now();
        System.out.println(ld);

        LocalTime lt = LocalTime.now();
        System.out.println(lt);

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        System.out.println(ld.withMonth(Month.MARCH.getValue()));

        LocalDate ld2 = LocalDate.of(1939,SEPTEMBER,17);
        System.out.println(ld2);

        LocalTime t1 = LocalTime.now(ZoneId.of("Singapore"));
        System.out.println(t1);

        Set s = ZoneId.getAvailableZoneIds();
        s.forEach(System.out::println);
    }
}