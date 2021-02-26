package org.o7planning.simplelistview.D_ARES.model;


/**
 * Menu type for MainMenu.java
 */
public enum MenuType {

    NORMAL(1001), HEADER(1002), SUB_HEADER(1003), DIVIDER(1004);

    private final int value;

    MenuType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
