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
	
	//서버를 구동시켜 클라이언트 연결을 기다리는 메소드
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
		
		//클라이언트가 접속할 때까지 계속 기다리는 쓰레드입니다.
		Runnable thread = new Runnable() {

			@Override
			public void run() { 
				while(true) {
					try {
						Socket socket = serverSocket.accept();
						clients.add(new Client(socket));
						System.out.println("[클라이언트 접속] "
								+ socket.getRemoteSocketAddress()
								+ "  :::  " + Thread.currentThread().getName());
					} catch (IOException e) { 
						e.printStackTrace();
						if(!serverSocket.isClosed()) {
							stopServer();
						}
						break;  // 빠져 나옴 
					}
					
				}
				
			} 
			
		};  // end Runnable
		threadPool = Executors.newCachedThreadPool();
		threadPool.submit(thread);
		
		
	}
	
	//서버의 작동을 중지시키는 메소드 
	public void stopServer() {
		try {
			//현재 작동중인 모든 클라이언트를 닫는다. 
			Iterator<Client> iterator = clients.iterator();
			while(iterator.hasNext()) {
				Client client = iterator.next();
				client.socket.close();
				iterator.remove(); 
			}
			
			//서버 소켓 닫기 
			if(serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			
			//쓰레드 풀 종료
			if(threadPool != null && !threadPool.isShutdown()) {
				threadPool.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//UI 를 생성하고, 실질적으로 프로그램을 동작 시키는 메소드 
	@Override
	public void start(Stage primaryStage) {
		
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));  // 패잉을 준다
		
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("나눔고딕", 15));
		
		root.setCenter(textArea);
		
		Button toggleButton = new Button("시작하기");
		toggleButton.setMaxWidth(Double.MAX_VALUE);
		
		BorderPane.setMargin(toggleButton, new Insets(1,0,0,0));
		root.setBottom(toggleButton);
		
		String IP = "127.0.0.1";
		int port = 9876;
		
		toggleButton.setOnAction(event -> {
			if(toggleButton.getText().equals("시작하기")) {
				startServer(IP, port);
				Platform.runLater(() ->  {
					String message = String.format("[서버 시작]\n" , IP, port);
					textArea.appendText(message);
					toggleButton.setText("종료하기");
				});
			} else {
				stopServer();
				Platform.runLater(() -> {
					String message = String.format("[서버 종료]\n" , IP, port);
					textArea.appendText(message);
					toggleButton.setText("시작하기");
				});
			}
		});
		
		Scene   scene = new Scene(root, 1200, 700);
		primaryStage.setTitle("[채팅 서버]");
		primaryStage.setOnCloseRequest(event -> stopServer());   // 버튼으로 종료하면 서버도 종료한다. 
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
