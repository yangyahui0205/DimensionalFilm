package com.baway.dimensional_film.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.baway.dimensional_film.di.module.StartPageModule;
import com.baway.dimensional_film.mvp.contract.StartPageContract;

import com.jess.arms.di.scope.ActivityScope;
import com.baway.dimensional_film.mvp.ui.activity.StartPageActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/06/2019 16:34
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = StartPageModule.class, dependencies = AppComponent.class)
public interface StartPageComponent {
    void inject(StartPageActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        StartPageComponent.Builder view(StartPageContract.View view);

        StartPageComponent.Builder appComponent(AppComponent appComponent);

        StartPageComponent build();
    }
}