package application;
 
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
 

public class Client {

	Socket socket;
	
	public Client(Socket socket) {
		this.socket = socket;
		receive();
	}

	//클라이언트로부터 메세지를 전달 받는 메소드입니다. 
	private void receive() { 
		Runnable thread = new Runnable() {

			@Override
			public void run() {

				try {
					InputStream in  =  socket.getInputStream();
					byte[] buffer = new byte[512];
					int length = in.read(buffer); 
					while(length == -1) throw new IOException();  // 클라이언트 입력시 에러 발생 exception 
					System.out.println("[메세지 수신 성공]"
							+ socket.getRemoteSocketAddress()
							+"  : "+ Thread.currentThread().getName());
					
					String message = new String(buffer, 0, length, "UTF-8");  
					for(Client client : Main.clients) {  // 다른 클라이언트들에 메세지 받은걸 보내준다 .
						client.send(message);
					}
					
				} catch(Exception e) {
					try {
						System.out.println("[메세지 수신 오류]"
								+ socket.getRemoteSocketAddress()
								+"  : "+ Thread.currentThread().getName());
					} catch (Exception e2) { 
						e.printStackTrace();
					}
				}
			}
			
		};
		Main.threadPool.submit(thread);
	}
	
	//클라이언트에게 메세지 전송하는 메소드
	public void send(String message) {
		
		Runnable thread = new Runnable() {

			@Override
			public void run() { 
				try {
					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					out.write(buffer); //buffer 내용을 서버에서 클라이언트로 전송해주기 
					out.flush();
				} catch (IOException e) { 
					try {
						System.out.println("[메세지 송신 오류]"
								+ socket.getRemoteSocketAddress()
								+ " :::: " + Thread.currentThread().getName());
						Main.clients.remove(Client.this);  // 해당 클라이언트를 오류 발생했으므로 삭제해준다. 
						socket.close();   // 오류가 생긴 클라이언트를 닫아 버림 
					} catch (Exception e2) { 
						e.printStackTrace();
					}
				}
			}
			
		};
		Main.threadPool.submit(thread);
		
	}
	
}
