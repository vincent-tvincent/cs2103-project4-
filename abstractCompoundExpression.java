import java.util.HashMap;
public abstract class abstractCompoundExpression extends abstractExpression implements CompoundExpression{
    private HashMap<String,Expression> subExpressions;
    public abstractCompoundExpression(abstractCompoundExpression parent, abstractExpression subExpression){
        super(parent);
        subExpressions.put(subExpression.ExpressionType, subExpression);
    }

    public abstractCompoundExpression(abstractCompoundExpression parent){
        super(parent);
    }

    private void addSubExpressionToList(abstractExpression expression){
        subExpressions.put(expression.ExpressionType,expression);
    }

    @Override
    public void addSubexpression(Expression subexpression) {
        addSubExpressionToList((abstractExpression) subexpression);
    }

    public boolean haveSubExpression(String expressionType){
        return subExpressions.containsKey(expressionType);
    }

    public Expression getSubExpression(String expressionType){
        return subExpressions.get(expressionType);
    }
}
