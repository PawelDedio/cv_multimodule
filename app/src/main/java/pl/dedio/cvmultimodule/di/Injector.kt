package pl.dedio.cvmultimodule.di

import android.content.Context
import pl.dedio.cvmultimodule.MainApplication
import pl.dedio.cvmultimodule.base.BaseActivity
import pl.dedio.cvmultimodule.di.components.ActivityComponent
import pl.dedio.cvmultimodule.di.components.ApplicationComponent
import pl.dedio.cvmultimodule.di.components.DaggerApplicationComponent
import pl.dedio.cvmultimodule.di.modules.ActivityModule
import pl.dedio.cvmultimodule.di.modules.ApplicationModule

class Injector(context: Context) {

    var applicationComponent: ApplicationComponent
        private set

    lateinit var activityComponent: ActivityComponent
        private set

    init {
        applicationComponent = createApplicationComponent(context)
    }

    private fun safeAssignActivityComponent(context: Context) {
        if(context is BaseActivity) {
            activityComponent = createActivityComponent(context)
        }
    }

    private fun createApplicationComponent(context: Context): ApplicationComponent {
        return when (context) {
            is MainApplication -> {
                createApplicationComponent(context)
            }
            is BaseActivity -> {
                createApplicationComponent(context)
            }
            else -> {
                throw IllegalStateException("Context must be either MainApplication or BaseActivity")
            }
        }
    }

    private fun createApplicationComponent(activity: BaseActivity): ApplicationComponent {
        val application = activity.application as MainApplication
        return createApplicationComponent(application)
    }

    private fun createApplicationComponent(application: MainApplication): ApplicationComponent {
        return DaggerApplicationComponent.builder().run {
            applicationModule(ApplicationModule(application))
            build()
        }
    }

    private fun createActivityComponent(activity: BaseActivity): ActivityComponent {
        return applicationComponent.plus(ActivityModule(activity))
    }

    companion object {
        lateinit var instance: Injector

        fun getInstance(context: Context): Injector {
            if (!::instance.isInitialized) {
                instance = Injector(context)
            }

            instance.safeAssignActivityComponent(context)

            return instance
        }
    }
}