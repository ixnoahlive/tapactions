# TapActions
A pretty darn modular Housing mod letting creators use custom actions.

> [!CAUTION]
> Seeing as the mod is not fully stable, action names like `tap:bossbar` are subject to change!
> Do not use this in production of your house yet!

## Contributing & Dev Usage
### Adding a new action
```kt
val myFunction = { params : MutableMap<String, String> ->
    // your code here
}   

// live.ixnoah.tapactions.ActionManager
ActionManager.registerAction(
    "mymod:exampleAction",
    myFunction, 
    mutableListOf("requiredParam1","requiredParam2") // optional
// )
```

Please organise into classes if contributing to master branch. Cheers.
