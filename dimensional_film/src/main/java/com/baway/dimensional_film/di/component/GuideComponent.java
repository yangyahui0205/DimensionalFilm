package com.baway.dimensional_film.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.baway.dimensional_film.di.module.GuideModule;
import com.baway.dimensional_film.mvp.contract.GuideContract;

import com.jess.arms.di.scope.ActivityScope;
import com.baway.dimensional_film.mvp.ui.activity.GuideActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/06/2019 15:15
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = GuideModule.class, dependencies = AppComponent.class)
public interface GuideComponent {
    void inject(GuideActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        GuideComponent.Builder view(GuideContract.View view);

        GuideComponent.Builder appComponent(AppComponent appComponent);

        GuideComponent build();
    }
}