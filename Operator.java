public class Operator implements CompoundExpression {
    private char _identifier;
    public Operator (char identifier) {
        _identifier = identifier;
    }

    public char getIdentifier() {
        return _identifier;
    }

    @Override
    public void addSubexpression(Expression subexpression) {

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
