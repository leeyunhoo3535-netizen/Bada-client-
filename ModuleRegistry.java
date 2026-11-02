package com.imbada.badaclient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ModuleRegistry {
    private final List<ClientModule> modules = new ArrayList<>();

    public void registerDefaults() {
        add("Auto Builder", "World", "Planned — needs a build blueprint and placement logic");
        add("Scaffold", "World", "Not implemented");
        add("Fast Place", "World", "Not implemented");
        add("Auto Tool", "World", "Not implemented");
        add("KillAura", "Combat", "Not implemented");
        add("Crystal Aura", "Combat", "Not implemented");
        add("Auto Totem", "Combat", "Not implemented");
        add("Auto Clicker", "Combat", "Not implemented");
        add("ESP", "Render", "Not implemented");
        add("Fullbright", "Render", "Not implemented");
        add("Tracers", "Render", "Not implemented");
        add("Storage ESP", "Render", "Not implemented");
        add("Sprint", "Movement", "Not implemented");
        add("Elytra Helper", "Movement", "Not implemented");
        add("Auto Eat", "Misc", "Not implemented");
        add("Auto Reconnect", "Misc", "Not implemented");
        add("Inventory Manager", "Misc", "Not implemented");
        add("AntiVanish", "Misc", "Alerts only for spectator entities visible to your client; hidden staff cannot be reliably detected");
        add("Stealth Mode", "Misc", "Local quiet mode: suppresses Bada chat alerts; does not hide the mod or bypass server anti-cheat");
        add("Dupe Mode", "Misc", "Monitor only — a client mod cannot force item duplication on Donut or Fyre");
    }

    private void add(String name, String category, String status) {
        modules.add(new ClientModule(name, category, status));
    }

    public ClientModule find(String name) {
        return modules.stream().filter(module -> module.name().equals(name)).findFirst().orElse(null);
    }

    public List<ClientModule> all() { return Collections.unmodifiableList(modules); }
}
