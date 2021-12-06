public class S extends abstractCompoundExpression{
    public S(){
        super();
        ExpressionType = "S";
        output = new StringBuffer("+");
        Expression.indent(output,indent);
    }

    public S(abstractCompoundExpression parent, int indent){
        super(parent,indent);
        ExpressionType = "S";
    }

    private boolean haveTwoParts(){
        return subExpressionNumber() == 2;
    }

    @Override
    public Expression deepCopy() {
        abstractCompoundExpression copy = new P((abstractCompoundExpression) this.getParent(),indent);
        if(haveTwoParts()){
            copy.addSubexpression(getSubExpression("S"));
        }
        copy.addSubexpression(getSubExpression("M"));
        return copy;
    }

    @Override
    public void flatten() {
        System.out.println(convertToString(indent));
    }

    @Override
    public String convertToString(int indentLevel) {
        output.append("\n");
        if(haveTwoParts()){
            abstractCompoundExpression S = (abstractCompoundExpression) getSubExpression("S");
            S.indent = indent + 1;
            output.append(S.convertToString(S.indent));
            output.append("\n");
        }
        abstractCompoundExpression M = (abstractCompoundExpression) getSubExpression("M");
        M.indent = indent + 1;
        output.append(M.convertToString(M.indent));
        return null;
    }
}
