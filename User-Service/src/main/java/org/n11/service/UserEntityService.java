package org.n11.service;

import org.n11.entity.User;
import org.n11.repository.UserRepository;
import org.n11.utilities.general.service.BaseEntityService;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@Service
public class UserEntityService extends BaseEntityService<User, UserRepository> {
    protected UserEntityService(UserRepository repository) {
        super(repository);
    }
}
