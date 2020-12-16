package weeidl.com


import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode


class ArTwo : AppCompatActivity() {
    private var arFragment: ArFragment? = null
    private var modelRenderable: ModelRenderable? = null

    //3d model credit : google.poly.com
    private val Model_URL = "https://github.com/weeidl/DeelTech/blob/master/Material/LAPTOP.glb?raw=true"

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar_two)
        arFragment = supportFragmentManager.findFragmentById(R.id.fragment2) as ArFragment?
        setUpModel()
        setUpPlane()
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private fun setUpModel() {
        ModelRenderable.builder()
            .setSource(
                this,
                RenderableSource.builder().setSource(
                    this,
                    Uri.parse(Model_URL),
                    RenderableSource.SourceType.GLB
                )
                    .setScale(0.75f)
                    .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                    .build()
            )
            .setRegistryId(Model_URL)
            .build()
            .thenAccept { renderable: ModelRenderable? ->
                modelRenderable = renderable
            }
            .exceptionally { throwable: Throwable? ->
                Log.i("Model", "cant load")
                Toast.makeText(this, "Model can't be Loaded", Toast.LENGTH_SHORT).show()
                null
            }
    }

    private fun setUpPlane() {
        arFragment!!.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane?, motionEvent: MotionEvent? ->
            val anchor = hitResult.createAnchor()
            val anchorNode =
                AnchorNode(anchor)
            anchorNode.setParent(arFragment!!.arSceneView.scene)
            createModel(anchorNode)
        }
    }

    private fun createModel(anchorNode: AnchorNode) {
        val node = TransformableNode(arFragment!!.transformationSystem)
        node.setParent(anchorNode)
        node.renderable = modelRenderable
        node.select()
    }

}