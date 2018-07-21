package paul.wintz.nodes;

import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;

class FlowCurve extends CubicCurve {

    FlowCurve(double startX, double startY, ObservableValue<Number> endX, ObservableValue<Number> endY) {
        setStroke(Color.BLACK);
        setStrokeWidth(2);
        setFill(null);

        setStart(startX, startY);
        bindEnd(endX, endY);

        double other = 0.5;
        controlX1Property().bind(startXProperty().add(endXProperty()).multiply(other));
        controlY1Property().bind(startYProperty());
        controlX2Property().bind(startXProperty().add(endXProperty()).multiply(1 - other));
        controlY2Property().bind(endYProperty());
    }

    public void bindEnd(ObservableValue<Number> endX, ObservableValue<Number> endY) {
        endXProperty().bind(endX);
        endYProperty().bind(endY);
    }

    public void setStart(double startX, double startY) {
        setStartX(startX);
        setStartY(startY);
    }

}
