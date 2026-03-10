package ru.practicum.shareit.user.dao;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.EmailException;
import ru.practicum.shareit.user.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final Map<Long, User> users = new HashMap<>();
    private long id;

    @Override
    public User createUser(User user) {
        user.setId(++id);
        users.put(id, user);
        return user;
    }

    @Override
    public Optional<User> getUser(long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        List<User> found = users.values().stream()
                .filter(user -> Optional.ofNullable(user.getEmail())
                        .map(e -> e.equals(email))
                        .orElse(false))
                .toList();
        if (found.size() > 1) {
            throw new EmailException("Обнаружено несколько пользователей с таким email адресом: " + email);
        }
        return found.stream().findFirst();
    }

    @Override
    public void updateUser(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void deleteUser(long id) {
        users.remove(id);
    }
}
