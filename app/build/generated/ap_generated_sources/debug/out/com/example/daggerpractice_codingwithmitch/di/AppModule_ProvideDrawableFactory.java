// Generated by Dagger (https://dagger.dev).
package com.example.daggerpractice_codingwithmitch.di;

import android.app.Application;
import android.graphics.drawable.Drawable;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ProvideDrawableFactory implements Factory<Drawable> {
  private final Provider<Application> applicationProvider;

  public AppModule_ProvideDrawableFactory(Provider<Application> applicationProvider) {
    this.applicationProvider = applicationProvider;
  }

  @Override
  public Drawable get() {
    return provideDrawable(applicationProvider.get());
  }

  public static AppModule_ProvideDrawableFactory create(Provider<Application> applicationProvider) {
    return new AppModule_ProvideDrawableFactory(applicationProvider);
  }

  public static Drawable provideDrawable(Application application) {
    return Preconditions.checkNotNullFromProvides(AppModule.provideDrawable(application));
  }
}
