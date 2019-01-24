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

	//Ŭ���̾�Ʈ�κ��� �޼����� ���� �޴� �޼ҵ��Դϴ�. 
	private void receive() { 
		Runnable thread = new Runnable() {

			@Override
			public void run() {

				try {
					InputStream in  =  socket.getInputStream();
					byte[] buffer = new byte[512];
					int length = in.read(buffer); 
					while(length == -1) throw new IOException();  // Ŭ���̾�Ʈ �Է½� ���� �߻� exception 
					System.out.println("[�޼��� ���� ����]"
							+ socket.getRemoteSocketAddress()
							+"  : "+ Thread.currentThread().getName());
					
					String message = new String(buffer, 0, length, "UTF-8");  
					for(Client client : Main.clients) {  // �ٸ� Ŭ���̾�Ʈ�鿡 �޼��� ������ �����ش� .
						client.send(message);
					}
					
				} catch(Exception e) {
					try {
						System.out.println("[�޼��� ���� ����]"
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
	
	//Ŭ���̾�Ʈ���� �޼��� �����ϴ� �޼ҵ�
	public void send(String message) {
		
		Runnable thread = new Runnable() {

			@Override
			public void run() { 
				try {
					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					out.write(buffer); //buffer ������ �������� Ŭ���̾�Ʈ�� �������ֱ� 
					out.flush();
				} catch (IOException e) { 
					try {
						System.out.println("[�޼��� �۽� ����]"
								+ socket.getRemoteSocketAddress()
								+ " :::: " + Thread.currentThread().getName());
						Main.clients.remove(Client.this);  // �ش� Ŭ���̾�Ʈ�� ���� �߻������Ƿ� �������ش�. 
						socket.close();   // ������ ���� Ŭ���̾�Ʈ�� �ݾ� ���� 
					} catch (Exception e2) { 
						e.printStackTrace();
					}
				}
			}
			
		};
		Main.threadPool.submit(thread);
		
	}
	
}
