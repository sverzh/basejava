package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] array = new int[]{1, 9, 4, 5, 5};
        System.out.println(minValue(array));
        List<Integer> integers = Arrays.stream(array)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(oddOrEven(integers));
    }

    public static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> 10 * a + b);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int mod = integers.stream().mapToInt(Integer::intValue)
                .sum() % 2;
        System.out.println(mod);
        return integers
                .stream()
                .filter(n -> n % 2 != mod)
                .collect(Collectors.toList());
    }
}
