package ru.job4j.solid.isp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Element {
    private String key;
    private String name;
    private Action action;
    private List<Element> children;

    public Element(String name, Action action) {
        this.action = action;
        this.name = name;
        this.children = new ArrayList<>();
    }

    public Action getAction() {
        return action;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Element> getChildren() {
        return children;
    }

    public boolean addChild(Element child) {
        boolean result;
        if (this.children.contains(child)) {
            result = false;
        } else {
            result = this.children.add(child);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Element element = (Element) o;
        return Objects.equals(key, element.key)
                && Objects.equals(name, element.name)
                && Objects.equals(action, element.action)
                && Objects.equals(children, element.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, name, action, children);
    }
}