package repository;

import model.User;
import repository.CRUDRepository;

public interface UserRepository extends CRUDRepository<User> {
    User findByUsername(String username);
}
