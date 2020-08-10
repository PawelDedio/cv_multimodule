package pl.dedio.home

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.withStyledAttributes
import de.hdodenhof.circleimageview.CircleImageView

class AvatarImageBehaviour : CoordinatorLayout.Behavior<CircleImageView> {

    private var customStartXPosition: Float = 0f
    private var customFinalYPosition: Float = 0f
    private var customStartToolbarPosition: Float = 0f
    private var customStartHeight: Float = 0f
    private var customFinalHeight: Float = 0f


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        context.withStyledAttributes(attrs, R.styleable.AvatarImageBehaviour) {
            customStartXPosition = getDimension(R.styleable.AvatarImageBehaviour_startXPosition, 0f)
            customFinalYPosition = getDimension(R.styleable.AvatarImageBehaviour_finalYPosition, 0f)
            customStartToolbarPosition = getDimension(R.styleable.AvatarImageBehaviour_startToolbarPosition, 0f)
            customStartHeight = getDimension(R.styleable.AvatarImageBehaviour_startHeight, 0f)
            customFinalHeight = getDimension(R.styleable.AvatarImageBehaviour_finalHeight, 0f)
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
}