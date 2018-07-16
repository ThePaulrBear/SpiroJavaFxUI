package paul.wintz.nodes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import paul.wintz.utils.logging.Lg;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

class PlugView {

    private static final String TAG = Lg.makeTAG(PlugView.class);
    private final Circle plugEnd;
    private final PositionProperty connectionPoint;
    private final Set<Circle> dropReceivers;

    public PlugView(PositionProperty connectionPoint, NodesCanvasView nodesCanvasView) {
        this.dropReceivers = nodesCanvasView.getDropReceivers();
        plugEnd = new Circle(10, Color.BLACK);

        FXUtils.setUpDragging(plugEnd, new CircleDraggable(), event -> {
            Lg.v(TAG, "%s dragDropped", this);
            for(Circle circle : dropReceivers) {
                if(FXUtils.doCirclesIntersect(plugEnd, circle)) {
                    bindToCircle(circle);
                    return;
                }
            }
            bindEndToLeftOfCordConnection();
        });

        plugEnd.setOnDragEntered(event -> Lg.d(TAG, "onDragEntered"));
        plugEnd.setOnDragOver(event -> Lg.d(TAG, "onDragOver"));
        plugEnd.setOnMouseDragOver(event -> Lg.d(TAG, "onMouseDragOver"));

        this.connectionPoint = connectionPoint;
        bindEndToLeftOfCordConnection();

        nodesCanvasView.add(plugEnd);

        final FlowCurve cordCurve = new FlowCurve(
                plugEnd.centerXProperty(),
                plugEnd.centerYProperty(),
                connectionPoint.x(),
                connectionPoint.y());
        nodesCanvasView.add(cordCurve);

    }

    public void bindEndToLeftOfCordConnection() {
        plugEnd.centerXProperty().bind(this.connectionPoint.x().add(-20));
        plugEnd.centerYProperty().bind(this.connectionPoint.y());
    }

    public void bindToCircle(Circle circle) {
        Lg.i(TAG, "Binding " + circle + " to " + this);

        plugEnd.centerXProperty().bind(circle.centerXProperty());
        plugEnd.centerYProperty().bind(circle.centerYProperty());
    }

    private class CircleDraggable implements FXUtils.DraggableWrapper {
        @Override
        public double getX() {
            return plugEnd.getCenterX();
        }

        @Override
        public double getY() {
            return plugEnd.getCenterY();
        }

        @Override
        public void setX(double newX) {
            plugEnd.centerXProperty().unbind();
            plugEnd.centerXProperty().setValue(newX);
        }

        @Override
        public void setY(double newY) {
            plugEnd.centerYProperty().unbind();
            plugEnd.centerYProperty().setValue(newY);
        }
    }
}
