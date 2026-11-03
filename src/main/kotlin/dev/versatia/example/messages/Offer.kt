package dev.versatia.example.messages

import dev.versatia.text.Presentable
import dev.versatia.text.presentation
import net.kyori.adventure.text.Component.text

/** Describes itself: `{offer}` is the item, `{offer.price}` the price, which has properties of its own. */
class Offer(val item: String, val price: Price) : Presentable {
    override fun presentation() = presentation {
        component { text(item) }
        property("item") { item }
        property("price") { price }
    }
}

class Price(val coins: Int) : Presentable {
    override fun presentation() = presentation {
        component { text("$coins coins") }
        property("coins") { coins }
    }
}
