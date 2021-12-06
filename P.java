public class P extends abstractCompoundExpression{
    public P(){
        super();
        ExpressionType = "P";
        output = new StringBuffer("()");
        Expression.indent(output,indent);
    }

    public P(abstractCompoundExpression parent, int indent){
        super(parent,indent);
        ExpressionType = "P";
    }

    private String SorL(){
        if(haveSubExpression("L")){
            return "L";
        } else if(haveSubExpression("S")){
            return "S";
        }else{
            return null;
        }
    }

    @Override
    public Expression deepCopy() {
        abstractCompoundExpression copy = new P((abstractCompoundExpression) this.getParent(),indent);
        copy.addSubexpression(getSubExpression(SorL()));
        return copy;
    }

    @Override
    public void flatten() {
        System.out.println(convertToString(indent));
    }

    @Override
    public String convertToString(int indentLevel) {
        abstractExpression se = (abstractExpression) getSubExpression(SorL());
        se.indent = indent + 1;
        output.append("\n" + se.convertToString(indent + 1));
        return output.toString();
    }


}
