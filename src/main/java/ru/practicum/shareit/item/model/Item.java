package ru.practicum.shareit.item.model;

import lombok.*;
import jakarta.persistence.*;
import ru.practicum.shareit.request.ItemRequest;

import ru.practicum.shareit.user.model.User;

@Entity
@Builder
@Data
@Table(name = "items")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "is_available", nullable = false)
    private Boolean available;
    private ItemRequest request;
}
