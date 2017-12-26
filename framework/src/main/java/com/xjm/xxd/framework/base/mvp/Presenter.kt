package com.xjm.xxd.framework.base.mvp

/**
 * Created by queda on 2017/12/26.
 */

interface Presenter<in V : Viewer> {

    fun attachViewer(v: V)

    fun detachViewer()

}