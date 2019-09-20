package com.robotsandpencils.coininfo.data

import com.robotsandpencils.coininfo.data.db.DatabaseDomainMapper
import com.robotsandpencils.coininfo.data.network.NetworkDomainMapper

interface DomainMapper<F> : DatabaseDomainMapper<F>, NetworkDomainMapper<F>

