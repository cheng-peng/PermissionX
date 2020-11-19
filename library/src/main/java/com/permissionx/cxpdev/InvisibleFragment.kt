package com.permissionx.cxpdev

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * 文 件 名: InvisibleFragment
 * 创 建 人: CXP
 * 创建日期: 2020-11-20 0:25
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */

typealias PermissionCallback=(Boolean,List<String>)->Unit

class InvisibleFragment : Fragment() {
    private var callback: PermissionCallback? = null

    fun requestNow(cb: PermissionCallback, vararg permission: String) {
        callback = cb
        requestPermissions(permission, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted=deniedList.isEmpty()
            callback?.let { it(allGranted,deniedList) }
        }
    }
}