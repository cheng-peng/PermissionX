package com.permissionx.cxpdev

import androidx.fragment.app.FragmentActivity

/**
 * 文 件 名: PermissionX
 * 创 建 人: CXP
 * 创建日期: 2020-11-20 0:37
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
object PermissionX {

    private const val TAG = "InvisibleFragment"

    fun request(activity: FragmentActivity,vararg permission:String,callback: PermissionCallback){
        val fragmentManager=activity.supportFragmentManager
        val existedFragment=fragmentManager.findFragmentByTag(TAG)
        val fragment=if (existedFragment!=null){
            existedFragment as InvisibleFragment
        }else{
            val invisibleFragment=InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback,*permission)
    }
}