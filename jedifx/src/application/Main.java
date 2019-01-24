package application;
	
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
	

	public static ExecutorService  threadPool;
	public static Vector<Client> clients = new Vector<Client>();
	
	ServerSocket  serverSocket;
	
	//������ �������� Ŭ���̾�Ʈ ������ ��ٸ��� �޼ҵ�
	public void startServer(String ip , int port) {
		
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(ip, port));
		} catch (IOException e) { 
			e.printStackTrace();
			if(!serverSocket.isClosed()) {
				stopServer();
			}
			return;
		}
		
		//Ŭ���̾�Ʈ�� ������ ������ ��� ��ٸ��� �������Դϴ�.
		Runnable thread = new Runnable() {

			@Override
			public void run() { 
				while(true) {
					try {
						Socket socket = serverSocket.accept();
						clients.add(new Client(socket));
						System.out.println("[Ŭ���̾�Ʈ ����] "
								+ socket.getRemoteSocketAddress()
								+ "  :::  " + Thread.currentThread().getName());
					} catch (IOException e) { 
						e.printStackTrace();
						if(!serverSocket.isClosed()) {
							stopServer();
						}
						break;  // ���� ���� 
					}
					
				}
				
			} 
			
		};  // end Runnable
		threadPool = Executors.newCachedThreadPool();
		threadPool.submit(thread);
		
		
	}
	
	//������ �۵��� ������Ű�� �޼ҵ� 
	public void stopServer() {
		try {
			//���� �۵����� ��� Ŭ���̾�Ʈ�� �ݴ´�. 
			Iterator<Client> iterator = clients.iterator();
			while(iterator.hasNext()) {
				Client client = iterator.next();
				client.socket.close();
				iterator.remove(); 
			}
			
			//���� ���� �ݱ� 
			if(serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			
			//������ Ǯ ����
			if(threadPool != null && !threadPool.isShutdown()) {
				threadPool.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//UI �� �����ϰ�, ���������� ���α׷��� ���� ��Ű�� �޼ҵ� 
	@Override
	public void start(Stage primaryStage) {
		
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));  // ������ �ش�
		
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("�������", 15));
		
		root.setCenter(textArea);
		
		Button toggleButton = new Button("�����ϱ�");
		toggleButton.setMaxWidth(Double.MAX_VALUE);
		
		BorderPane.setMargin(toggleButton, new Insets(1,0,0,0));
		root.setBottom(toggleButton);
		
		String IP = "127.0.0.1";
		int port = 9876;
		
		toggleButton.setOnAction(event -> {
			if(toggleButton.getText().equals("�����ϱ�")) {
				startServer(IP, port);
				Platform.runLater(() ->  {
					String message = String.format("[���� ����]\n" , IP, port);
					textArea.appendText(message);
					toggleButton.setText("�����ϱ�");
				});
			} else {
				stopServer();
				Platform.runLater(() -> {
					String message = String.format("[���� ����]\n" , IP, port);
					textArea.appendText(message);
					toggleButton.setText("�����ϱ�");
				});
			}
		});
		
		Scene   scene = new Scene(root, 1200, 700);
		primaryStage.setTitle("[ä�� ����]");
		primaryStage.setOnCloseRequest(event -> stopServer());   // ��ư���� �����ϸ� ������ �����Ѵ�. 
		primaryStage.setScene(scene);
		primaryStage.show();
		
		/*
		 * try { BorderPane root = new BorderPane(); Scene scene = new
		 * Scene(root,1400,400);
		 * scene.getStylesheets().add(getClass().getResource("application.css").
		 * toExternalForm()); primaryStage.setScene(scene); primaryStage.show(); }
		 * catch(Exception e) { e.printStackTrace(); }
		 */
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
