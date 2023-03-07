import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static List<Integer> list = Arrays.asList(1000, 5555, 321, 27, 49, 998, 57, 89);

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<T> arrayList;
        T min;
        T max;
        arrayList = stream
                .sorted(order)
                .collect(Collectors.toList());
        if (!arrayList.isEmpty()) {
            min = arrayList.get(0);
            max = arrayList.get(arrayList.size() - 1);
            minMaxConsumer.accept(min, max);
        } else {
            minMaxConsumer.accept(null, null);
        }
    }

    public static void main(String[] args) {

//        Задача №1
        Stream<Integer> stream = list.stream();
        findMinMax(
                stream,
                Comparator.naturalOrder(),
                (min, max) -> System.out.println("минимальный элемент: " + min + "\n" + "максимальный элемент: " + max + "\n"));


//        Задача №2
        AtomicInteger integer = new AtomicInteger(1);
        long count = list.stream()
                .filter(n -> n % 2 == 0)
                .peek(i -> System.out.println(integer.getAndIncrement() + "-ое" + " четное число: " + i)).count();
        System.out.println("Количество четных чисел: " + count);
    }
}