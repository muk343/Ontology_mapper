/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.IOException;
import java.io.Writer;
import javax.swing.JTextArea;

/**
 *
 * @author N Hari Prasad
 */
public final class TextAreaWriter extends Writer {

	private final JTextArea textArea;

	public TextAreaWriter(final JTextArea textArea) {
		this.textArea = textArea;
	}

    @Override
    public void flush(){ }
    
    @Override
    public void close(){ }

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		textArea.append(new String(cbuf, off, len));
	}
}
