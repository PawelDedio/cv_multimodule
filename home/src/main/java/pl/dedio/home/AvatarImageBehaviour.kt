package pl.dedio.home

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.withStyledAttributes
import de.hdodenhof.circleimageview.CircleImageView

class AvatarImageBehaviour : CoordinatorLayout.Behavior<CircleImageView> {

    private val context: Context

    private var customStartXPosition: Float = 0f
    private var customFinalYPosition: Float = 0f
    private var customStartToolbarPosition: Float = 0f
    private var customStartHeight: Float = 0f
    private var customFinalHeight: Float = 0f
    private var avatarMaxSize: Float = 0f
    private var finalStartAvatarPadding: Float = 0f

    private var startPosition: Float = 0f
    private var startXPosition: Int = 0
    private var startToolbarPosition: Float = 0f
    private var startYPosition: Int = 0
    private var finalYPosition: Int = 0
    private var startHeight: Int = 0
    private var finalXPosition: Int = 0
    private var changeBehaviourPoint: Float = 0f


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.context = context

        context.withStyledAttributes(attrs, R.styleable.AvatarImageBehaviour) {
            customStartXPosition = getDimension(R.styleable.AvatarImageBehaviour_startXPosition, 0f)
            customFinalYPosition = getDimension(R.styleable.AvatarImageBehaviour_finalYPosition, 0f)
            customStartToolbarPosition = getDimension(R.styleable.AvatarImageBehaviour_startToolbarPosition, 0f)
            customStartHeight = getDimension(R.styleable.AvatarImageBehaviour_startHeight, 0f)
            customFinalHeight = getDimension(R.styleable.AvatarImageBehaviour_finalHeight, 0f)
            avatarMaxSize = getDimension(R.styleable.AvatarImageBehaviour_avatarBigSize, 0f)
            finalStartAvatarPadding = getDimension(R.styleable.AvatarImageBehaviour_avatarSmallMarginStart, 0f)
        }
    }

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: CircleImageView,
        dependency: View
    ): Boolean {
        return dependency is Toolbar
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: CircleImageView,
        dependency: View
    ): Boolean {
        return super.onDependentViewChanged(parent, child, dependency)
    }

    private fun updatePositions(child: CircleImageView, dependency: View) {
        if(startYPosition == 0) {
            startYPosition = dependency.y.toInt()
        }

        if(finalYPosition == 0) {
            finalYPosition = dependency.height / 2
        }

        if(startHeight == 0) {
            startHeight = child.height
        }

        if(startXPosition == 0) {
            startXPosition = (child.x + child.width / 2).toInt()
        }

        if(finalXPosition == 0) {
            finalXPosition = context.resources.getDimensionPixelOffset(R)
        }
    }
}