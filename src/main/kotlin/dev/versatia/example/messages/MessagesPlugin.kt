package dev.versatia.example.messages

import dev.versatia.Phase
import dev.versatia.FriendlyName
import dev.versatia.VersatiaPlugin
import dev.versatia.lifecycle.AutoInvoke
import dev.versatia.messages.Messages
import dev.versatia.text.Text
import dev.versatia.text.arg
import dev.versatia.text.present
import dev.versatia.text.presentation
import java.time.Duration
import net.kyori.adventure.text.Component.text

/** `Messages` and `Text` are prototypes VersatiaCore exposes to every plugin: this plugin's own bank, read from its `messages/` directory, and its own parser. */
@FriendlyName("shop")
class MessagesPlugin : VersatiaPlugin() {
    /** Runs at start and at `/versatia reload shop`; the bank was reloaded just before, so an edited file shows here. */
    @AutoInvoke(Phase.PLUGIN_RELOAD_OR_START)
    fun announce(messages: Messages) {
        componentLogger.info(messages["shop.open"].get(arg("offers", 3)))
        componentLogger.info(messages["shop.offer"].get(arg("offer", Offer("Diamond", Price(5)))))
        componentLogger.info(messages["shop.closed"].get())
        logger.info("${messages.keys.size} messages: ${messages.keys}")
    }

    /** Runs once: registrations on `Text` are kept, and they apply to this plugin's messages too. */
    @AutoInvoke(Phase.PLUGIN_START_ONLY)
    fun describe(text: Text, messages: Messages) {
        // A type this plugin does not own is described here; its own types describe themselves (see Offer.kt)
        text.present<Duration> { duration ->
            presentation {
                component { text("${duration.toMinutes()} min") }
                property("seconds") { duration.toSeconds() }
            }
        }
        componentLogger.info(messages["shop.restock"].get(arg("wait", Duration.ofMinutes(3))))
        componentLogger.info(text.parse("Chat stays text: {chat}", arg("chat", "<red>not red</red>")))
    }
}
