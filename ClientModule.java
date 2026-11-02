package com.imbada.badaclient;

public final class ClientModule {
    private final String name;
    private final String category;
    private boolean enabled;
    private final String status;

    public ClientModule(String name, String category, String status) {
        this.name = name;
        this.category = category;
        this.status = status;
    }

    public String name() { return name; }
    public String category() { return category; }
    public boolean enabled() { return enabled; }
    public void toggle() { enabled = !enabled; }
    public String status() { return status; }
}
