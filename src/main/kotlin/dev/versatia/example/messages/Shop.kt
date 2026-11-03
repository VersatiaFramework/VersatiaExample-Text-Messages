package dev.versatia.example.messages

import dev.versatia.di.LocalBean
import dev.versatia.messages.Messages
import dev.versatia.text.arg
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/** A service sending messages: the key names the message, the arguments fill it. */
@LocalBean
class Shop(private val messages: Messages) {
    fun sell(player: Player, item: ItemStack, price: Int) =
        messages["shop.bought"].send(player, arg("player", player), arg("item", item), arg("price", price))
}
