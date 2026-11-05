# VersatiaExample-Text-Messages

Messages read from YAML files under `messages/`, reloaded with the plugin, sent with runtime arguments: inject `Messages` and write `messages["shop.bought"].send(player, arg("player", player), arg("item", stack), arg("price", 5))`. The text decides what to show of an argument, `{offer}`, `{offer.price.coins}`, `{player.location}`, and the plugin's own `Text` describes the types it shows.

Part of the [Versatia examples](https://github.com/VersatiaFramework/VersatiaExamples); documentation: [Messages](https://versatia.dev/docs/messages).

**Start with** `src/main/resources/resources/messages/` and [`MessagesPlugin.kt`](src/main/kotlin/dev/versatia/example/messages/MessagesPlugin.kt), then [`Offer.kt`](src/main/kotlin/dev/versatia/example/messages/Offer.kt) and [`Shop.kt`](src/main/kotlin/dev/versatia/example/messages/Shop.kt).

## What the log shows

```
The shop is open: 3 offers today.
Today: Diamond for 5 coins (5 coins).
The shop is closed.
Come back tomorrow.
6 messages: [shop.open, shop.offer, shop.restock, shop.bought, shop.closed, welcome.join]
New stock in 3 min (180 s).
Chat stays text: <red>not red</red>
```

A player joining is greeted with their name, world and position, then sees `shop.bought` for a
sample sale made through [`Shop.kt`](src/main/kotlin/dev/versatia/example/messages/Shop.kt).

## Things to try

- Edit `plugins/VersatiaExample-Text-Messages/messages/shop.yml` on the server and run
  `/versatia reload shop`: the messages `announce()` logs show the new text, nothing in the plugin knows about reloading.
- Break the YAML and reload: the reload fails with `shop failed to reload: messages/shop.yml is not valid YAML at line ...`,
  and the previous messages stay in use.
- Write `{offer.colour}` in `shop.offer` and reload: `announce()` renders it at reload, so the
  reload fails with `{offer} has no property "colour"; it has: item, price`. Write `{ofer}`:
  `No argument "ofer"; the arguments are: offer`. Versatia does not check braces when it loads a
  file, so a message rendered only later fails only then. Braces that name nothing known, such as `{}` or
  `{ x }`, stay plain text.
- Ask for `messages["shop.byu"]`: `Plugin VersatiaExample-Text-Messages has no message "shop.byu";
  the messages under "shop" are: shop.open, shop.offer, shop.restock, shop.bought, shop.closed`.

## Building

Requires JDK 25 and VersatiaAPI in your local Maven repository
(`./gradlew publishToMavenLocal` in each checkout; see
[Building the framework](https://versatia.dev/docs/contributing/building)); running it requires a
Paper 26.3 server with VersatiaCore installed, and the
[CommandAPI](https://commandapi.jorel.dev) 12.x plugin for `/versatia reload` (without it, a
plugin can still reload itself with `versatiaReload()`).

```bash
./gradlew build
```

To have the JAR copied to your server after every build, copy `local.properties.example` to
`local.properties` and set `pluginsDir`. The generated descriptor is in
`build/generated/ksp/main/kotlin`; open it to see exactly what the runtime executes.
