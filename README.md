# Bada Client — Fabric 1.21.11

A blue/black Fabric client-side starter project for Minecraft Java 1.21.11.

## Included
- Right Shift opens the Bada ClickGUI.
- Click module rows to toggle them; scroll to view the full list.
- Module categories include Combat, Movement, Render, World, and Misc.
- **AntiVanish (limited):** when enabled, alerts if a spectator player entity is actually present and visible in the client world. It cannot detect staff who are fully hidden or merely observing through server-side tools.
- **Stealth Mode (local quiet mode):** suppresses Bada Client's own AntiVanish chat notifications while enabled. It does not hide the mod, spoof packets, bypass anti-cheat, or prevent staff from detecting client behavior.
- **Dupe Mode (monitor placeholder):** included in the GUI, but does not duplicate items. A client-side mod cannot force Donut SMP or Fyre SMP to duplicate server-authoritative inventory items.
- Auto Builder is listed as planned; blueprint loading and automatic block placement are not implemented yet.

## Important limitations
Most listed combat, movement, render, and world modules are UI placeholders and do not perform gameplay actions yet. The AntiVanish visible-spectator alert and Stealth Mode's local notification suppression are the only gameplay-adjacent features currently implemented.

## Requirements
- JDK 21
- Minecraft Java 1.21.11
- Fabric Loader 0.18.1 or newer
- Fabric API for 1.21.11
- Gradle (Loom downloads build dependencies from Fabric Maven)

## Build
From this directory, run:

```bash
gradle build
```

The remapped mod JAR should be created in `build/libs/`. Install it together with Fabric API in your Minecraft 1.21.11 Fabric `mods` folder.

## Keybind
- Right Shift: open ClickGUI
