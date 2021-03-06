package com.thepiguy.buttersb

import com.thepiguy.buttersb.commands.RegisterCommands
import com.thepiguy.buttersb.config.ButterConfig
import com.thepiguy.buttersb.utils.HudDragableConfig
import com.thepiguy.buttersb.utils.RenderHud
import net.fabricmc.api.ModInitializer
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText
import net.minecraft.text.Text


@Suppress("UNUSED")
class ButterSB : ModInitializer {

    override fun onInitialize() {
        // Init log to the console
        println("Learning Kotlin on FULL GEAR!!")

        // vigilance shit for config
        buttercfg.preload()

        // command for doing shit lol
        RegisterCommands().registerCommands()
    }

    // events go here lol
    companion object {
        private val mc = MinecraftClient.getInstance()
        val buttercfg:ButterConfig = ButterConfig()
        var butterConfigScreen:Screen? = null
        val dragablecfg = HudDragableConfig(LiteralText("Dragable"))

        var dragableConfigFlag: Boolean = false


        // does things per tick
        fun onTick() {
            if (butterConfigScreen != null) {
                mc.setScreen(butterConfigScreen)
            }
            butterConfigScreen = null
            if (dragableConfigFlag) {
                mc.setScreen(dragablecfg)
            }
            dragableConfigFlag = false
        }

        // run on world load
        fun onWorldLoad() {
            //UChat.chat("Thank You for using Butter SB")

        }

        // render thing, renders stuff each tick
        private val renderhud = RenderHud()
        fun onRender(matrices: MatrixStack, overlayMessage: Text?) {
            renderhud.renderall(matrices, overlayMessage)
        }
    }
}
