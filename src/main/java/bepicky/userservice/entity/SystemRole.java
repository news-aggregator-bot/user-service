package bepicky.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
@Table(name = "system_role")
@EqualsAndHashCode(callSuper = true)
public class SystemRole extends IdEntity {

    @Enumerated(EnumType.STRING)
    private Name name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "system_user_role",
        joinColumns = {@JoinColumn(name = "id_role")},
        inverseJoinColumns = {@JoinColumn(name = "id_user")}
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<SystemUser> users = new HashSet<>();

    public enum Name {
        ADMIN, GOD, SUPPORT
    }
}
