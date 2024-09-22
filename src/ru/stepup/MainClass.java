package ru.stepup;

import java.util.*;
import java.util.stream.Collectors;

public class MainClass {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 1, 2, 3, 4, 5);
        System.out.println(removeDuplicates(list));

        list= List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);
        System.out.println(findThirdMax(list));
        System.out.println(findThirdUniqMax(list));

        List<User> users = List.of(
                new User("Иван", 25, "Инженер"),
                new User("Петр", 35, "Бухгалтер"),
                new User("Вася", 25, "Бухгалтер"),
                new User("Федя", 40, "Инженер"),
                new User("Серега", 28, "Инженер"),
                new User("Стас", 55, "Инженер")
        );
        System.out.println(findOldestEngineers(users));
        System.out.println(findAverageAge(users));

        List<String> strings = List.of("Java", "Python", "Kotlin", "JavaScript", "C++");
        System.out.println(findMaxStringLength(strings));

        String words = "java python kotlin javascript c++ java java kotlin javascript javascript javascript c++";
        System.out.println(countWordOccurrences(words));

        List<String> strings2 = List.of("Java9", "Python", "Kotlin", "Java7", "Java8", "JavaScript", "C++");
        sortAndPrintStrings(strings2);

        String[] strings3 = {"aa bbb cccc ddddd eee",
                "fff gggggg iii jjjjjjj k",
                "qweqwe wewewe fgfgf fgfg gggggg",
                "asdasd fgdfgdfg werwerw rrr",
                "asdasd fgdfgdfg werwerw rrrrrrrrrrrr"};
        System.out.println(findMaxWord(strings3));
    }

    //удаление дубликатов из списка
    public static <T> List<T> removeDuplicates(List<T> list) {
        return list.stream()
                .collect(Collectors.toSet())
                .stream().collect(Collectors.toList());

    }

    //поиск третьего максимального числа в списке
    public static Integer findThirdMax(List<Integer> list) {
        return list.stream().sorted(Collections.reverseOrder()).skip(2).findFirst().orElse(null);
    }
    //поиск третьего максимального уникального числа в списке
    public static Integer findThirdUniqMax(List<Integer> list) {
        return list.stream().distinct().sorted(Collections.reverseOrder()).skip(2).findFirst().orElse(null);
    }

    //поиск трех старших инженеров
    public static List<String> findOldestEngineers(List<User> users) {
        return users.stream()
                .filter(user -> "Инженер".equals(user.getCasta()))
                .sorted((u1, u2) -> Integer.compare(u2.getAge(), u1.getAge()))
                .limit(3)
                .map(User::getName)
                .collect(Collectors.toList());
    }

    //поиск среднего возраста сотрудников с должностью "Инженер"
    public static double findAverageAge(List<User> users) {
        return users.stream().filter(user->"Инженер".equals(user.getCasta()))
                .mapToInt(User::getAge)
                .average()
                .orElse(0.0);
    }

    //поиск самого длинногог слова в списке
    public static String findMaxStringLength(List<String> list) {
        return list.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
    }

    //подсчет количества вхождений каждого слова в строке
    public static Map<String, Long> countWordOccurrences(String words) {
        return Arrays.stream(words.split(" "))
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    }

    //сортировка и вывод строк по длине и алфавиту
    public static void sortAndPrintStrings(List<String> list) {
        list.stream().sorted(Comparator.comparingInt(String::length).thenComparing(String::compareTo)).forEach(System.out::println);
    }

    //поиск самого длинного слова в массиве строк
    public static String findMaxWord(String[] strings) {
        return Arrays.stream(strings)
                .flatMap(str -> Arrays.stream(str.split(" ")))
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
    }

}