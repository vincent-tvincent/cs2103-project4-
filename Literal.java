public class Literal implements Expression{
    private String _data;
    private CompoundExpression _parent;

    public Literal(String data) {
        _data = data;
    }

    @Override
    public CompoundExpression getParent() {
        return _parent;
    }

    @Override
    public void setParent(CompoundExpression parent) {
        _parent = parent;
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
        StringBuffer s = new StringBuffer();
        Expression.indent(s, indentLevel);
        s.append(_data);
        return s.toString();
    }
}
