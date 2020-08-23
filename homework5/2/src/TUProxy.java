import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

class TUProxy extends URLStreamHandler {
	TUProxy(final String protocal) {}
	@Override
	protected URLConnection openConnection(final URL url) throws IOException {
		return new URLConnectionProxy(url);
	}

	private static class URLConnectionProxy extends URLConnection {
		@SuppressWarnings("serial")
		static Map<URL, File> NAT = new HashMap<URL, File>() {
			{
				try {
					put(new URL("http://www.sample.com/"), new File("proxy/sample.txt"));
					put(new URL("proxy://calculus/"), new File("proxy/calculus.txt"));
				} catch (final Exception e) {
				}
			}
		};

		protected URLConnectionProxy(final URL url) {
			super(url);
			connected=false;
		}

		private InputStream is = null;

		@Override
		public void connect() throws IOException {
			final File f = NAT.get(getURL());
			if (f == null)
				throw new UnknownHostException("Unknown host: " + getURL());
			try {
				is = new BufferedInputStream(new FileInputStream(f));
			} catch (final FileNotFoundException e) {
				throw new ConnectException("Cannot connect to URL: " + getURL());
			}
			connected = (is != null);
		}

		@Override
		public InputStream getInputStream() throws IOException {
			if(!connected)
				connect();
			return is;
		}
	}
}
