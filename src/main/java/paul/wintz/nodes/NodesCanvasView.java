package paul.wintz.nodes;

import javafx.beans.binding.DoubleExpression;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import paul.wintz.doublesourcehierarchy.BinaryOperatorNode;
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

    public void initialize() {

//        final Circle circle = new Circle(10, Color.RED);
//        pane.getChildren().add(circle);
//        pane.setOnMouseMoved(event -> {
//            circle.setCenterX(event.getX());
//            circle.setCenterY(event.getY());
//        });


        pane.setOnDragDetected(event -> Lg.d(TAG, "onDragDetected"));
        pane.setOnDragEntered(event -> Lg.d(TAG, "onDragEntered"));
        pane.setOnDragDropped(event -> Lg.d(TAG, "onDragDropped"));
        pane.setOnDragOver(event -> Lg.d(TAG, "onDragOver"));
        pane.setOnMouseDragOver(event -> Lg.d(TAG, "onMouseDragOver"));

        Lg.d(TAG, "initialize()");
    }

    private void createTestOutPlug(DoubleConsumer c) {

        PositionProperty connectionPoint = new PositionProperty() {
            @Override
            public DoubleExpression x() {
                return pane.widthProperty();
            }

            @Override
            public DoubleExpression y() {
                return pane.heightProperty().divide(2);
            }
        };

        new PlugView(connectionPoint, this);


    }

    public void createTestNode() {
        BinaryOperatorNode.AdderNode node = new BinaryOperatorNode.AdderNode();
        final NodeView nodeBox = new NodeView(this, node, 50 + nodeList.size() * 10, 50 + nodeList.size() * 10, 100, 100);
        pane.getChildren().add(nodeBox);
        dropReceivers.add(nodeBox.getSocket());
        onNodeAdded(node);
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
        Lg.d(TAG, "%s - Nodes: %s",
                action,
                nodeList.size() > 3?
                        nodeList.size() + " nodes" : nodeList);
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
}
