package com.marriage;

import com.marriage.util.HibernateUtil;
import com.marriage.view.MarriageMenu;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            MarriageMenu menu = new MarriageMenu();
            menu.   start(scanner);
        } finally {
            scanner.close();
            HibernateUtil.shutdown();
        }
    }
}