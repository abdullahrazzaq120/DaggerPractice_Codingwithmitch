package com.example.daggerpractice_codingwithmitch.di;

import com.example.daggerpractice_codingwithmitch.di.auth.AuthModule;
import com.example.daggerpractice_codingwithmitch.di.auth.AuthViewModelsModule;
import com.example.daggerpractice_codingwithmitch.ui.auth.AuthActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = ActivityBuilderModule_ContributeAuthActivity.AuthActivitySubcomponent.class)
public abstract class ActivityBuilderModule_ContributeAuthActivity {
  private ActivityBuilderModule_ContributeAuthActivity() {}

  @Binds
  @IntoMap
  @ClassKey(AuthActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      AuthActivitySubcomponent.Factory builder);

  @Subcomponent(modules = {AuthViewModelsModule.class, AuthModule.class})
  public interface AuthActivitySubcomponent extends AndroidInjector<AuthActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<AuthActivity> {}
  }
}
