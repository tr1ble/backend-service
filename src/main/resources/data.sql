INSERT INTO users(id, username, password, role) SELECT '123e4567-e89b-12d3-a456-426614174002', 'admin', '$2a$12$fiBwspXISej9l2oH8me6xenk2weZFJroFOwvwyJlzOcmQgVoHXsPa', 'ADMIN' WHERE NOT EXISTS(SELECT username FROM users WHERE  username = 'admin');
INSERT INTO users(id, username, password, role) SELECT '123e4567-e89b-12d3-a456-426614174003', 'user', '$2a$12$SZt0gxUQScDut4UAkU/mNu/skmHsAs7VkU8SI8nAehBE9uw3jvz5K', 'USER' WHERE NOT EXISTS(SELECT username FROM users WHERE  username = 'user');
