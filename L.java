public class L extends abstractExpression{
    String item;
    public L(abstractCompoundExpression parent, String item){
        super(parent);
        ExpressionType = "L";
        this.item = item;
        output = new StringBuffer(item);
    }
    public L(abstractCompoundExpression parent, String item,int indent){
        super(parent,indent);
        ExpressionType = "L";
        this.item = item;
        output = new StringBuffer(item);
    }

    @Override
    public Expression deepCopy() {
        return new L((abstractCompoundExpression) this.getParent(), item);
    }

    @Override
    public void flatten() {
        System.out.println(convertToString(indent));
    }

    @Override
    public String convertToString(int indentLevel) {
        Expression.indent(output,indentLevel);
        return output.toString();
    }

}
