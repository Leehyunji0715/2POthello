package o;

import java.io.IOException;
import java.net.UnknownHostException;

public class Controller {

	public static void main(String[] args){
		
		ServerBackground sb = new ServerBackground();
		sb.setting();
		ClientBackground clientbackground = new ClientBackground();
		clientbackground.connect();
		//Client call
		//ClientBackground cb = new ClientBackground();
	}
}

