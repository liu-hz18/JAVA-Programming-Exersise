class Singleton extends BaseSingleton {
    private static Singleton mInstance = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() {
        return mInstance;
    }
}
