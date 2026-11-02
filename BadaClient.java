package com.imbada.badaclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.glfw.GLFW;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class BadaClient implements ClientModInitializer {
    public static final ModuleRegistry MODULES = new ModuleRegistry();
    private static KeyBinding openGuiKey;
    private final Set<UUID> alertedSpectators = new HashSet<>();

    @Override
    public void onInitializeClient() {
        MODULES.registerDefaults();
        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.bada_client.open_gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                KeyBinding.Category.MISC
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.wasPressed()) {
                if (client.currentScreen == null) client.setScreen(new BadaClickGui());
            }

            ClientModule antiVanish = MODULES.find("AntiVanish");
            ClientModule stealthMode = MODULES.find("Stealth Mode");
            boolean quietLocalOutput = stealthMode != null && stealthMode.enabled();
            if (client.player == null || client.world == null) {
                alertedSpectators.clear();
                return;
            }
            if (antiVanish != null && antiVanish.enabled()) {
                Set<UUID> currentlyVisible = new HashSet<>();
                for (PlayerEntity other : client.world.getPlayers()) {
                    if (other == client.player || !other.isSpectator()) continue;
                    UUID id = other.getUuid();
                    currentlyVisible.add(id);
                    if (alertedSpectators.add(id) && !quietLocalOutput) {
                        client.player.sendMessage(Text.literal("§b[Bada AntiVanish] §fVisible spectator: §e" + other.getName().getString()), false);
                    }
                }
                alertedSpectators.retainAll(currentlyVisible);
            } else {
                alertedSpectators.clear();
            }
        });
    }
}
