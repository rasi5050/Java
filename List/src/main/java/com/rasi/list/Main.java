package com.rasi.list;
public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");
        List<Integer> list = new List<>();
        list.add(1);
        list.add(1);
        list.add(987978);
        list.add(3454);
        list.add(1);
        list.add(1);
        list.add(987978);
        list.add(3454);
        list.add(1);
        list.add(1);
        list.add(987978);
        list.pop();
        list.pop();
        list.add(5,66);
        list.add(8,66);
        list.remove(5);

        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.indexOf(1));
        System.out.println(list.indexOf(66));
        System.out.println(list.contains(1));
        System.out.println(list.contains(66));
        System.out.println(list.contains(0));

    }
}
