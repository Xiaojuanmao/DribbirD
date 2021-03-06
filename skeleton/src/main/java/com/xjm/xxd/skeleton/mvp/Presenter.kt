package com.xjm.xxd.skeleton.mvp

/**
 * Created by queda on 2017/12/26.
 */

interface Presenter<in V : Viewer> {

    fun attachViewer(v: V)

    fun detachViewer()

}