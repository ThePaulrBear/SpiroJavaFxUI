package paul.wintz.nodes;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.StringConverter;
import paul.wintz.doublesourcehierarchy.*;
import paul.wintz.doublesourcehierarchy.Node;
import paul.wintz.doublesourcehierarchy.Plug;
import paul.wintz.doublesourcehierarchy.presenter.NodesPresenter;
import paul.wintz.javafx.widgets.ListSpinner;
import paul.wintz.sourcefactories.DoubleConsumer;
import paul.wintz.uioptiontypes.values.ListOption;
import paul.wintz.utils.logging.Lg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class NodesCanvasView implements NodesPresenter.View {
    private static final String TAG = Lg.makeTAG(NodesCanvasView.class);
    private List<Node> nodes = new ArrayList<>();
    private List<DoubleConsumer> consumers = new ArrayList<>();

    @FXML private Pane pane;
    @FXML private Button addNode;
    @FXML private ListSpinner<Class<? extends Node>> nodeTypes;

    private Set<NodeView.SocketCircle> dropReceivers = new HashSet<>();
    private NodesPresenter presenter;

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

    public void add(javafx.scene.Node node) {
        pane.getChildren().add(node);
    }

    public Set<NodeView.SocketCircle> getDropReceivers() {
        return dropReceivers;
    }

    @Override
    public void onConsumerAdded(DoubleConsumer doubleConsumer) {
        new PlugView(new Point2D(pane.getWidth(), pane.getHeight()/2.0), pane, this.getDropReceivers(), new Plug());
    }

    @Override
    public void onConsumerRemoved(DoubleConsumer doubleConsumer) {
    }

    @Override
    public void onNodeRemoved(Node node) {
        printNodes("onNodeRemoved");
        nodes.remove(node);
    }

    private void printNodes(String action) {
        Lg.d(TAG, "%s - Nodes: %s", action,
                nodes.size() > 3? nodes.size() + " nodes" : nodes);
    }

    @Override
    public void onNodeAdded(Node node) {
        printNodes("onNodeAdded: " + node);
        nodes.add(node);
    }

    @Override
    public void onClearNodes() {
        printNodes("onClearNodes");
        nodes.clear();
    }

    @Override
    public void setPresenter(NodesPresenter presenter) {
        this.presenter = checkNotNull(presenter);
        nodes.addAll(presenter.getNodes());
        consumers.addAll(presenter.getConsumers());
        for(Node node : presenter.getNodes()){
            createNodeView(node);
        }

        ListOption<Class<? extends Node>> listOption = ListOption.<Class<? extends Node>>builder()
                .addAll(presenter.getNodeTypes())
                .addViewValueChangeCallback(nodeType -> createNodeView(presenter.addNode(nodeType)))
                .build();
        nodeTypes.setOption(listOption);
        listOption.emitViewValueChanged(IncrementerLeafNode.class);
        Lg.v(TAG, "Nodes: " + nodes);
        Lg.v(TAG, "Consumers: " + consumers);
        Lg.v(TAG, "Nodes factory: " + presenter.getNodeTypes());

        // Setup node factory list
        nodeTypes.setStringConverter(new StringConverter<Class<? extends Node>>() {

            @Override
            public String toString(Class<? extends Node> object) {
                return object.getSimpleName();
            }

            @Override
            public Class<? extends Node> fromString(String string) {
                throw new UnsupportedOperationException();
            }

        });
    }

    @FXML public void createLeaf(MouseEvent mouseEvent) {
        createNodeView(AbstractLeafNode.ONE);
    }

    @FXML public void createAdderNode() {
        createNodeView(new BinaryOperatorNode.AdderNode());
    }

    private void createNodeView(Node unitLeaf) {
        final NodeView nodeView = new NodeView(this, unitLeaf, 50 + nodes.size() * 10, 50 + nodes.size() * 10, 100, 100);
        pane.getChildren().add(nodeView);
        dropReceivers.add(nodeView.getSocket());
        onNodeAdded(unitLeaf);
    }

}
