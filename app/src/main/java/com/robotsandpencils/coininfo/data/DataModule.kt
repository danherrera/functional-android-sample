package com.robotsandpencils.coininfo.data

import com.robotsandpencils.coininfo.data.db.databaseModule
import com.robotsandpencils.coininfo.data.network.networkModule

val dataModules = listOf(networkModule, databaseModule)