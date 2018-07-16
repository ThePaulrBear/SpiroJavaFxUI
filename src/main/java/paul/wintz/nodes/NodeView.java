package paul.wintz.nodes;

import com.google.common.collect.ImmutableList;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import paul.wintz.doublesourcehierarchy.Node;
import paul.wintz.doublesourcehierarchy.Plug;
import paul.wintz.utils.logging.Lg;

import static com.google.common.base.Preconditions.checkNotNull;

class NodeView extends Rectangle {
    private static final String TAG = Lg.makeTAG(NodeView.class);

    private final NodesCanvasView pane;
    private final Circle socket;

    NodeView(NodesCanvasView pane, Node node, double x, double y, double width, double height) {
        super(x, y, width, height);
        this.pane = checkNotNull(pane);
        setFill(Color.CORAL);

        ImmutableList<Plug> plugs = node.getPlugs();
        for (int plugNdx = 0; plugNdx < plugs.size(); plugNdx++) {
            createPlug(plugNdx, plugs.size());
        }

        socket = createSocket();

        // move handle:
        Circle moveHandle = new Circle((double) 10, Color.GOLD);
        // bind to bottom center of Rectangle:
        moveHandle.centerXProperty().bind(xProperty().add(widthProperty().divide(2)));
        moveHandle.centerYProperty().bind(yProperty().add(heightProperty()));

        pane.add(moveHandle);

        FXUtils.setUpDragging(moveHandle, new RectangleDraggable()) ;

        setOnDragEntered(event -> Lg.d(TAG, "onDragEntered"));
        setOnDragDropped(event -> Lg.d(TAG, "onDragDropped"));
        setOnDragOver(event -> Lg.d(TAG, "onDragOver"));
        setOnMouseDragOver(event -> Lg.d(TAG, "onMouseDragOver"));
    }

    private Circle createSocket() {
        Circle socketCircle = new Circle(15, Color.BLUE);
        socketCircle.centerXProperty().bind(xProperty().add(widthProperty()));
        socketCircle.centerYProperty().bind(yProperty().add(heightProperty().divide(2)));

        socketCircle.setOnDragDetected(event -> {});

        this.pane.add(socketCircle);

        return socketCircle;
    }

    private void createPlug(int plugNdx, double plugCount) {
        final double verticalOffset = ((plugNdx + 0.5) * getHeight()) / plugCount;
        PositionProperty connectionPoint = new PositionProperty() {
            @Override
            public DoubleExpression x() {
                return xProperty();
            }

            @Override
            public DoubleExpression y() {
                return yProperty().add(verticalOffset);
            }
        };
        new PlugView(connectionPoint, pane);
    }

    public Circle getSocket() {
        return socket;
    }

    private class RectangleDraggable implements FXUtils.DraggableWrapper {
        @Override
        public double getX() {
            return NodeView.this.getX();
        }

        @Override
        public double getY() {
            return NodeView.this.getY();
        }

        @Override
        public void setX(double newX) {
            NodeView.this.setX(newX);
        }

        @Override
        public void setY(double newY) {
            NodeView.this.setY(newY);
        }
    }
}
