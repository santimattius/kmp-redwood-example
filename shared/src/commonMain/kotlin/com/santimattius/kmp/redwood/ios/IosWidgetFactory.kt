package com.santimattius.kmp.redwood.ios

import com.santimattius.kmp.redwood.example.widget.Button
import com.santimattius.kmp.redwood.example.widget.SchemaWidgetFactory
import com.santimattius.kmp.redwood.example.widget.Text
import platform.UIKit.UIView

object IosWidgetFactory : SchemaWidgetFactory<UIView> {
    override fun Text(): Text<UIView> = IosText()
    override fun Button(): Button<UIView> = IosButton()
}