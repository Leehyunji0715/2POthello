package o;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Time;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class ClientBackground{
	
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private ClientGUI gui;
	private String msg;
	
	public void setGui(ClientGUI gui) {
		this.gui = gui;
	}
	public void connect() {
		try {
			socket = new Socket("127.0.0.1", 7777);
			System.out.println("서버 연결됨 ");
			
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			out.writeUTF("안녕하세요 나는 클라이언트 입니다..");
			System.out.println("클라이언트 메시지 전송 완료!");
			
			while(in!=null) {
				msg = in.readUTF();
				gui.appendMsg(msg);
			}
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ClientBackground clientbackground = new ClientBackground();
		clientbackground.connect();
	}
	public void sendMessage(String msg2) {
		try {
			out.writeUTF("클라이언트: "+msg2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
