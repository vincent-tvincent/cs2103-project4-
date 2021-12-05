public abstract class abstractExpression implements Expression{
    private abstractCompoundExpression ParentExpression;
    public String ExpressionType;
    public abstractExpression(CompoundExpression parent){
        ParentExpression = (abstractCompoundExpression) parent;
    }

    public CompoundExpression getParent(){
        return ParentExpression;
    }

    public void setParent(CompoundExpression parent){
        ParentExpression = (abstractCompoundExpression) parent;
    }

    public boolean haveParentExpression(String expressionType){
        return ParentExpression.ExpressionType.equals(expressionType);
    }
}
