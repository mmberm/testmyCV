/*
 * ImmediateThreadExecutor.kt
 *  homebanking-android
 *  Created by angel.vargas on 3/28/19 4:30 PM
 *  Copyright (c) 2019 Globant-MX. All rights reserved.
 */

package com.antoniunix.domain.executor

import com.antoniunix.domain.executor.ThreadExecutor

class ImmediateThreadExecutor : ThreadExecutor {
    override fun execute(runnable: Runnable?) {
        runnable?.run()
    }
}