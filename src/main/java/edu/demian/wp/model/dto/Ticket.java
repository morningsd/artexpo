package edu.demian.wp.model.dto;

import edu.demian.wp.model.dao.AccountDAO;
import edu.demian.wp.model.dao.ExpositionDAO;
import edu.demian.wp.model.util.DataTransferObject;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Ticket implements DataTransferObject {
    private long id;
    private long expositionId;
    private long accountId;
    private int quantity;
    private BigDecimal totalPrice;
    private LocalDateTime creationDate;

    private Exposition exposition;
    private Account account;

    private final DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu HH:mm:ss");

    private String creationDateFormatted;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public long getExpositionId() {
        return expositionId;
    }

    public void setExpositionId(long expositionId) {
        this.exposition = new ExpositionDAO().findById(expositionId);
        this.expositionId = expositionId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.account = new AccountDAO().findById(accountId);
        this.accountId = accountId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Exposition getExposition() {
        return exposition;
    }

    public void setExposition(Exposition exposition) {
        this.exposition = exposition;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDateFormatted = creationDate.format(formatters);
        this.creationDate = creationDate;
    }

    public String getCreationDateFormatted() {
        return creationDateFormatted;
    }

    public Account getAccount() {
        return account;
    }

    public static List<Ticket> sort(List<Ticket> ticketList) {
        ticketList.sort((o1, o2) -> {
            LocalDateTime dt1 = o1.getCreationDate();
            LocalDateTime dt2 = o2.getCreationDate();

            return -dt1.compareTo(dt2);
        });
        return ticketList;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", expositionId=" + expositionId +
                ", accountId=" + accountId +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", creationDateFormatted=" + creationDateFormatted +
                '}';
    }
}
