package com.su.community.enums;

/**
 * @author SUZJ
 */

public enum BoardNameEnum {
    BOARD_Campus_Information(1, "校园信息");

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    BoardNameEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String nameOfId(int id) {
        for (BoardNameEnum boardNameEnum : BoardNameEnum.values()) {
            if (boardNameEnum.getId() == id) {
                return boardNameEnum.getName();
            }
        }
        return "";
    }

}
