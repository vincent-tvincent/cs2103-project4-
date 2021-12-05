public class Literal implements Expression{
    private String _data;
    public Literal(String data) {
        _data = data;
    }

    @Override
    public CompoundExpression getParent() {
        return null;
    }

    @Override
    public void setParent(CompoundExpression parent) {

    }

    @Override
    public Expression deepCopy() {
        return null;
    }

    @Override
    public void flatten() {

    }

    @Override
    public String convertToString(int indentLevel) {
        return null;
    }
}
