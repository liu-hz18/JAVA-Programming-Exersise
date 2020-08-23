class MachinePhilosopher implements Runnable {
	public final int id;

	MachinePhilosopher(final int id) {
		this.id = id;
	}

	@Override
	public void run() {
		// TODO do as the problem description
		Com com = Com.getInstance();
		Integer msgNumber = null;
		Integer tempNumber;
		while (true){
			synchronized (com) {
				if (msgNumber == null) {
					msgNumber = com.getNumber();
					try {
						com.wait();
					} catch (InterruptedException e) {
						// Restore the interrupted status
             			Thread.currentThread().interrupt();
					}
				} else {
					if ((tempNumber = com.getNumber()) != msgNumber) {
						msgNumber = tempNumber;
						com.star(msgNumber, this.id);
					} else {
						continue;
					}
				}
			}
		}
	}
}
