package com.daniate.androidx_coordinatorlayout_examples;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {
    private String text;
    private Class activityClass;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Class getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class activityClass) {
        this.activityClass = activityClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(text, item.text) &&
                Objects.equals(activityClass, item.activityClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, activityClass);
    }
}
