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
* Head to our [Workflows](https://github.com/NoahTheNerd/tapactions/actions/workflows/build.yml?query=is%3Acompleted+branch%3Amaster) page
* Open the top result
* Scroll down and download `mod-jar.zip`
* Unzip this to obtain the latest nightly mod jar! These builds are automagically generated and will contain some bugs!