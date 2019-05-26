
package ru.job4j.solid.isp;

import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    private HashMap<String, Action> actions;
    private Element root;

    public Menu() {
        this.actions = new HashMap<>(10);
    }

    public boolean addRoot(Element root) {
        boolean result = false;
        if (this.root == null) {
            result = true;
            this.root = root;
            this.root.setKey("1");
            System.out.println("Root element is created: " + root.getName());
        } else {
            System.out.println("Error! Root element has already created: " + this.root.getName());
        }
        return result;
    }

    public boolean addElement(Element parent, Element child) {
        boolean result = false;
        if (this.root == null) {
            this.addRoot(parent);
        }
        if (child != null) {
            result = parent.addChild(child);
        }
        return result;
    }

    public boolean dropElement(Element element) {
        boolean result = false;
        if (this.root != null) {
            if (this.root.equals(element)) {
                result = true;
                this.root = null;
                this.actions = null;
                System.out.println(String.format("Element '%s' and its subelements are deleted! Exit.", element.getName()));
            } else {
                result = this.tryDropChild(this.root, element);
            }
        }
        return result;
    }

    private boolean tryDropChild(Element parent, Element deletableElement) {
        boolean result = false;
        int i = 0;
        for (Element el : parent.getChildren()) {
            if (deletableElement.equals(el)) {
                result = true;
                System.out.println(
                        String.format("Element '%s' and its subelements are deleted!", deletableElement.getName())
                );
                this.actions.remove(el.getKey());
                i = parent.getChildren().indexOf(el);
                break;
            } else {
                result = tryDropChild(el, deletableElement);
            }
        }
        if (result) {
            parent.getChildren().remove(i);
        }
        return result;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String line;
        if (this.root != null) {
            do {
                this.showMenu(this.root, "");
                System.out.println();
                System.out.println("Input a key of menu:");
                System.out.println("For example: 1.1");
                System.out.println("\"exit\" for closing");
                line = scanner.nextLine();
                if (this.actions.containsKey(line)) {
                    System.out.println();
                    this.actions.get(line).execute();
                } else if (!"exit".equals(line)) {
                    System.out.println("Incorrect input! Try again.");
                }
            } while (!"exit".equals(line) && this.root != null);
        }
    }

    public void showMenu(Element root, String symbol) {
        if (this.root != null) {
            this.actions.put(root.getKey(), root.getAction());
            System.out.println(symbol + root.getKey() + "." +  root.getName());
            int childKey = 1;
            for (Element child : root.getChildren()) {
                if (child != null) {
                    child.setKey(root.getKey() + "." + childKey++);
                    this.showMenu(child, symbol + "--");
                }
            }
        } else {
            System.out.println("Root element is not created!");
        }
    }

    public static void main(String[] args) {
        Element java = new Element("Java", () -> System.out.println("This is 'Java'"));
        Element junior = new Element("Junior", () -> System.out.println("This is 'Junior'"));
        Element collection = new Element("Collection", () -> System.out.println("This is 'Collection'"));
        Element io = new Element("IO", () -> System.out.println("This is 'IO'"));
        Element middle = new Element("Middle", () -> System.out.println("This is 'Middle'"));
        Element multiThreading = new Element("MultiThreading", () -> System.out.println("This is 'MultiThreading'"));
        Element spring = new Element("Spring", () -> System.out.println("This is 'Spring'"));
        Element hibernate = new Element("Hibernate", () -> System.out.println("This is 'Hibernate'"));

        Menu menu = new Menu();
        menu.addElement(java, junior);
        menu.addElement(java, middle);
        menu.addElement(junior, collection);
        menu.addElement(junior, io);
        menu.addElement(middle, multiThreading);
        menu.addElement(middle, spring);
        menu.addElement(middle, hibernate);
        menu.start();

        menu.dropElement(junior);
        menu.start();
    }
}