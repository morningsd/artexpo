package edu.demian.wp.model.dto;

import edu.demian.wp.model.util.DataTransferObject;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class Exposition implements DataTransferObject {
    private long id;
    private String topic;
    private Date startDate;
    private Date endDate;
    private Time startWorkTime;
    private Time endWorkTime;
    private BigDecimal price;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(Time startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public Time getEndWorkTime() {
        return endWorkTime;
    }

    public void setEndWorkTime(Time endWorkTime) {
        this.endWorkTime = endWorkTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String toString() {
        return "Exposition{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startWorkTime=" + startWorkTime +
                ", endWorkTime=" + endWorkTime +
                ", price=" + price +
                '}';
    }
}
