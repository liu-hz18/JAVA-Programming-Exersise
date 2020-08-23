abstract class BaseSingleton {
	private static int counter = 0;

	protected BaseSingleton() {
		++counter;
	}
	static int whatsCounter() {
		return counter;
	}
}
