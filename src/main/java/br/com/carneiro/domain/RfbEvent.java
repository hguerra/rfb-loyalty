package br.com.carneiro.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A RfbEvent.
 */
@Entity
@Table(name = "rfb_event")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RfbEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name = "event_code")
    private String eventCode;

    @ManyToOne
    private RfbLocation rfbLocation;

    @OneToMany(mappedBy = "rfbEvent")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RfbEventAttendance> homeLocations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public RfbEvent eventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventCode() {
        return eventCode;
    }

    public RfbEvent eventCode(String eventCode) {
        this.eventCode = eventCode;
        return this;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public RfbLocation getRfbLocation() {
        return rfbLocation;
    }

    public RfbEvent rfbLocation(RfbLocation rfbLocation) {
        this.rfbLocation = rfbLocation;
        return this;
    }

    public void setRfbLocation(RfbLocation rfbLocation) {
        this.rfbLocation = rfbLocation;
    }

    public Set<RfbEventAttendance> getHomeLocations() {
        return homeLocations;
    }

    public RfbEvent homeLocations(Set<RfbEventAttendance> rfbEventAttendances) {
        this.homeLocations = rfbEventAttendances;
        return this;
    }

    public RfbEvent addHomeLocation(RfbEventAttendance rfbEventAttendance) {
        this.homeLocations.add(rfbEventAttendance);
        rfbEventAttendance.setRfbEvent(this);
        return this;
    }

    public RfbEvent removeHomeLocation(RfbEventAttendance rfbEventAttendance) {
        this.homeLocations.remove(rfbEventAttendance);
        rfbEventAttendance.setRfbEvent(null);
        return this;
    }

    public void setHomeLocations(Set<RfbEventAttendance> rfbEventAttendances) {
        this.homeLocations = rfbEventAttendances;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RfbEvent rfbEvent = (RfbEvent) o;
        if (rfbEvent.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rfbEvent.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RfbEvent{" +
            "id=" + getId() +
            ", eventDate='" + getEventDate() + "'" +
            ", eventCode='" + getEventCode() + "'" +
            "}";
    }
}
