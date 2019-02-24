package paul.wintz.nodes;

import com.google.common.collect.ImmutableList;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import paul.wintz.doublesourcehierarchy.Node;
import paul.wintz.doublesourcehierarchy.Plug;
import paul.wintz.utils.logging.Lg;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

class NodeView extends Pane {
    private static final String TAG = Lg.makeTAG(NodeView.class);

    private final NodesCanvasView pane;
    private final SocketCircle socket;
    private Node node;
    private final Text text = new Text();

    class SocketCircle extends Circle {
        SocketCircle(int radius, Color fill) {
            super(radius, fill);
        }

        void onConnect(Plug plug){
            plug.plugInto(node);
            Lg.v(TAG, "Connected " + plug + " to " + node + ", value is now: " + node.getValue());
            text.setText("Value: " + node.getValue());
        }

        Point2D getGlobalPosition() {
            return localToScene(getTranslateX(), getTranslateY());
        }
    }

    NodeView(NodesCanvasView pane, Node node, double x, double y, double width, double height) {
        this.node = node;
        setTranslateX(x);
        setTranslateY(y);
        setWidth(width);
        setMaxWidth(width);
        setHeight(height);
        setMaxHeight(height);
        this.pane = checkNotNull(pane);

        Rectangle boundingBox = new Rectangle();
        boundingBox.setStroke(Color.RED);
        boundingBox.setStrokeWidth(4);
        boundingBox.setStrokeType(StrokeType.CENTERED);
        boundingBox.widthProperty().bind(this.widthProperty());
        boundingBox.heightProperty().bind(this.heightProperty());
        getChildren().add(boundingBox);

        Rectangle background = new Rectangle(0, 0, width, height);
        background.setFill(Color.DARKSLATEGRAY);
        getChildren().add(background);

        text.setText("Value: " + node.getValue());
        getChildren().add(text);

        List<Plug> plugs = node.getPlugs();
        for (int plugNdx = 0; plugNdx < plugs.size(); plugNdx++) {
            createPlug(plugNdx, plugs);
        }

        socket = createSocket();

        FXUtils.setUpDragging(background, new RectangleDraggable()) ;
    }

    private SocketCircle createSocket() {
        SocketCircle socketCircle = new SocketCircle(15, Color.BLUE);
        socketCircle.setCenterX(getWidth());
        socketCircle.setCenterY(getHeight() / 2.0);

        this.getChildren().add(socketCircle);

        return socketCircle;
    }

    private void createPlug(int plugNdx, List<Plug> plugs) {
        final double verticalOffset = ((plugNdx + 0.5) * getHeight()) / plugs.size();
        Point2D connectionPoint = new Point2D(0, verticalOffset);
        new PlugView(connectionPoint, this, pane.getDropReceivers(), plugs.get(plugNdx));
    }

    public NodeView.SocketCircle getSocket() {
        return socket;
    }

    private class RectangleDraggable implements FXUtils.DraggableWrapper {
        @Override
        public double getX() {
            return NodeView.this.getTranslateX();
        }

        @Override
        public double getY() {
            return NodeView.this.getTranslateY();
        }

        @Override
        public void setX(double newX) {
            NodeView.this.setTranslateX(newX);
        }

        @Override
        public void setY(double newY) {
            NodeView.this.setTranslateY(newY);
        }
    }

}
