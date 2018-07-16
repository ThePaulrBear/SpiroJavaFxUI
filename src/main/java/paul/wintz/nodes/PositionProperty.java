package paul.wintz.nodes;

import javafx.beans.binding.DoubleExpression;

interface PositionProperty {
    DoubleExpression x();
    DoubleExpression y();
}
