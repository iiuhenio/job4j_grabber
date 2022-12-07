package ru.job4j.ood.srp;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * В данном примере один метод реализовывает фильтр, а другой сохраняет полученную информацию
 * правильней разделить эти методы на два класса
 */
public class Filter {
    public static List<String> filter(String file) {
        List<String> result = List.of();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            result = in.lines().filter(x -> "404".equals(x.split(" ")[8]))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String a : log) {
                out.println(a);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String a : log) {
            System.out.println(a);
            save(log, "404.txt");
        }
    }
}