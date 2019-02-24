package paul.wintz.nodes;

import javafx.beans.binding.Bindings;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import paul.wintz.doublesourcehierarchy.Plug;
import paul.wintz.utils.logging.Lg;

import java.util.Set;

class PlugView {

    private static final String TAG = Lg.makeTAG(PlugView.class);
    private final Circle plugEndCircle;
    private Point2D connectionPoint;
    private final Set<NodeView.SocketCircle> dropReceivers;
    private Plug plug;

    PlugView(Point2D connectionPoint, Pane parent, Set<NodeView.SocketCircle> dropReceivers, Plug plug) {
        this.connectionPoint = connectionPoint;
        this.dropReceivers = dropReceivers;
        this.plug = plug;
        plugEndCircle = new Circle(10, Color.BLACK);

        FXUtils.setUpDragging(plugEndCircle, new CircleDraggable(), this::onPlugDropped);

        bindEndToLeftOfCordConnection();

        parent.getChildren().add(plugEndCircle);

        final FlowCurve cordCurve = new FlowCurve(
                connectionPoint.getX(),
                connectionPoint.getY(),
                plugEndCircle.centerXProperty(),
                plugEndCircle.centerYProperty());
        parent.getChildren().add(cordCurve);

    }

    private void bindEndToLeftOfCordConnection() {
        plugEndCircle.setCenterX(this.connectionPoint.getX() - 20);
        plugEndCircle.setCenterY(this.connectionPoint.getY());
    }

    private void bindToSocketCircle(NodeView.SocketCircle circle) {
        Lg.i(TAG, "Binding " + this + " to " + circle);

        circle.localToSceneTransformProperty().addListener(observable -> Lg.v(TAG, "localToSceneTransformProperty changed"));

        plugEndCircle.centerXProperty().bind(
                Bindings.createDoubleBinding(
                        () -> positionInLocal(circle).getX(),
                        circle.getParent().translateXProperty(), plugEndCircle.getParent().translateXProperty()));
        plugEndCircle.centerYProperty().bind(
                Bindings.createDoubleBinding(
                        () -> positionInLocal(circle).getY(),
                        circle.getParent().translateYProperty(), plugEndCircle.getParent().translateYProperty()));

        circle.onConnect(plug);
    }

    private Point2D positionInLocal(Circle circle) {
        return plugEndCircle.sceneToLocal(circle.localToScene(circle.getCenterX(), circle.getCenterY()));
    }

    private void onPlugDropped(MouseEvent event) {
        for (NodeView.SocketCircle circle : this.dropReceivers) {
            if (FXUtils.doCirclesIntersect(plugEndCircle, circle)) {
                bindToSocketCircle(circle);
                return;
            }
        }
        bindEndToLeftOfCordConnection();
        event.consume();
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
