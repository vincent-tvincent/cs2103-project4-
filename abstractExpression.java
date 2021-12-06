public abstract class abstractExpression implements Expression{
    private abstractCompoundExpression ParentExpression;
    String ExpressionType;
    StringBuffer output;
    public int indent;
    public abstractExpression(CompoundExpression parent){
        ParentExpression = (abstractCompoundExpression) parent;
        indent = 1;
    }

    public abstractExpression(CompoundExpression parent,int indent){
        ParentExpression = (abstractCompoundExpression) parent;
        this.indent = indent;
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
