package paul.wintz.nodes;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import paul.wintz.utils.logging.Lg;

class FXUtils {
    private static final String TAG = Lg.makeTAG(FXUtils.class);

    static void setUpDragging(Node handle, DraggableWrapper toMove){
        setUpDragging(handle, toMove, (event) -> {});
    }

    static void setUpDragging(Node handle, DraggableWrapper toMove, EventHandler<MouseEvent> onDragEndedListener) {

        ValueWrapper<Point2D> mouseLocation = new ValueWrapper<>();

        handle.setOnDragDetected(event -> {
            handle.getParent().setCursor(Cursor.CLOSED_HAND);
            mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
            Lg.v(TAG, "DragDetected for " + handle);
            event.consume();
        });

        handle.setOnMouseDragged(event -> {
            if (mouseLocation.value == null) return;

            double deltaX = event.getSceneX() - mouseLocation.value.getX();
            toMove.setX(toMove.getX() + deltaX);

            double deltaY = event.getSceneY() - mouseLocation.value.getY();
            toMove.setY(toMove.getY() + deltaY);

            mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
            event.consume();
        });

        handle.setOnMouseReleased(event -> {
            handle.getParent().setCursor(Cursor.DEFAULT);
            mouseLocation.value = null;
            onDragEndedListener.handle(event);
            Lg.v(TAG, "MouseReleased for " + handle);
            event.consume();
        });
    }

    public interface DraggableWrapper {
        double getX();
        double getY();
        void setX(double newX);
        void setY(double newY);
    }

    static class ValueWrapper<T> { T value ; }

    static boolean doCirclesIntersect(Circle first, Circle second) {
        Lg.v(TAG, "doCirclesIntersect(%s, %s)", first, second);

        Point2D firstPoint = first.localToScene(first.getCenterX(), first.getCenterY());
        Point2D secondPoint = second.localToScene(second.getCenterX(), second.getCenterY());

        double xDelta = firstPoint.getX() - secondPoint.getX();
        double yDelta = firstPoint.getY() - secondPoint.getY();
        return Math.hypot(xDelta, yDelta) <= first.getRadius() + second.getRadius();
    }

    private FXUtils() {}

}
