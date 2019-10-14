package com.baway.dimensional_film.mvp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.baway.dimensional_film.R;
import com.baway.dimensional_film.mvp.ui.fragment.CinemaFragment;
import com.baway.dimensional_film.mvp.ui.fragment.FilmFragment;
import com.baway.dimensional_film.mvp.ui.fragment.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmHomeActivity extends AppCompatActivity {

    @BindView(R.id.rb_film)
    RadioButton rbFilm;
    @BindView(R.id.rb_cinema)
    RadioButton rbCinema;
    @BindView(R.id.rb_my)
    RadioButton rbMy;
    @BindView(R.id.rg_home_bottom_bar)
    RadioGroup rgHomeBottomBar;
    private FragmentTransaction fragmentTransaction;
    private CinemaFragment cinemaFragment;
    private FilmFragment filmFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_home);
        ButterKnife.bind(this);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        cinemaFragment = new CinemaFragment();
        filmFragment = new FilmFragment();
        mineFragment = new MineFragment();

        fragmentTransaction
                .add(R.id.fl_home_layout, filmFragment).show(filmFragment)
                .add(R.id.fl_home_layout, cinemaFragment).hide(cinemaFragment)
                .add(R.id.fl_home_layout, mineFragment).hide(mineFragment)
                .commit();
        rgHomeBottomBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (checkedId) {
                    case R.id.rb_film:
                        transaction.show(filmFragment).hide(cinemaFragment).hide(mineFragment);
                        break;
                    case R.id.rb_cinema:
                        transaction.show(cinemaFragment).hide(filmFragment).hide(mineFragment);
                        break;
                    case R.id.rb_my:
                        transaction.show(mineFragment).hide(cinemaFragment).hide(filmFragment);
                        break;

                }
                transaction.commit();
            }
        });
    }
}
