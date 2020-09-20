/*
 * Created by canberkozcelik at 2020
 * Last modified 9/19/20 4:06 PM
 */

package com.canberkozcelik.cstory.utility.extension

/**Example**/
/**
 * var any:Any? = null
 * if(any.isNull()) -> false
 * any = Any()
 *
 * if(any.isNull()) -> true
 * */
fun Any?.isNotNull(): Boolean {
    return (this != null)
}

/**Example**/
/**
 * var any:Any? = null
 * any.isNull(object :ReturnListener{
 *
 *  override fun onTrue() {
 *      this line doesn't run
 *  }
 *
 *  override fun onFalse() {
 *      this line runs
 *  }
 *
 * }
 * )
 * */
fun Any?.isNotNull(listener: ReturnListener): Boolean {
    if (this.isNotNull()) {
        listener.onTrue()
    } else {
        listener.onFalse()
    }
    return (this != null)
}

/**
 * if the variable isn't null, the function runs
 */
inline fun <R> R?.notNull(f: (it: R) -> Unit) {
    if (this != null) {
        f(this)
    }
}

interface ReturnListener {
    fun onTrue()
    fun onFalse()
}