package com.baway.dimensional_film.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.baway.dimensional_film.di.module.FilmModule;
import com.baway.dimensional_film.mvp.contract.FilmContract;

import com.jess.arms.di.scope.FragmentScope;
import com.baway.dimensional_film.mvp.ui.fragment.FilmFragment;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/08/2019 21:15
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = FilmModule.class, dependencies = AppComponent.class)
public interface FilmComponent {
    void inject(FilmFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        FilmComponent.Builder view(FilmContract.View view);

        FilmComponent.Builder appComponent(AppComponent appComponent);

        FilmComponent build();
    }
}