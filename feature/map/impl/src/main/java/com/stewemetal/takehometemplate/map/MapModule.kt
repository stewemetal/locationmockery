package com.stewemetal.takehometemplate.map

import com.stewemetal.takehometemplate.login.contract.MapNavGraphFactory
import com.stewemetal.takehometemplate.map.navigation.MapNavGraphFactoryImpl
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@ComponentScan
class MapModule {
    @Factory
    fun mapNavigationGraphFactory(): MapNavGraphFactory =
        MapNavGraphFactoryImpl()
}
