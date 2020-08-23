
@SuppressWarnings("serial")
class NetException extends Exception {
	// TODO create constructor for NetException
    private String mInfo;
    public NetException(final String info) {
        mInfo = info;
    }
    public String toString() {
        return "NetException: network attack: " + this.mInfo;
    }
}
