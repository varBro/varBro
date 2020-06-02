package com.varbro.varbro.repository;

import com.varbro.varbro.model.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {
}
