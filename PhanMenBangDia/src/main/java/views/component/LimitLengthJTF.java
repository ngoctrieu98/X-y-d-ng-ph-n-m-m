package views.component;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitLengthJTF extends PlainDocument {

	private int limit;
	private static final long serialVersionUID = -611533337610164597L;

	public LimitLengthJTF(int limit) {
		this.limit = limit;
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if (str == null) {
			return;
		}
		if ((getLength() + str.length()) <= limit)
			super.insertString(offs, str, a);
	}
}
