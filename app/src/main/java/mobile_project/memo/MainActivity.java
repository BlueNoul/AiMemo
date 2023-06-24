package mobile_project.memo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Main_memos main_memos = new Main_memos();
    private Main_write main_write = new Main_write();
    private Main_calendar main_calendar =new Main_calendar();
    private Main_options main_options = new Main_options();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.menu_frame_layout, main_write).commitAllowingStateLoss(); //기본 메뉴

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation); // 하단 네비게이션바
        bottomNavigationView.setSelectedItemId(R.id.menu_write);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) { // 프래그먼트 고르는곳
                FragmentTransaction transaction1 = fragmentManager.beginTransaction(); // 프래그먼트 생성
                switch (item.getItemId()){

                    case R.id.menu_board:
                        transaction1.replace(R.id.menu_frame_layout, main_memos).commitAllowingStateLoss();
                        break;
                    case R.id.menu_write:
                        transaction1.replace(R.id.menu_frame_layout, main_write).commitAllowingStateLoss();
                        break;
                    case R.id.menu_cale:
                        transaction1.replace(R.id.menu_frame_layout, main_calendar).commitAllowingStateLoss();
                        break;
                    case R.id.menu_option:
                        transaction1.replace(R.id.menu_frame_layout, main_options).commitAllowingStateLoss();
                        break;
                    default:
                        transaction1.replace(R.id.menu_frame_layout, main_memos).commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });

    }
}