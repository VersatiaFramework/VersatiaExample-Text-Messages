package dev.versatia.example.messages

import dev.versatia.messages.Messages
import dev.versatia.text.arg
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack

/** `player` is shown through the built-in presentation of `Player`, which gives `{player.world}` and `{player.location}`. */
class Welcome(private val messages: Messages, private val shop: Shop) : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        messages["welcome.join"].send(event.player, arg("player", event.player))
        shop.sell(event.player, ItemStack.of(Material.DIAMOND), 5)
    }
}
