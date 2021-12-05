public class P extends abstractCompoundExpression{
    public P(abstractCompoundExpression parent){
        super(parent);
        ExpressionType = "P";
    }

    @Override
    public Expression deepCopy() {
        abstractCompoundExpression copy = new P((abstractCompoundExpression) this.getParent());
        if(copy.haveSubExpression("L")){
            copy.addSubexpression(this.getSubExpression("L"));
        } else if(copy.haveSubExpression("S")){
            copy.addSubexpression(this.getSubExpression("S"));
        }
        return copy;
    }


    @Override
    public void flatten() {
        String internalExpression;
        if(haveSubExpression("L")){
            internalExpression = "L";
        }else{
            internalExpression = "S";
        }
        System.out.print("("+ getSubExpression(internalExpression).convertToString() + ")");
    }

    @Override
    public String convertToString(int indentLevel) {
        return null;
    }


}
