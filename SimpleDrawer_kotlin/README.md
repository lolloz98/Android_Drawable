# SimpleDrawer_kotlin
Guide on how to set up an app with a drawer and the navigation component.
## Description
This is a really simple app: we are just able to use the drawer to navigate to two different locations and a button to navigate in another fragment.\
I use data binding: the app is simple, if you do not know what it is I challenge you to find out :)\
## Important notes
If you look at the app you can guess quite easily how it works. The only tricky part is to understand how the drawer menu and the navigator are connected.
In order to make these two elements cooperate we need to specify consistent ids for the items in the menu: these must be the same of the fragments ids in the navigator.xml.\
To better understand compare the file res/navigation/navigator.xml to res/menu/bottom_nav.xml.\
Inside the DrawerLayout in activity_main.xml, we can see that there is a NavigationView element. This makes the configuration much easier.\
In MainActivity there are some important considerations about the default behaviour of the back button if you decide to use this implementation.\

Did you discover what data binding is?
Check it out: https://developer.android.com/topic/libraries/data-binding
