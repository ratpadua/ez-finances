package br.com.ez.finances.domain.enums;

/**
 * Enum with all the possible entities statuses.
 */
public enum Status {
    ACTIVE,
    INACTIVE;

    /**
     * Function used to check if the status list is filled, if not, return all statuses.
     * @param statuses The status list.
     * @return The status list.
     */
    public static Status[] validateStatuses(Status[] statuses) {
        if (statuses == null || statuses.length == 0) {
            statuses = statuses();
        }
        return statuses;
    }

    /**
     * Get all of the statuses.
     * @return List of all statuses.
     */
    public static Status[] statuses() {
        Status[] statusList = new Status[2];
        statusList[0] = Status.ACTIVE;
        statusList[1] = Status.INACTIVE;

        return statusList;
    }
}
