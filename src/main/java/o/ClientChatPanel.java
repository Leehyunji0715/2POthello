package o;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientChatPanel extends JPanel implements ActionListener{
	private JTextField jtf = new JTextField(25);
	private JTextArea jta = new JTextArea(40,25);
	
	ClientBackground client = new ClientBackground();
	
	public ClientChatPanel (ClientBackground client) {
		this.client = client;
		setSize(270,410);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String msg = jtf.getText()+"\n";
		jta.append("클라이언트: "+msg);
		System.out.print(msg);
		client.sendMessage(msg);
		jtf.setText("");
		
	}
	public void appendMsg(String msg) {
		jta.append(msg);
		System.out.println("날라온 메시지 : "+msg);
	}

}
