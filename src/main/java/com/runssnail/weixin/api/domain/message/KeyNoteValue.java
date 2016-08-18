package com.runssnail.weixin.api.domain.message;

import java.io.Serializable;

public class KeyNoteValue implements Serializable {

    /**
     *
     */
    private static final long  serialVersionUID = -1062547601256807111L;

    /**
     * 默认黑色
     */
    public static final String DEFAULT_COLOR    = "#000000";

    private String             value;

    private String             color            = DEFAULT_COLOR;

    public KeyNoteValue(String value) {
        this.value = value;
    }

    public KeyNoteValue(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public KeyNoteValue() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
