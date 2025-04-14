package net.criminalduck.ducksbackpackmod.classes.workbench;

import com.mojang.blaze3d.systems.RenderSystem;
import net.criminalduck.ducksbackpackmod.DucksBackpackMod;
import net.criminalduck.ducksbackpackmod.classes.backpack.BackpackMenu;
import net.criminalduck.ducksbackpackmod.classes.slots.RestrictedSlot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.ShulkerBoxScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

public class WorkbenchScreen extends AbstractContainerScreen<WorkbenchMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(DucksBackpackMod.MODID, "textures/gui/workbench_gui.png");

    public WorkbenchScreen(WorkbenchMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageHeight = 170;
        this.inventoryLabelY += 5;
        this.titleLabelY = 10000;
        ++this.imageHeight;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        for (Slot slot : this.menu.slots) {
            if (slot instanceof RestrictedSlot restrictedSlot && !slot.hasItem()) {
                ResourceLocation iconTexture = restrictedSlot.getIconTexture();
                RenderSystem.setShaderTexture(0, iconTexture);
                guiGraphics.blit(iconTexture, x + slot.x, y + slot.y, 0, 0, 16, 16);
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
