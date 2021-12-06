import java.util.ArrayList;
import java.util.List;

public class Operator implements CompoundExpression {
    private String _identifier;
    private Operator _parent;
    private List<Expression> _subexpresions;

    public Operator (String identifier) {
        _identifier = identifier;
        _subexpresions = new ArrayList<>();
    }

    public String getIdentifier() {
        return _identifier;
    }

    @Override
    public void addSubexpression(Expression subexpression) {
        _subexpresions.add(subexpression);
    }

    public void removeSubexpression(Expression subexpression) {
        _subexpresions.remove(subexpression);
    }

    @Override
    public CompoundExpression getParent() {
        return _parent;
    }

    @Override
    public void setParent(CompoundExpression parent) {
        _parent = (Operator) parent;
    }

    @Override
    public Expression deepCopy() {
        return null;
    }

    @Override
    public void flatten() {
        Expression[] subArray = new Expression[_subexpresions.size()];
        _subexpresions.toArray(subArray);

        for (int i = 0; i < subArray.length; i++) {
            subArray[i].flatten();
        }
        if(_parent != null && _parent.getIdentifier().equals(getIdentifier())) {
            for (int i = 0; i < subArray.length; i++) {
                _parent.addSubexpression(subArray[i]);
                subArray[i].setParent(_parent);

                _parent.removeSubexpression(this);
            }
        }
    }

    @Override
    public String convertToString(int indentLevel) {
        StringBuffer s = new StringBuffer();

        Expression.indent(s, indentLevel);
        s.append(_identifier);

        indentLevel += 1;
        for(Expression e : _subexpresions) {
            s.append("\n");
            s.append(e.convertToString(indentLevel));
        }
        if(_parent == null) {
            s.append("\n");
        }
        return s.toString();
    }
}
