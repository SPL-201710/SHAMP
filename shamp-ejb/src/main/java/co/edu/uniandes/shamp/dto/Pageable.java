package co.edu.uniandes.shamp.dto;

public class Pageable {

    private Integer page;
    private Integer size;

    public Pageable(int page, int size) {

        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }

        if (size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

        this.page = page;
        this.size = size;
    }

    public int getOffset() {
        return this.page * this.size;
    }

    public int getPageNumber() {
        return this.page;
    }

    public int getPageSize() {
        return this.size;
    }

    public boolean hasPrevious() {
        return this.page > 0;
    }

}