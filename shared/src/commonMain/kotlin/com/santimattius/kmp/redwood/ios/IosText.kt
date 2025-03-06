package com.santimattius.kmp.redwood.ios

import app.cash.redwood.Modifier
import com.santimattius.kmp.redwood.example.widget.Text
import platform.UIKit.NSTextAlignmentCenter
import platform.UIKit.UIColor
import platform.UIKit.UILabel
import platform.UIKit.UIView

class IosText : Text<UIView> {
    override val value = UILabel().apply {
        textColor = UIColor.blackColor
        textAlignment = NSTextAlignmentCenter
    }

    override var modifier: Modifier = Modifier

    override fun text(text: String?) {
        value.text = text

        // This very simple integration wraps the size of whatever text is entered. Calling
        // this function will update the bounds and trigger relayout in the parent.
        value.sizeToFit()
    }
}