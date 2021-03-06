package com.jh.mask_radar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jh.mask_radar.ui.map.NavViewHeightReturnSender;
import com.naver.maps.map.NaverMapSdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements NavViewHeightReturnSender, BottomNavigationView.OnNavigationItemReselectedListener {

    private int navViewHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_map, R.id.navigation_favorite, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        NaverMapSdk.getInstance(this).setClient(new NaverMapSdk.NaverCloudPlatformClient(getString(R.string.API_KEY)));
        //navViewHeight = navView.getHeight();  이 방식으로 측정시 0값 측정됨.
        navView.post(()-> navViewHeight = navView.getMeasuredHeight());

        navView.setOnNavigationItemReselectedListener(this);
    }

    @Override
    public int getNavViewHeight(){
        return navViewHeight;
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {
        //Fragment로 띄워져 있는 화면에 대한 BottomNav 버튼이 Disable 되어있지 않은 문제 있어서 추가.
        //do nothing. 터치는 되는데 작동은 안되게 설정
    }
}
