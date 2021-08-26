package edu.demian.wp.model.dto;

import edu.demian.wp.model.util.DataTransferObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Exposition implements DataTransferObject {
    private long id;
    private String topic;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startWorkTime;
    private LocalTime endWorkTime;
    private BigDecimal price;
    private long categoryId;

    private List<Hall> hallList;

    private final DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
    private String startDateFormatted;
    private String endDateFormatted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDateFormatted = startDate.format(formatters);
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDateFormatted = endDate.format(formatters);
        this.endDate = endDate;
    }

    public LocalTime getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(LocalTime startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public LocalTime getEndWorkTime() {
        return endWorkTime;
    }

    public void setEndWorkTime(LocalTime endWorkTime) {
        this.endWorkTime = endWorkTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getStartDateFormatted() {
        return startDateFormatted;
    }

    public String getEndDateFormatted() {
        return endDateFormatted;
    }

    public List<Hall> getHallList() {
        return hallList;
    }

    public void setHallList(List<Hall> hallList) {
        this.hallList = hallList;
    }

    public static long getOffset(long currentPage, int limit) {
        // curP = 1 ==> limit numOfRecords, offset 0 (curP - 1) * numOfRecords
        // curP = 2 ==> limit numOfRecords, offset 5 (curP - 1) * numOfRecords
        return (currentPage - 1) * limit;
    }

    public String toString() {
        return "Exposition{" +
                "id=" + id +
                ", topic='" + topic +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startWorkTime=" + startWorkTime +
                ", endWorkTime=" + endWorkTime +
                ", price=" + price +
                '}';
    }
}
