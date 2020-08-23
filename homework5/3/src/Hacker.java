import java.io.PrintStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;


class Hacker implements Runnable {
	@Override
	public void run() {
		// TODO do as the problem description
        try {
            while (true) {
                final Socket socket = new Socket(InetAddress.getLoopbackAddress(), 11111);
                PrintStream os = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
                synchronized (Cloud.class) {
                    int hack = Cloud.getKey() ^ Cloud.getData();
                    os.println(hack);
                    os.flush();
                    os.close();
                    socket.close();
                    Cloud.class.wait();
                }
            }
        } catch (Exception e) {
            //nop
        }
	}
}
