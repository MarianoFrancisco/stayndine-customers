ALTER TABLE customer
    ADD COLUMN user_id BINARY(16) NULL AFTER id,
    ADD UNIQUE KEY uq_customer_user (user_id);