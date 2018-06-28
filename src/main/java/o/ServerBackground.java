package o;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//1. 메시지를 주고 받고 싶다.
//2. GUI를 만들고 싶다
//3. 연동 
public class ServerBackground {
	private ServerSocket serverSocket;
	private Socket socket;//귀역할 
	private DataInputStream in;
	private DataOutputStream out;
	private ServerGUI gui;
	String msg;
	

	public void setGui(ServerGUI gui) {
		this.gui = gui;
	}
	public void setting() {
		
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("대기중....");
			socket = serverSocket.accept();//client가 들어올 떄까지 대기중이당. 
			System.out.println(socket.getInetAddress()+"에서 접속했습니다. ");
			
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			msg = in.readUTF();
			System.out.println("클라이언트로부터 메시지 : "+msg);
			gui.appendMsg(msg);
			
			while(in!=null) {
				msg = in.readUTF();
				gui.appendMsg(msg);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		ServerBackground sb = new ServerBackground();
		sb.setting();
	}
	public void sendMessage(String msg) {
		try {
			out.writeUTF("서버: "+msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}


/*
public class ServerBackground implements ActionListener{
	static ServerSocket server;
	static Socket conn;
	JPanel panel;
	JTextField NewMsg;
	JTextArea ChatHistory;
	JButton Send;
	DataInputStream dis;
	DataOutputStream dos;
	
	public ServerBackground()throws UnknownHostException, IOException{
		Board board = new Board(); 
		panel = new JPanel();
		NewMsg = new JTextField();
		ChatHistory = new JTextArea();
		Send = new JButton("Send");
		
		panel.setLayout(null);
		board.sb.add(panel);
		ChatHistory.setBounds(50, 300, 500, 380);
		panel.add(ChatHistory);
		NewMsg.setBounds(50, 650, 340, 30);
		panel.add(NewMsg);
		Send.setBounds(600, 670, 95, 30);
		panel.add(Send);
		Send.addActionListener((ActionListener) board.sb);
		server = new ServerSocket(2000, 1, InetAddress.getLocalHost());
		ChatHistory.setText("Waiting for Client");
		conn = server.accept();
		ChatHistory.setText(ChatHistory.getText() + '\n' + "Client Found");
		
		while (true) {
			try {
				DataInputStream dis = new DataInputStream(conn.getInputStream());
				String string = dis.readUTF();
				ChatHistory.setText(ChatHistory.getText() + '\n' + "Client:"
						+ string);
			} catch (Exception e1) {
				ChatHistory.setText(ChatHistory.getText() + '\n'
						+ "Message sending fail:Network Error");
				try {
					Thread.sleep(3000);
					System.exit(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((e.getSource() == Send) && (NewMsg.getText() != "")) {
			ChatHistory.setText(ChatHistory.getText() + 'n' + "ME:"
					+ NewMsg.getText());
			try {
				DataOutputStream dos = new DataOutputStream(
						conn.getOutputStream());
				dos.writeUTF(NewMsg.getText());
			} catch (Exception e1) {
				try {
					Thread.sleep(3000);
					System.exit(0);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
			NewMsg.setText("");
		}
	}
		
	}
*/








 
