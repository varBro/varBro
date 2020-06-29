package com.varbro.varbro.repository;

import com.varbro.varbro.model.PasswordResetToken;
import org.springframework.data.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
}
