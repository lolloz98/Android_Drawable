package com.carpa.simpledrawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.carpa.simpledrawer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Instead of:
        //setContentView(R.layout.activity_main);
        //we use data binding: a way to tell the app where to find views at compile time.
        //In this case it would have been best to use view binding https://developer.android.com/topic/libraries/view-binding.
        //Since it is a simple tutorial I'd preferred to show how to set up data binding.
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.myNavHostFrag);
        drawerLayout = binding.drawerLayout;
        NavigationUI.setupWithNavController(binding.navView, navController);
        //If we wanted we could have used another tool bar. But we are good with the default :)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        //To lock the drawer just to the start fragment:
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController nc, @NonNull NavDestination nd, @Nullable Bundle arguments) {
                if (nd.getId() == nc.getGraph().getStartDestination()) {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                } else {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        // if we did not have a drawer: return navController.navigateUp()
        //Drawer handling the stack:
        //If you are in one fragment and you have navigated into it through the drawer, the back button
        //will take you to the start fragment (every other fragment above it is automatically popped)
        return NavigationUI.navigateUp(navController, drawerLayout);
    }
}
