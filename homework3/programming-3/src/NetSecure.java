class NetSecure {
	public static void check(String packet) throws NetException {
		// TODO throw NetException if the packet is malware
        int index = packet.lastIndexOf(".");
        if (index >= 0) {
            String suffix = packet.substring(index, packet.length()).toLowerCase(); 
            if (suffix.equals(".exe")) {
                throw new NetException(packet);
            }
        }
	}
}
