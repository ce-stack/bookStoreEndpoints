package com.example.demo.config;

public enum AuthEnum {

    REGISTER("/auth/register"),
    LOGIN("/auth/login"),
    AddComment("/comment/add");

    private final String path;

    AuthEnum(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }


    public static boolean isPublicPath(String path) {
        for (AuthEnum authPath : values()) {
            if (path.startsWith(authPath.getPath())) {
                return true;
            }
        }
        return false;
    }
}
