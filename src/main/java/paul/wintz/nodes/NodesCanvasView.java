package paul.wintz.nodes;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import org.w3c.dom.css.Rect;
import paul.wintz.doublesourcehierarchy.BinaryOperatorNode;
import paul.wintz.doublesourcehierarchy.Leaf;
import paul.wintz.doublesourcehierarchy.Node;
import paul.wintz.doublesourcehierarchy.presenter.INodesPresenter;
import paul.wintz.doublesourcehierarchy.presenter.NodesPresenter;
import paul.wintz.sourcefactories.DoubleConsumer;
import paul.wintz.utils.logging.Lg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NodesCanvasView implements NodesPresenter.View {
    private static final String TAG = Lg.makeTAG(NodesCanvasView.class);
    private List<Node> nodeList = new ArrayList<>();

    @FXML private Pane pane;
    @FXML private Button addNode;

    private Set<Circle> dropReceivers = new HashSet<>();

    @FXML public void initialize() {
        Rectangle backRectangle = new Rectangle();
        backRectangle.setFill(Color.BEIGE);
        backRectangle.setStrokeType(StrokeType.INSIDE);
        backRectangle.setStroke(Color.GOLD);
        backRectangle.setStrokeWidth(12);
        backRectangle.widthProperty().bind(pane.widthProperty());
        backRectangle.heightProperty().bind(pane.heightProperty());
        pane.getChildren().add(backRectangle);
        backRectangle.toBack();
    }

    private void createTestOutPlug(DoubleConsumer c) {

        new PlugView(new Point2D(pane.getWidth(), pane.getHeight()/2.0), pane, this.getDropReceivers());

    }

    public void add(javafx.scene.Node node) {
        pane.getChildren().add(node);
    }

    public Set<Circle> getDropReceivers() {
        return dropReceivers;
    }

    @Override
    public void onNodeRemoved(Node node) {
        nodeList.remove(node);
        printNodes("onNodeRemoved");
    }

    private void printNodes(String action) {
        Lg.d(TAG, "%s - Nodes: %s", action,
                nodeList.size() > 3? nodeList.size() + " nodes" : nodeList);
    }

    @Override
    public void onNodeAdded(Node node) {
        nodeList.add(node);
        printNodes("onNodeAdded");
    }

    @Override
    public void onClear() {
        nodeList.clear();
        printNodes("onNodeAdded");
    }

    @Override
    public void setPresenter(INodesPresenter presenter) {
        for(DoubleConsumer consumer : presenter.getConsumers()) {
            createTestOutPlug(consumer);
        }
    }

    @FXML public void createLeaf(MouseEvent mouseEvent) {
        createNodeView(Leaf.ONE);
    }

    @FXML public void createAdderNode() {
        createNodeView(new BinaryOperatorNode.AdderNode());
    }

    private void createNodeView(Node unitLeaf) {
        final NodeView nodeView = new NodeView(this, unitLeaf, 50 + nodeList.size() * 10, 50 + nodeList.size() * 10, 100, 100);
        pane.getChildren().add(nodeView);
        dropReceivers.add(nodeView.getSocket());
        onNodeAdded(unitLeaf);
    }

}
