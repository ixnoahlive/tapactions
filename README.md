# TapActions
Hypixel's Housing mode is great for aspiring game designers, builders and developers! Unfortunately, the basic coding systems can be a bit lacking at times in features! Some things such as the sidebar and bossbar can barely or not at all be fully utilised!

**TapActions** lets players experience another layer of interactivity, you get to have an enhanced experience in any supported house you join! Creators using the mod get give the tools to modify and tweak so much more all at the click (or, tap) of a button!

Players can install the mod and experience a better Housing with no wait!

## For Creators
To use a TapAction, you must add a "Display Title" action in your house. Set the enter, exit and stay to 0 seconds. In the title, edit the text and type out the name and options as `/createaction <name> key=value | ...` 

For a list of all these actions, check the [wiki page](https://github.com/NoahTheNerd/tapactions/wiki)!

> [!CAUTION]
> Due to the beta state, we may make drastic changes to how certain actions work.

## For Developers
You can access TapActions source code [here](https://github.com/NoahTheNerd/tapactions). 

```kotlin
val myAction = { params: MutableMap<String, String> ->
    // code here
}

ActionManager.registerAction("mymod:myaction", myaction)
```

## Nightly Releases
Be warned! These releases contain *bugs*! Make sure to report them on our [issues](https://github.com/NoahTheNerd/tapactions/issues) page!
* Head to our [GitHub](https://github.com/NoahTheNerd/tapactions)
* Click on "Actions" on the navigation bar up top
* Open the latest workflow run (the top one)
* Scroll down and download `mod-jar.zip`, this contains the `.jar` file of the latest nightly release! Enjoy!