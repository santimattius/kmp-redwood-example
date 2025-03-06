@file:OptIn(BetaInteropApi::class, ExperimentalForeignApi::class)

package com.santimattius.kmp.redwood.ios

import app.cash.redwood.Modifier
import com.santimattius.kmp.redwood.example.widget.Button
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import platform.UIKit.UIButton
import platform.UIKit.UIColor
import platform.UIKit.UIControlEventTouchUpInside
import platform.UIKit.UIControlStateNormal
import platform.UIKit.UIView
import platform.objc.sel_registerName

class IosButton : Button<UIView> {

    override val value = UIButton().apply {
        backgroundColor = UIColor.blueColor
    }

    override var modifier: Modifier = Modifier

    override fun text(text: String?) {
        value.setTitle(text, UIControlStateNormal)
    }

    override fun enabled(enabled: Boolean) {
        value.enabled = enabled
    }

    private val clickedPointer = sel_registerName("clicked")


    @ObjCAction
    fun clicked() {
        onClick?.invoke()
    }

    private var onClick: (() -> Unit)? = null
    override fun onClick(onClick: (() -> Unit)?) {
        this.onClick = onClick
        if (onClick != null) {
            value.addTarget(this, clickedPointer, UIControlEventTouchUpInside)
        } else {
            value.removeTarget(this, clickedPointer, UIControlEventTouchUpInside)
        }
    }
}
