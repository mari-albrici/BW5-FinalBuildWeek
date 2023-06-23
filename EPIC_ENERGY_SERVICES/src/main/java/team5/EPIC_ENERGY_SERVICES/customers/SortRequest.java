package team5.EPIC_ENERGY_SERVICES.customers;

public class SortRequest {
    private String sortBy;
    private boolean descending;

    public SortRequest() {}

    public SortRequest(String sortBy, boolean descending) {
        this.sortBy = sortBy;
        this.descending = descending;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public boolean isDescending() {
        return descending;
    }

    public void setDescending(boolean descending) {
        this.descending = descending;
    }
}
