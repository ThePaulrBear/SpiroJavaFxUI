package paul.wintz.nodes;

import javafx.beans.binding.Bindings;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import paul.wintz.utils.logging.Lg;

import java.util.Set;

class PlugView {

    private static final String TAG = Lg.makeTAG(PlugView.class);
    private final Circle plugEndCircle;
    private Point2D connectionPoint;
    private final Set<Circle> dropReceivers;

    public PlugView(Point2D connectionPoint, Pane parent, Set<Circle> dropReceivers) {
        this.connectionPoint = connectionPoint;
        this.dropReceivers = dropReceivers;
        plugEndCircle = new Circle(10, Color.BLACK);

        FXUtils.setUpDragging(plugEndCircle, new CircleDraggable(), event -> {
            Lg.v(TAG, "%s dragDropped", this);
            for(Circle circle : this.dropReceivers) {
                if(FXUtils.doCirclesIntersect(plugEndCircle, circle)) {
                    bindToCircle(circle);
                    return;
                }
            }
            bindEndToLeftOfCordConnection();
        });

        bindEndToLeftOfCordConnection();

        parent.getChildren().add(plugEndCircle);

        final FlowCurve cordCurve = new FlowCurve(
                connectionPoint.getX(),
                connectionPoint.getY(),
                plugEndCircle.centerXProperty(),
                plugEndCircle.centerYProperty());
        parent.getChildren().add(cordCurve);

    }

    public void bindEndToLeftOfCordConnection() {
        plugEndCircle.setCenterX(this.connectionPoint.getX() - 20);
        plugEndCircle.setCenterY(this.connectionPoint.getY());
    }

    public void bindToCircle(Circle circle) {
        Lg.i(TAG, "Binding " + circle + " to " + this);

        circle.localToSceneTransformProperty().addListener(observable -> Lg.v(TAG, "localToSceneTransformProperty changed"));

        plugEndCircle.centerXProperty().bind(
                Bindings.createDoubleBinding(
                        () -> positionInLocal(circle).getX(),
                        circle.getParent().translateXProperty(), plugEndCircle.getParent().translateXProperty()));
        plugEndCircle.centerYProperty().bind(
                Bindings.createDoubleBinding(
                        () -> positionInLocal(circle).getY(),
                        circle.getParent().translateYProperty(), plugEndCircle.getParent().translateYProperty()));
    }

    private Point2D positionInLocal(Circle circle) {
        return plugEndCircle.sceneToLocal(circle.localToScene(circle.getCenterX(), circle.getCenterY()));
    }

    private class CircleDraggable implements FXUtils.DraggableWrapper {
        @Override
        public double getX() {
            return plugEndCircle.getCenterX();
        }

        @Override
        public double getY() {
            return plugEndCircle.getCenterY();
        }

        @Override
        public void setX(double newX) {
            plugEndCircle.centerXProperty().unbind();
            plugEndCircle.setCenterX(newX);
        }

        @Override
        public void setY(double newY) {
            plugEndCircle.centerYProperty().unbind();
            plugEndCircle.setCenterY(newY);
        }
    }
}
