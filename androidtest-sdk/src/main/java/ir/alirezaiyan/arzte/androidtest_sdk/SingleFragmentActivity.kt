
package ir.alirezaiyan.arzte.androidtest_sdk

import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.FrameLayout.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.get
import ir.alirezaiyan.androidtest_sdk.R

class SingleFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val content = FrameLayout(this)
        content.layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        content.id = R.id.container
        setContentView(content)
    }

    fun setFragment(graphId: Int, nodeId: Int, args: Bundle) {
        val navController = NavController(this)
        navController.navigatorProvider.addNavigator(FragmentNavigator(this, supportFragmentManager, 123))
        val navGraph = navController.navInflater.inflate(graphId)
        val node = navGraph[nodeId]
        val fragmentClass = (node as FragmentNavigator.Destination).className

        val fragment = androidx.fragment.app.Fragment.instantiate(this, fragmentClass).apply {
            arguments = args
        }

        supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment, "TEST")
                .commit()

    }
}
