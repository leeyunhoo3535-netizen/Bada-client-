package com.imbada.badaclient;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public final class BadaClickGui extends Screen {
    private static final int BLUE = 0xFF168BFF;
    private static final int DARK = 0xE8101724;
    private static final int PANEL = 0xF0182638;
    private static final int ROW = 0xFF20334A;
    private static final int ROW_ON = 0xFF075A9E;
    private static final int TEXT = 0xFFFFFFFF;
    private static final int MUTED = 0xFF9DB2C8;
    private static final int PANEL_WIDTH = 360;
    private static final int ROW_HEIGHT = 22;
    private final List<ClientModule> modules = BadaClient.MODULES.all();
    private int scroll;

    public BadaClickGui() { super(Text.literal("Bada Client")); }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        int left = (width - PANEL_WIDTH) / 2;
        int top = Math.max(20, (height - 330) / 2);
        context.fill(left, top, left + PANEL_WIDTH, top + 330, DARK);
        context.fill(left, top, left + PANEL_WIDTH, top + 3, BLUE);
        context.drawText(textRenderer, "BADA CLIENT", left + 14, top + 12, TEXT, true);
        context.drawText(textRenderer, "1.21.11  |  RIGHT SHIFT TO TOGGLE", left + 14, top + 27, MUTED, false);
        int y = top + 48 - scroll;
        String currentCategory = "";
        for (ClientModule module : modules) {
            if (!module.category().equals(currentCategory)) {
                currentCategory = module.category();
                context.drawText(textRenderer, currentCategory.toUpperCase(), left + 14, y, BLUE, true);
                y += 16;
            }
            if (y >= top + 46 && y + ROW_HEIGHT <= top + 322) {
                int color = module.enabled() ? ROW_ON : ROW;
                context.fill(left + 10, y, left + PANEL_WIDTH - 10, y + ROW_HEIGHT - 2, color);
                context.drawText(textRenderer, module.name(), left + 18, y + 6, TEXT, false);
                String state = module.enabled() ? "ON" : "OFF";
                context.drawText(textRenderer, state, left + PANEL_WIDTH - 42, y + 6, module.enabled() ? 0xFF65C7FF : MUTED, true);
            }
            y += ROW_HEIGHT;
        }
        context.drawText(textRenderer, "Click a module to toggle its UI state  |  ESC to close", left + 12, top + 315, MUTED, false);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            int left = (width - PANEL_WIDTH) / 2;
            int top = Math.max(20, (height - 330) / 2);
            int y = top + 48 - scroll;
            String currentCategory = "";
            for (ClientModule module : modules) {
                if (!module.category().equals(currentCategory)) {
                    currentCategory = module.category();
                    y += 16;
                }
                if (mouseX >= left + 10 && mouseX <= left + PANEL_WIDTH - 10 && mouseY >= y && mouseY < y + ROW_HEIGHT - 2) {
                    module.toggle();
                    return true;
                }
                y += ROW_HEIGHT;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        scroll = Math.max(0, Math.min(250, scroll - (int) (verticalAmount * 24)));
        return true;
    }

    @Override
    public boolean shouldPause() { return false; }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
