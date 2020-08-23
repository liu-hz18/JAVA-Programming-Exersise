class NaiveStaff extends BaseStaff {
	private final String type;
	private final int number;
	private int punchCounter;

	public NaiveStaff(final String type, final int number) {
		super();
		this.type = type;
		this.number = number;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public int getNumber() {
		return number;
	}

	/**
	 * Count a punch record.
	 */
	public final void punch() {
		punchCounter++;
	}

	/**
	 * Check punch record.
	 * @return number of punch records.
	 */
	public final int punchCounter() {
		return punchCounter;
	}
}
