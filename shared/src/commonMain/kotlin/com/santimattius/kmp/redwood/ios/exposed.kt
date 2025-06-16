
import app.cash.redwood.Modifier
import app.cash.redwood.layout.uiview.UIViewRedwoodLayoutWidgetFactory
import app.cash.redwood.lazylayout.uiview.UIViewRedwoodLazyLayoutWidgetFactory
import app.cash.redwood.treehouse.AppService
import app.cash.redwood.treehouse.Content
import app.cash.redwood.treehouse.TreehouseUIView
import app.cash.redwood.treehouse.TreehouseView
import app.cash.redwood.treehouse.TreehouseView.WidgetSystem
import app.cash.redwood.treehouse.bindWhenReady
import com.santimattius.kmp.redwood.example.protocol.host.DragonBallProtocolFactory
import com.santimattius.kmp.redwood.example.widget.DragonBallWidgetFactory
import com.santimattius.kmp.redwood.example.widget.DragonBallWidgetSystem
import com.santimattius.kmp.redwood.ios.SearchLauncher
import okio.ByteString
import okio.ByteString.Companion.toByteString
import okio.Closeable
import platform.Foundation.NSData

// Used to export types to Objective-C / Swift.
fun exposedTypes(
    searchLauncher: SearchLauncher,
    searchWidgetFactory: DragonBallWidgetFactory<*>,
    protocolFactory: DragonBallProtocolFactory<*>,
    treehouseUIView: TreehouseUIView,
    uiViewRedwoodLayoutWidgetFactory: UIViewRedwoodLayoutWidgetFactory,
    uiViewRedwoodLazyLayoutWidgetFactory: UIViewRedwoodLazyLayoutWidgetFactory,
    treehouseWidgetSystem: WidgetSystem<*>,
    widgetSystem: DragonBallWidgetSystem<*>,
) {
    throw AssertionError()
}

fun byteStringOf(data: NSData): ByteString = data.toByteString()

fun modifier(): Modifier = Modifier

fun <A : AppService> bindWhenReady(
    content: Content,
    view: TreehouseView<*>,
): Closeable = content.bindWhenReady(view)