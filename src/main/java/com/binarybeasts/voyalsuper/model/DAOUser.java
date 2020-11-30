package com.binarybeasts.voyalsuper.model;

import com.binarybeasts.voyalsuper.model.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted=true WHERE id=?")
@Where(clause = "deleted = false")
public class DAOUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    private String password;

    @NotBlank
    @Email
    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(
            name = "users_shopping_carts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "shopping_cart_id")
    )
    private List<ShoppingCart> favouriteShoppingCarts;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    private Boolean deleted;

    public DAOUser(String username, String password, @NotBlank @Email String email, UserRole role, Boolean deleted) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.deleted = deleted;
        this.favouriteShoppingCarts = new ArrayList<>();
    }

    public DAOUser() {
        this.deleted = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ShoppingCart> getFavouriteShoppingCarts() {
        return favouriteShoppingCarts;
    }

    public void setFavouriteShoppingCarts(List<ShoppingCart> favouriteShoppingCarts) {
        this.favouriteShoppingCarts = favouriteShoppingCarts;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public void addShoppingCartToFavorites(ShoppingCart shoppingCart){
        favouriteShoppingCarts.add(shoppingCart);
    }
}
