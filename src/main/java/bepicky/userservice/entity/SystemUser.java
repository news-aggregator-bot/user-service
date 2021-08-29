package bepicky.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "system_user")
@EqualsAndHashCode(callSuper = true)
public class SystemUser extends IdEntity {

    @JsonProperty("chat_id")
    @Column(nullable = false, unique = true)
    private Long chatId;

    @Column
    private String username;

    @Column(name = "first_name")
    @JsonProperty("first_name")
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty("last_name")
    private String lastName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "system_user_role",
        joinColumns = {@JoinColumn(name = "id_user")},
        inverseJoinColumns = {@JoinColumn(name = "id_role")}
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<SystemRole> roles = new HashSet<>();

    public enum Status {
        ENABLED, DISABLED, NEW, PAUSED
    }
}
