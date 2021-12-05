public class L extends abstractExpression{
    String item;
    public L (abstractCompoundExpression parent, String item){
        super(parent);
        ExpressionType = "L";
        this.item = item;
    }

    @Override
    public Expression deepCopy() {
        return new L((abstractCompoundExpression) this.getParent(), item);
    }

    @Override
    public void flatten() {
        System.out.print(item);
    }

    @Override
    public String convertToString(int indentLevel) {

        return Expression.indent(,indentLevel) + item ;
    }

}
