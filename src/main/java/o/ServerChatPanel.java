package o;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerChatPanel extends JPanel implements ActionListener{
	private JTextField jtf = new JTextField(25);
	private JTextArea jta = new JTextArea(40,25);
	ServerBackground server;
	
	public ServerChatPanel(ServerBackground server) {
		this.server = server;
		setSize(150,410);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String msg = jtf.getText()+"\n";
		jta.append("서버: "+msg);
		System.out.println(msg);
		
		server.sendMessage(msg);
		jtf.setText("");
	}
	public void appendMsg(String msg) {
		jta.append(msg);
		System.out.println("날라온 메시지 : "+msg);
	}
}
