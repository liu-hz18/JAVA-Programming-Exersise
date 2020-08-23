public abstract class BaseStaff {
	protected BaseStaff() {
		countCreate(this);
	}

	/**
	 * Get the type of this staff.
	 * 
	 * @return a String, "Teacher" or "Student"
	 */
	public abstract String getType();

	/**
	 * Get the number of this staff.
	 * 
	 * @return an int, teacher number or student number
	 */
	public abstract int getNumber();

	@Override
	public int hashCode() {
		return (getType() + getNumber()).hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		return obj instanceof BaseStaff &&
				getType().equals(((BaseStaff) obj).getType()) &&
				getNumber() == ((BaseStaff) obj).getNumber();
	}

	/**
	 * Count how many BaseStaffs are created.
	 */
	private static class StaffCounter {
		private static int create;
	}

	/**
	 * Count a creation.
	 */
	private static void countCreate(final BaseStaff staff) {
		StaffCounter.create++;
	}

	/**
	 * Check creation.
	 * @return number of creations.
	 */
	public static int checkCreate() {
		return StaffCounter.create;
	}
}
