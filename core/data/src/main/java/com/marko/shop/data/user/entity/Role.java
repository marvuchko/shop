package com.marko.shop.data.user.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.marko.shop.data.base.Base;

@Entity
@Table(name = "role")
public class Role extends Base {

    @Column(length = 120)
    private String caption;

    @Lob
    private String description;
    
    private Float discountByRole;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {
        super();
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public Float getDiscountByRole() {
		return discountByRole;
	}

	public void setDiscountByRole(Float discountByRole) {
		this.discountByRole = discountByRole;
	}

	public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
