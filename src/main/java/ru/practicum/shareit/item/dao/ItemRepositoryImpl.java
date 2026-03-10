package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ItemRepositoryImpl implements ItemRepository {

    private final Map<Long, Item> items = new HashMap<>();
    private long id;

    @Override
    public Item createItem(Item item) {
        item.setId(++id);
        items.put(id, item);
        return item;
    }

    @Override
    public Optional<Item> getItemById(long id) {
        return Optional.ofNullable(items.get(id));
    }

    @Override
    public List<Item> searchItems(String search) {
        return items.values().stream()
                .filter(item -> Optional.ofNullable(item.getAvailable()).orElse(false))
                .filter(item -> Optional.ofNullable(item.getName())
                        .map(name -> name.toLowerCase().contains(search))
                        .orElse(false)
                        || Optional.ofNullable(item.getDescription())
                        .map(desc -> desc.toLowerCase().contains(search))
                        .orElse(false))
                .toList();
    }

    @Override
    public List<Item> getUserItems(long userId) {
        return items.values().stream()
                .filter(item -> Optional.ofNullable(item.getOwner())
                        .map(owner -> owner.getId() ==  userId)
                        .orElse(false))
                .toList();
    }

    @Override
    public void updateItem(Item item) {
        items.put(item.getId(), item);
    }
}
