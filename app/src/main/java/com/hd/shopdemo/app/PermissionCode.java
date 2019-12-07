package com.hd.shopdemo.app;

public enum PermissionCode {
    READ_EXTERNAL_STORAGE_CODE("读取外部存储"),
    WRITE_EXTERNAL_STORAGE_CODE("写入外部存储"),
    RW_EXTERNAL_STORAGE_CODE("读写外部存储");

    private final String title;

    PermissionCode(String title) {
        this.title = title;
    }

    // 根据value返回枚举类型,主要在switch中使用
    public static PermissionCode valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }

    public String getTitle() {
        return title;
    }
}
